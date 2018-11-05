package com.leaforbook.orange.controller.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProductForm {

    @NotBlank(message = "产品名称不能为空")
    private String productName;

    private String productDesc;

    private String priceAttribute;

    private String freightAttribute;

}
