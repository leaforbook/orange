package com.leaforbook.orange.service;

import com.leaforbook.orange.controller.form.ProductPriceGetForm;
import com.leaforbook.orange.controller.form.ProductPriceListForm;
import com.leaforbook.orange.controller.form.ProductPriceUpdateListForm;
import com.leaforbook.orange.dao.model.OrangeProductPrice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductPriceService {
    void create(String userId, ProductPriceListForm form);

    void update(String userId, ProductPriceUpdateListForm form);

    List<OrangeProductPrice> get(ProductPriceGetForm form);

    void delete(String productId);
}
