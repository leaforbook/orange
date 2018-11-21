package com.leaforbook.orange.controller.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CustomAddressIdForm {
    @NotBlank(message = "地址ID不能为空")
    private String addressId;
}
