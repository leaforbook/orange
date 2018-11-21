package com.leaforbook.orange.controller.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class CustomAddressForm {

    @NotBlank(message = "客户名称不能为空")
    private String name;

    @NotBlank(message = "客户性别不能为空")
    private String sex;

    @NotBlank(message = "省份ID不能为空")
    private String provinceId;

    @NotBlank(message = "省份名称不能为空")
    private String provinceName;

    @NotBlank(message = "收货地址不能为空")
    private String address;

    @NotBlank(message = "客户手机号不能为空")
    @Pattern(regexp="^1\\d{10}$",message="手机号格式不正确")
    private String telephone;

    private String mailcode;

    private String bak;
}
