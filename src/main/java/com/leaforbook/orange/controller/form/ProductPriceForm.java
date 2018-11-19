package com.leaforbook.orange.controller.form;

import com.leaforbook.orange.util.Json;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProductPriceForm {

    @NotBlank(message = "产品ID不能为空")
    private String productId;

    @DecimalMin("0.00")
    @NotNull(message = "进价不能为空")
    private BigDecimal inPrice;

    @DecimalMin("0.00")
    @NotNull(message = "最小出价不能为空")
    private BigDecimal outMinPrice;

    @DecimalMin("0.00")
    @NotNull(message = "最大出价不能为空")
    private BigDecimal outMaxPrice;

    @Json
    @NotBlank(message = "属性不能为空")
    private String attributeJson;
}
