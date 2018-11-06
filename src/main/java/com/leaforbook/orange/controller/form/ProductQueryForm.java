package com.leaforbook.orange.controller.form;

import com.leaforbook.orange.util.PageForm;
import lombok.Data;

@Data
public class ProductQueryForm extends PageForm {
    private String productId;
    private String productName;
}
