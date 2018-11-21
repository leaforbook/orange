package com.leaforbook.orange.controller.form;

import com.leaforbook.orange.util.PageForm;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class CustomAddressQueryForm extends PageForm {
    private String name;

    private String provinceName;

    private String address;

    private String telephone;

    private String mailcode;

    private String bak;
}
