package com.leaforbook.orange.common.controller.form;

import lombok.Data;

@Data
public class RegisterForm {
    private String userName;

    private String realName;

    private String password;

    private String repeatPassword;

    private String telephone;

    private String invitationCode;
}
