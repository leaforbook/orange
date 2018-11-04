package com.leaforbook.orange.common.controller.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class ModifyPasswordForm {
    @NotBlank(message="账号不能为空")
    @Pattern(regexp="^\\w{5,10}$",message="账号格式不正确:5-10位字母数字下划线")
    private String userName;
    @NotBlank(message="密码不能为空")
    @Pattern(regexp="^\\w{6,12}$",message="密码格式不正确:6-12位字母数字下划线")
    private String password;
    @NotBlank(message="新密码不能为空")
    @Pattern(regexp="^\\w{6,12}$",message="密码格式不正确:6-12位字母数字下划线")
    private String newPassword;
    @NotBlank(message="重复新密码不能为空")
    private String repeatNewPassword;
}
