package com.leaforbook.orange.controller.form;

import lombok.Data;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class ProductPriceForm {

    @NotBlank(message = "产品ID不能为空")
    private String productId;

    @DecimalMin("0.00")
    private BigDecimal inPrice;

    @DecimalMin("0.00")
    private BigDecimal outMinPrice;

    @DecimalMin("0.00")
    private BigDecimal outMaxPrice;

    @NotBlank(message = "属性不能为空")
    private String attributeValue;

    private boolean setOrNot;
}
