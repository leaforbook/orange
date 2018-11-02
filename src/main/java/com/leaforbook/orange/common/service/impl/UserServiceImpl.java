package com.leaforbook.orange.common.service.impl;

import com.leaforbook.orange.common.auth.CertificateUtils;
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
import com.leaforbook.orange.util.ExceptionEnum;
import com.leaforbook.orange.util.SnowFlake;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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


    @Override
    public UserInfo getUserInfo(HttpServletRequest request) {

        UserInfo userInfo = null;
        String certificate = CertificateUtils.getCertificate(request);
        if(StringUtils.isBlank(certificate)) {
            throw new BasicBusinessException(ExceptionEnum.UNLOGIN);
        }


        try{
            userInfo = (UserInfo)redisTemplate.opsForHash().get(UserConstants.LOGIN_CERTIFICATE,certificate);
        } catch (Throwable e) {
            throw new BasicBusinessException(ExceptionEnum.GET_USRINFO_FAILURE);
        }

        if(userInfo==null) {
            throw new BasicBusinessException(ExceptionEnum.LOGIN_EXPIRE);
        }

        return userInfo;
    }

    @Override
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
        String oneofus = snowFlake.getId();
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(user,userInfo);
        redisTemplate.opsForHash().put(UserConstants.LOGIN_CERTIFICATE,oneofus,userInfo);

        return oneofus;
    }

    @Override
    public void login(LoginForm form) {
        //验证是否已登录

        //验证用户是否已注册

        //验证用户密码

        //加入登录态
    }

    @Override
    public void loginOut(HttpServletRequest request) {
        //清除登陆态
    }

    @Override
    public void resetPassword(ResetPasswordForm form) {
        //验证旧密码是否正确

        //验证两次新密码是否一致

        //更新密码
    }

    @Override
    public void modifyPassword(ModifyPasswordForm form) {
        //验证凭据

        //验证两次新密码是否一致

        //更新密码
    }

    @Override
    public void initPassword(InitPasswordForm form) {
        //检查用户是否存在
        //初始化密码gnqcdlzs2018
    }
}
