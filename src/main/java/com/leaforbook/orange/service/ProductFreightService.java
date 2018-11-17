package com.leaforbook.orange.service;

import com.leaforbook.orange.controller.form.*;
import com.leaforbook.orange.dao.model.OrangeProductFreight;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface ProductFreightService {
    void create(String userId, ProductFreightListForm form);

    void update(String userId, ProductFreightUpdateForm form);

    OrangeProductFreight get(ProductFreightGetForm form);

    void delete(String productId);

    BigDecimal getPrice(ProductFreightGetPriceForm form);
}
