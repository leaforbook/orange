package com.leaforbook.orange.common.controller;

import com.alibaba.fastjson.JSON;
import com.leaforbook.orange.common.controller.form.*;
import com.leaforbook.orange.common.controller.vo.OneOfUsVO;
import com.leaforbook.orange.common.controller.vo.ProofVO;
import com.leaforbook.orange.common.dict.UserConstants;
import com.leaforbook.orange.common.service.UserService;
import com.leaforbook.orange.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/common/user")
@Slf4j
@Api(value = "用户信息维护", description = "用户信息维护")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private SessionUtil sessionUtil;


    @PostMapping("/verifyPassword")
    @ApiOperation(value = "验证密码", notes = "")
    public BasicResponse verifyPassword(HttpServletRequest request,@RequestBody PasswordForm form) {
        UserInfo userInfo = sessionUtil.getSessionInfo(request);
        boolean flag = userService.verifyPassword(userInfo.getUserId(),userInfo.getUserName(),form.getPassword());

        return new BasicResponse(flag);
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改用户信息", notes = "")
    @HasSession(key="verifyPassword")
    public BasicResponse update(HttpServletRequest request,@RequestBody UpdateUserForm form) {
        UserInfo userInfo = sessionUtil.getSessionInfo(request);
        String certificate = sessionUtil.getCertificate(request);
        boolean flag = form.getUserName().equals(userInfo.getUserName());
        userInfo.setRealName(form.getRealName());
        userInfo.setTelephone(form.getTelephone());
        userInfo.setUserName(form.getUserName());

        userService.update(userInfo,flag,certificate);

        return new BasicResponse();
    }

    @PostMapping("/userNameIsUsed")
    @ApiOperation(value = "判断用户名是否被占用", notes = "")
    public BasicResponse userNameIsUsed(@RequestBody UserForm form) {
        boolean flag = userService.userNameIsUsed(form.getUserName());
        return new BasicResponse(flag);
    }


    @PostMapping("/isLogined")
    @ApiOperation(value = "获取用户信息", notes = "")
    public BasicResponse isLogined(HttpServletRequest request) {
        boolean isLogined = sessionUtil.isLogined(request);
        return new BasicResponse(isLogined);
    }

    @PostMapping("/get")
    @ApiOperation(value = "获取用户信息", notes = "")
    public BasicResponse getUserInfo(HttpServletRequest request) {
        UserInfo userInfo = sessionUtil.getSessionInfo(request);
        return new BasicResponse(userInfo);
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册接口", notes = "")
    public BasicResponse register(HttpServletResponse response,@RequestBody @Valid RegisterForm form) {
        String certificate  = userService.register(form);
        this.addLoginState(response,certificate);

        OneOfUsVO vo = new OneOfUsVO();
        vo.setOneofus(certificate);
        return new BasicResponse(vo);
    }

    @PostMapping("/login")
    @ApiOperation(value = "登录接口", notes = "")
    public BasicResponse login(HttpServletResponse response, @RequestBody @Valid LoginForm form) {
        String certificate  = userService.login(form);
        this.addLoginState(response,certificate);

        OneOfUsVO vo = new OneOfUsVO();
        vo.setOneofus(certificate);
        return new BasicResponse(vo);
    }

    @PostMapping("/loginOut")
    @ApiOperation(value = "退出接口", notes = "")
    public BasicResponse loginOut(HttpServletRequest request) {
        String certificate =  sessionUtil.getCertificate(request);
        userService.loginOut(certificate);

        return new BasicResponse();
    }

    @PostMapping("/modifyPassword")
    @ApiOperation(value = "修改密码接口", notes = "")
    public BasicResponse modifyPassword(@RequestBody  @Valid ModifyPasswordForm form) {
        userService.modifyPassword(form);
        return new BasicResponse();
    }

    @PostMapping("/resetPassword")
    @ApiOperation(value = "重置密码接口", notes = "")
    public BasicResponse resetPassword(HttpServletResponse response,@RequestBody  @Valid ResetPasswordForm form) {
        String certificate = userService.resetPassword(form);
        this.addLoginState(response,certificate);

        OneOfUsVO vo = new OneOfUsVO();
        vo.setOneofus(certificate);
        return new BasicResponse(vo);
    }


    @PostMapping("/setProof")
    @ApiOperation(value = "设置重置密码凭证接口", notes = "")
    public BasicResponse setProof(HttpServletRequest request, @RequestBody  @Valid UserForm form) {
        log.info("UserController-setProof-form:"+JSON.toJSONString(form));
        this.notXiaoyilin(request);

        String proof = userService.setProof(form.getUserName());
        ProofVO vo = new ProofVO();
        vo.setProof(proof);

        return new BasicResponse(vo);
    }


    @PostMapping("/frozenUser")
    @ApiOperation(value = "冻结用户接口", notes = "")
    public BasicResponse frozenUser(HttpServletRequest request,@RequestBody  @Valid UserForm form) {
        log.info("UserController-frozenUser-form:"+JSON.toJSONString(form));
        this.notXiaoyilin(request);

        userService.frozenUser(form.getUserName());
        return new BasicResponse();
    }

    @PostMapping("/thawUser")
    @ApiOperation(value = "解冻用户接口", notes = "")
    public BasicResponse thawUser(HttpServletRequest request,@RequestBody  @Valid UserForm form) {
        log.info("UserController-thawUser-form:"+JSON.toJSONString(form));
        this.notXiaoyilin(request);

        userService.thawUser(form.getUserName());
        return new BasicResponse();
    }


    @PostMapping("/generateInvitation")
    @ApiOperation(value = "生成邀请码", notes = "")
    public BasicResponse generateInvitation(@RequestBody  @Valid InvitationForm form,HttpServletRequest request) {
        log.info("UserController-generateInvitation-form:"+JSON.toJSONString(form));
        this.notXiaoyilin(request);

        log.info(JSON.toJSONString(form));
        userService.generateInvitation(form);

        return new BasicResponse();
    }

    private void notXiaoyilin(HttpServletRequest request) {
        UserInfo userInfo = sessionUtil.getSessionInfo(request);
        if(!"xiaoyilin".equals(userInfo.getUserName())) {
            throw new BasicBusinessException(ExceptionEnum.NOT_XIAOYILIN);
        }
    }

    private void addLoginState(HttpServletResponse response,String certificate) {
        Cookie c = new Cookie(UserConstants.LOGIN_CERTIFICATE,certificate);
        c.setPath("/");
        response.addCookie(c);
    }
}
