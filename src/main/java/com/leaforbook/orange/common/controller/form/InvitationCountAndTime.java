package com.leaforbook.orange.common.controller.form;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class InvitationCountAndTime {

    @Min(1)
    private int count;

    @Min(1)
    private int time;
}