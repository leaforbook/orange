package com.leaforbook.orange.controller.form;

import com.leaforbook.orange.util.PageForm;
import lombok.Data;

@Data
public class OrderQueryForm extends PageForm {
    private String orderStatus;
}
