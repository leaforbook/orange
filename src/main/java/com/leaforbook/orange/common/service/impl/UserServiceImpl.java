package com.leaforbook.orange.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.leaforbook.orange.common.auth.UserInfo;
import com.leaforbook.orange.common.controller.form.*;
import com.leaforbook.orange.common.dao.mapper.CommonInvitationMapper;
import com.leaforbook.orange.common.dao.mapper.CommonUserMapper;
import com.leaforbook.orange.common.dao.model.CommonInvitation;
import com.leaforbook.orange.common.dao.model.CommonInvitationExample;
import com.leaforbook.orange.common.dao.model.CommonUser;
import com.leaforbook.orange.common.dao.model.CommonUserExample;
import com.leaforbook.orange.common.dict.UserConstants;
import com.leaforbook.orange.common.dict.UserStatus;
import com.leaforbook.orange.common.service.UserService;
import com.leaforbook.orange.util.BasicBusinessException;
import com.leaforbook.orange.util.DataStatus;
import com.leaforbook.orange.util.ExceptionEnum;
import com.leaforbook.orange.util.SnowFlake;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private SnowFlake snowFlake;

    @Autowired
    private CommonInvitationMapper commonInvitationMapper;

    @Autowired
    private CommonUserMapper userMapper;

    /**
     * 获取用户信息
     * @param certificate
     * @return
     */
    @Override
    public UserInfo getUserInfo(String certificate) {

        if(StringUtils.isBlank(certificate)) {
            throw new BasicBusinessException(ExceptionEnum.UNLOGIN);
        }

        UserInfo userInfo = new UserInfo();
        try{
            userInfo = JSON.parseObject((String)redisTemplate.opsForValue().get(UserConstants.LOGIN_CERTIFICATE+"_"+certificate),UserInfo.class);
        } catch (Throwable e) {
            throw new BasicBusinessException(ExceptionEnum.GET_USRINFO_FAILURE);
        }

        if(userInfo==null) {
            throw new BasicBusinessException(ExceptionEnum.LOGIN_EXPIRE);
        }

        return userInfo;
    }

    /**
     * 注册新用户
     * @param form
     * @return
     */
    @Override
    @Transactional
    public String register(RegisterForm form) {
        //验证邀请码有效性
        CommonInvitationExample invitationExample = new CommonInvitationExample();
        invitationExample.createCriteria().andAvailableCountGreaterThan(new Integer(0)).andInvitationIdEqualTo(form.getInvitationCode());
        List<CommonInvitation> invitationList = commonInvitationMapper.selectByExample(invitationExample);
        if(invitationList==null||invitationList.size()==0) {
            throw new BasicBusinessException(ExceptionEnum.INVITATION_INVALID);
        }
        CommonInvitation invitation =invitationList.get(0);

        //验证用户名是否被占用
        CommonUserExample userExample = new CommonUserExample();
        userExample.createCriteria().andUserNameEqualTo(form.getUserName());
        long count = userMapper.countByExample(userExample);
        if(count>0l) {
            throw new BasicBusinessException(ExceptionEnum.USERNAME_USED);
        }

        //验证两次密码是否一致
        if(form.getPassword()==null||!form.getPassword().equals(form.getRepeatPassword())) {
            throw new BasicBusinessException(ExceptionEnum.PASSWORD_DIFFER);
        }

        //注册用户信息，密码加密和初始化角色信息
        CommonUser user = new CommonUser();
        BeanUtils.copyProperties(form,user);
        user.setPassword(DigestUtils.md5DigestAsHex(form.getPassword().getBytes()));
        user.setUserStatus(UserStatus.NORMAL.getValue());
        user.setUserId(snowFlake.getId());
        userMapper.insertSelective(user);

        //加入登录态
        String certificate = snowFlake.getId();
        this.setLoginState(certificate,user);

        //把邀请码的可用次数减一
        invitation.setAvailableCount(invitation.getAvailableCount()-1);
        if(invitation.getAvailableCount()<=0) {
            invitation.setDataStatus(DataStatus.UNAVAILABLE.getValue());
        }
        commonInvitationMapper.updateByPrimaryKeySelective(invitation);

        return certificate;
    }

    /**
     * 登录
     * @param form
     * @return
     */
    @Override
    public String login(LoginForm form) {

        //验证用户密码
        CommonUser user = this.authentication(form.getUserName(),form.getPassword());

        //加入登录态
        String certificate = snowFlake.getId();
        this.setLoginState(certificate,user);

        return certificate;
    }

    /**
     * 设置登录态
     * @param certificate
     * @param user
     */
    private void setLoginState(String certificate,CommonUser user) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(user,userInfo);

        redisTemplate.opsForValue().set(UserConstants.LOGIN_CERTIFICATE+"_"+certificate,JSON.toJSONString(userInfo));
        redisTemplate.opsForValue().set(UserConstants.LOGIN_USERNAME+"_"+userInfo.getUserName(),certificate);
        redisTemplate.expire(UserConstants.LOGIN_CERTIFICATE+"_"+certificate,60*60*3, TimeUnit.SECONDS);
        redisTemplate.expire(UserConstants.LOGIN_USERNAME+"_"+userInfo.getUserName(),60*60*3, TimeUnit.SECONDS);
    }

    /**
     * 清楚登录态
     * @param certificate
     */
    @Override
    public void loginOut(String certificate) {
        //清除登陆态
        UserInfo userInfo = JSON.parseObject((String)redisTemplate.opsForValue().get(UserConstants.LOGIN_CERTIFICATE+"_"+certificate),UserInfo.class);
        if(userInfo==null) {
            throw new BasicBusinessException(ExceptionEnum.UNLOGIN);
        }

        String userName = userInfo.getUserName();
        redisTemplate.delete(UserConstants.LOGIN_CERTIFICATE+"_"+certificate);
        redisTemplate.delete(UserConstants.LOGIN_USERNAME+"_"+userName);
    }

    /**
     * 修改密码
     * @param form
     */
    @Override
    @Transactional
    public void modifyPassword(ModifyPasswordForm form) {
        //验证旧密码是否正确
        CommonUser user = this.authentication(form.getUserName(),form.getPassword());

        //验证两次新密码是否一致
        if(!form.getNewPassword().equals(form.getRepeatNewPassword())) {
            throw new BasicBusinessException(ExceptionEnum.PASSWORD_DIFFER);
        }

        //更新密码
        user.setPassword(DigestUtils.md5DigestAsHex(form.getNewPassword().getBytes()));
        user.setDateUpdate(new Date());

        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 认证用户密码是否正确
     * @param userName
     * @param password
     * @return
     */
    private CommonUser authentication(String userName,String password) {

        CommonUser user = this.getUser(userName);
        String passwordInDB = user.getPassword();
        if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(passwordInDB)) {
            throw new BasicBusinessException(ExceptionEnum.PASSWORD_WRONG);
        }

        return user;
    }

    /**
     * 根据用户名获取用户信息
     * @param userName
     * @return
     */
    private CommonUser getUser(String userName) {
        CommonUserExample userExample = new CommonUserExample();
        userExample.createCriteria().andUserNameEqualTo(userName).andUserStatusEqualTo(UserStatus.NORMAL.getValue());
        List<CommonUser> userList = userMapper.selectByExample(userExample);
        if(userList==null||userList.size()==0) {
            throw new BasicBusinessException(ExceptionEnum.USER_NON_EXISTENT);
        }

        CommonUser user = userList.get(0);

        return user;
    }

    private CommonUser getUserForAll(String userName) {
        CommonUserExample userExample = new CommonUserExample();
        userExample.createCriteria().andUserNameEqualTo(userName);
        List<CommonUser> userList = userMapper.selectByExample(userExample);
        if(userList==null||userList.size()==0) {
            throw new BasicBusinessException(ExceptionEnum.USER_NON_EXISTENT);
        }

        CommonUser user = userList.get(0);

        return user;
    }

    /**
     * 重置密码
     * @param form
     */
    @Override
    @Transactional
    public void resetPassword(ResetPasswordForm form) {
        //验证凭据
        String proofInRedis = (String)redisTemplate.opsForValue().get(UserConstants.MODIFY_PASSWORD_PROOF+"_"+form.getUserName());
        if(!form.getProof().equals(proofInRedis)) {
            throw new BasicBusinessException(ExceptionEnum.PROOF_WRONG);
        }

        //验证两次新密码是否一致
        if(!form.getPassword().equals(form.getRepeatPassword())) {
            throw new BasicBusinessException(ExceptionEnum.PASSWORD_DIFFER);
        }

        //更新密码
        CommonUser user = this.getUser(form.getUserName());
        user.setPassword(DigestUtils.md5DigestAsHex(form.getPassword().getBytes()));
        user.setDateUpdate(new Date());
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 设置重置密码凭据
     * @param userName
     * @return
     */
    @Override
    public String setProof(String userName) {
        this.getUser(userName);
        String proof = snowFlake.getId();
        redisTemplate.opsForValue().set(UserConstants.MODIFY_PASSWORD_PROOF+"_"+userName,proof);
        redisTemplate.expire(UserConstants.MODIFY_PASSWORD_PROOF+"_"+userName,60*30, TimeUnit.SECONDS);
        return proof;
    }

    /**
     * 冻结用户
     * @param userName
     */
    @Override
    @Transactional
    public void frozenUser(String userName) {
        CommonUser user = this.getUser(userName);
        user.setUserStatus(UserStatus.FROZEN.getValue());
        user.setDateUpdate(new Date());
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    @Transactional
    public void thawUser(String userName) {
        CommonUser user = this.getUserForAll(userName);
        user.setUserStatus(UserStatus.NORMAL.getValue());
        user.setDateUpdate(new Date());
        userMapper.updateByPrimaryKeySelective(user);
    }


    @Override
    public void generateInvitation(InvitationForm form) {
        List<InvitationCountAndTime> codes = form.getCodes();

        if(codes!=null&&codes.size()>0) {
            for(InvitationCountAndTime code: codes) {
                int count = code.getCount();
                int time = code.getTime();

                for(int i=0;i<count;i++) {
                    String invitation = snowFlake.getId();
                    CommonInvitation commonInvitation = new CommonInvitation();
                    commonInvitation.setInvitationId(invitation);
                    commonInvitation.setTotalCount(time);
                    commonInvitation.setAvailableCount(time);
                    commonInvitationMapper.insertSelective(commonInvitation);
                }
            }
        }

        CommonInvitationExample invitationExample = new CommonInvitationExample();
        invitationExample.createCriteria().andAvailableCountLessThanOrEqualTo(0);
        commonInvitationMapper.deleteByExample(invitationExample);
    }

}
