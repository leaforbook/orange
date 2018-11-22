package com.leaforbook.orange.controller.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OrderStatusForm {
    @NotBlank(message = "订单状态不能为空")
    private String orderStatus;

    @NotBlank(message = "订单号不能为空")
    private String orderId;
}
