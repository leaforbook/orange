package com.leaforbook.orange.common.controller.form;

import com.leaforbook.orange.util.PageForm;
import lombok.Data;

@Data
public class ExpressForm  extends PageForm {
    private String queryParams;
    private String expressId;
}
