package com.leaforbook.orange.common.controller.form;

import lombok.Data;

@Data
public class ModifyPasswordForm {
    private String userName;
    private String password;
    private String newPassword;
    private String repeatNewPassword;
}
