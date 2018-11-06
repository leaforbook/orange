package com.leaforbook.orange.controller.form;

import com.leaforbook.orange.util.Json;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProductForm {

    @NotBlank(message = "产品名称不能为空")
    private String productName;

    private String productDesc;

    @Json
    private String priceAttribute;

    @Json
    private String freightAttribute;

}
