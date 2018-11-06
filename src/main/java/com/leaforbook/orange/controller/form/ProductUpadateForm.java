package com.leaforbook.orange.controller.form;

import com.leaforbook.orange.util.Json;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class ProductUpadateForm {

    @NotBlank(message = "产品ID不能为空")
    private String productId;

    @NotBlank(message = "产品名称不能为空")
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9_]+$",message = "产品名称只能由中文，英文，数字，下划线组成")
    private String productName;

    private String productDesc;

    @Json
    private String priceAttribute;

    @Json
    private String freightAttribute;
}
