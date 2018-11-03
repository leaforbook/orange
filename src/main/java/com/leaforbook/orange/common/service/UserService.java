package com.leaforbook.orange.common.service;

import com.leaforbook.orange.common.auth.UserInfo;
import com.leaforbook.orange.common.controller.form.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
public interface UserService {

    UserInfo getUserInfo(String certificate);

    String register(RegisterForm form);

    String login(LoginForm form);

    void loginOut(String certificate);

    void resetPassword(ResetPasswordForm form);

    void modifyPassword(ModifyPasswordForm form);

    String setProof(String userName);

    void frozenUser(String userName);

    @Transactional
    void thawUser(String userName);

    void generateInvitation(InvitationForm form);
}
