package com.leaforbook.orange.controller.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LogisticsUpdateForm {

    @NotBlank(message = "订单ID不能为空")
    private String orderId;

    @NotBlank(message = "快递公司不能为空")
    private String type;

    @NotBlank(message = "快递单号不能为空")
    private String postid;
}
