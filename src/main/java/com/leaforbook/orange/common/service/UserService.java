package com.leaforbook.orange.common.service;

import com.leaforbook.orange.common.controller.form.*;
import com.leaforbook.orange.util.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface UserService {

    String register(RegisterForm form);

    String login(LoginForm form);

    void loginOut(String certificate);

    String resetPassword(ResetPasswordForm form);

    void modifyPassword(ModifyPasswordForm form);

    String setProof(String userName);

    void frozenUser(String userName);

    @Transactional
    void thawUser(String userName);

    void generateInvitation(InvitationForm form);

    UserInfo getUserByName(String userName);
}
