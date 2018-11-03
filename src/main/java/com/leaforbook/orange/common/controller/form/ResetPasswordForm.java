package com.leaforbook.orange.common.controller.form;

import lombok.Data;

@Data
public class ResetPasswordForm {
    private String userName;
    private String proof;
    private String password;
    private String repeatPassword;
}
