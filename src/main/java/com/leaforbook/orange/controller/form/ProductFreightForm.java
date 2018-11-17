package com.leaforbook.orange.controller.form;

import com.leaforbook.orange.util.Json;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProductFreightForm {

    @NotBlank(message = "产品ID不能为空")
    private String productId;

    @NotBlank(message = "省份不能为空")
    private String provinceId;

    @NotBlank(message = "运费属性不能为空")
    @Json
    private String attributeValue;

    @DecimalMin("0")
    @NotNull
    private BigDecimal freightPrice;
}
