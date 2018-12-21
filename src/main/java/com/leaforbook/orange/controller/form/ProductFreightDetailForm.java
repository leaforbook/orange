package com.leaforbook.orange.controller.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProductFreightDetailForm {

    @NotBlank(message = "运费ID不能为空")
    private String freightId;
}
