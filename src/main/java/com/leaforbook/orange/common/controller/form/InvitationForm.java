package com.leaforbook.orange.common.controller.form;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class InvitationForm {
    @Size(min=1)
    List<@Valid InvitationCountAndTime> codes;
}


