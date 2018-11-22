package com.leaforbook.orange.controller.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OrderIDForm {
    @NotBlank(message = "订单ID不能为空")
    private String orderId;
}
