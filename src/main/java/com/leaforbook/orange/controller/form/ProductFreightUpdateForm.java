package com.leaforbook.orange.controller.form;

import com.leaforbook.orange.util.Json;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class ProductFreightUpdateForm {

    @NotBlank(message = "运费ID不能为空")
    private String freightId;

    @NotBlank(message = "产品ID不能为空")
    private String productId;

    private String provinceId;

    private String attributeValue;

    @DecimalMin("0")
    private BigDecimal freightPrice;

    private boolean setOrNot;
}
