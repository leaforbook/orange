package com.leaforbook.orange.controller.form;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderForm {
    @NotBlank(message = "产品ID不能为空")
    private String productId;

    private String priceId;

    private String freightId;

    private String addressId;

    @Min(1)
    private Integer amount;

    @DecimalMin("0.00")
    @NotNull
    private BigDecimal actualUnitPrice;

    @DecimalMin("0.00")
    @NotNull
    private BigDecimal totalPrice;

    @DecimalMin("0.00")
    @NotNull
    private BigDecimal actualFreight;

    @DecimalMin("0.00")
    @NotNull
    private BigDecimal totalCost;

    private Date deliveryDate;
}
