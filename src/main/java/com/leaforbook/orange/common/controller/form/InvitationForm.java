package com.leaforbook.orange.common.controller.form;

import lombok.Data;

import java.util.List;

@Data
public class InvitationForm {
    List<InvitationCountAndTime> codes;
}


