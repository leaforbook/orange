package com.leaforbook.orange.controller.vo;

import com.leaforbook.orange.dao.model.OrangeOrder;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrangeOrderVO extends OrangeOrder {
    private String productName;
    private String price;
    private String freight;
    private String name;
}
