package com.leaforbook.orange.controller.form;

import lombok.Data;

@Data
public class CustomAddressUpdateForm {
    private String addressId;

    private String name;

    private String sex;

    private String provinceId;

    private String provinceName;

    private String address;

    private String telephone;

    private String mailcode;

    private String bak;
}
