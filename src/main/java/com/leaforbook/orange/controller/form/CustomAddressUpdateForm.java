package com.leaforbook.orange.controller.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class CustomAddressUpdateForm {
    @NotBlank(message = "地址ID不能为空")
    private String addressId;

    private String name;

    private String sex;

    private String provinceId;

    private String provinceName;

    private String address;

    @Pattern(regexp="^1\\d{10}$",message="手机号格式不正确")
    private String telephone;

    private String mailcode;

    private String bak;
}
