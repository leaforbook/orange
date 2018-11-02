package com.leaforbook.orange.common.service;

import com.leaforbook.orange.common.auth.UserInfo;
import com.leaforbook.orange.common.controller.form.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public interface UserService {

    UserInfo getUserInfo(HttpServletRequest request);

    String register(RegisterForm form);

    void login(LoginForm form);

    void loginOut(HttpServletRequest request);

    void resetPassword(ResetPasswordForm form);

    void modifyPassword(ModifyPasswordForm form);

    void initPassword(InitPasswordForm form);
}
