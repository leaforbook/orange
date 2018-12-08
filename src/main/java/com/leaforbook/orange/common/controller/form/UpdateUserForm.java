package com.leaforbook.orange.common.controller.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UpdateUserForm {
    @NotBlank(message="账号不能为空")
    @Pattern(regexp="^\\w{5,10}$",message="账号格式不正确:5-10位字母数字下划线")
    private String userName;
    @NotBlank(message="真实姓名不能为空")
    @Pattern(regexp="^[\\u4e00-\\u9fa5]{0,}$",message="真实姓名格式不正确")
    private String realName;
    @NotBlank(message="手机号不能为空")
    @Pattern(regexp="^1\\d{10}$",message="手机号格式不正确")
    private String telephone;
}
