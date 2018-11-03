package com.leaforbook.orange.common.controller;

import com.alibaba.fastjson.JSON;
import com.leaforbook.orange.common.auth.CertificateUtils;
import com.leaforbook.orange.common.auth.UserInfo;
import com.leaforbook.orange.common.controller.form.*;
import com.leaforbook.orange.common.controller.vo.ProofVO;
import com.leaforbook.orange.common.dict.UserConstants;
import com.leaforbook.orange.common.service.UserService;
import com.leaforbook.orange.util.BasicBusinessException;
import com.leaforbook.orange.util.BasicResponse;
import com.leaforbook.orange.util.ExceptionEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/common/user")
@Slf4j
@Api(value = "用户信息维护", description = "用户信息维护")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/get")
    @ApiOperation(value = "获取用户信息", notes = "")
    public UserInfo getUserInfo(HttpServletRequest request) {
        String certificate =  CertificateUtils.getCertificate(request);
        UserInfo userInfo = userService.getUserInfo(certificate);
        return userInfo;
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册接口", notes = "")
    public BasicResponse register(HttpServletResponse response,@RequestBody RegisterForm form) {
        String certificate  = userService.register(form);
        this.addLoginState(response,certificate);

        return new BasicResponse();
    }

    @PostMapping("/login")
    @ApiOperation(value = "登录接口", notes = "")
    public BasicResponse login(HttpServletResponse response, @RequestBody LoginForm form) {
        String certificate  = userService.login(form);
        this.addLoginState(response,certificate);

        return new BasicResponse();
    }

    @PostMapping("/loginOut")
    @ApiOperation(value = "退出接口", notes = "")
    public BasicResponse loginOut(HttpServletRequest request) {
        String certificate =  CertificateUtils.getCertificate(request);
        userService.loginOut(certificate);

        return new BasicResponse();
    }

    @PostMapping("/modifyPassword")
    @ApiOperation(value = "修改密码接口", notes = "")
    public BasicResponse modifyPassword(@RequestBody ModifyPasswordForm form) {
        userService.modifyPassword(form);
        return new BasicResponse();
    }

    @PostMapping("/resetPassword")
    @ApiOperation(value = "修改密码接口", notes = "")
    public BasicResponse resetPassword(@RequestBody ResetPasswordForm form) {
        userService.resetPassword(form);
        return new BasicResponse();
    }


    @PostMapping("/setProof")
    @ApiOperation(value = "设置重置密码凭证接口", notes = "")
    public ProofVO setProof(HttpServletRequest request, @RequestBody UserForm form) {
        log.info("UserController-setProof-form:"+JSON.toJSONString(form));
        this.notXiaoyilin(request);

        String proof = userService.setProof(form.getUserName());
        ProofVO vo = new ProofVO();
        vo.setProof(proof);

        return vo;
    }


    @PostMapping("/frozenUser")
    @ApiOperation(value = "冻结用户接口", notes = "")
    public BasicResponse frozenUser(HttpServletRequest request,@RequestBody UserForm form) {
        log.info("UserController-frozenUser-form:"+JSON.toJSONString(form));
        this.notXiaoyilin(request);

        userService.frozenUser(form.getUserName());
        return new BasicResponse();
    }

    @PostMapping("/thawUser")
    @ApiOperation(value = "解冻用户接口", notes = "")
    public BasicResponse thawUser(HttpServletRequest request,@RequestBody UserForm form) {
        log.info("UserController-thawUser-form:"+JSON.toJSONString(form));
        this.notXiaoyilin(request);

        userService.thawUser(form.getUserName());
        return new BasicResponse();
    }


    @PostMapping("/generateInvitation")
    @ApiOperation(value = "生成邀请码", notes = "")
    public BasicResponse generateInvitation(@RequestBody InvitationForm form,HttpServletRequest request) {
        log.info("UserController-generateInvitation-form:"+JSON.toJSONString(form));
        this.notXiaoyilin(request);

        log.info(JSON.toJSONString(form));
        userService.generateInvitation(form);

        return new BasicResponse();
    }

    private void notXiaoyilin(HttpServletRequest request) {
        String certificate =  CertificateUtils.getCertificate(request);
        UserInfo userInfo = userService.getUserInfo(certificate);
        if(!"xiaoyilin".equals(userInfo.getUserName())) {
            throw new BasicBusinessException(ExceptionEnum.NOT_XIAOYILIN);
        }
    }

    private void addLoginState(HttpServletResponse response,String certificate) {
        Cookie c = new Cookie(UserConstants.LOGIN_CERTIFICATE,certificate);
        response.addCookie(c);
    }
}
