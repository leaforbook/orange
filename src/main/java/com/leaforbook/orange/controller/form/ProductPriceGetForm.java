package com.leaforbook.orange.controller.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProductPriceGetForm {
    @NotBlank(message = "产品ID不能为空")
    private String productId;
}
