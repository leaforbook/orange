package com.leaforbook.orange.controller.form;

import com.leaforbook.orange.util.Json;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class ProductPriceUpdateForm {
    @NotBlank(message = "价格ID不能为空")
    private String priceId;

    @NotBlank(message = "产品ID不能为空")
    private String productId;

    @DecimalMin("0.00")
    private BigDecimal inPrice;

    @DecimalMin("0.00")
    private BigDecimal outMinPrice;

    @DecimalMin("0.00")
    private BigDecimal outMaxPrice;

    @Json
    private String attributeJson;
}
