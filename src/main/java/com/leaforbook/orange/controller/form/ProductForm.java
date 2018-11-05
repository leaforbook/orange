package com.leaforbook.orange.controller.form;

import com.leaforbook.orange.dao.model.OrangeProductPrice;
import lombok.Data;

import java.util.List;

@Data
public class ProductForm {
    private String productId;

    private String userId;

    private String productName;

    private String productDesc;

    private List<OrangeProductPrice> productPriceList;
}
