package com.leaforbook.orange.controller.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProductPriceDetailForm {

    @NotBlank(message = "价格ID不能为空")
    private String priceId;
}
