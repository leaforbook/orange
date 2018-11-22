package com.leaforbook.orange.controller.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LogisticsSingleForm {
    @NotBlank(message = "订单号不能为空")
    private String OrderId;
}
