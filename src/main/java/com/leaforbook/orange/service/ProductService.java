package com.leaforbook.orange.service;

import com.leaforbook.orange.controller.form.ProductForm;
import com.leaforbook.orange.controller.form.ProductUpadateForm;
import com.leaforbook.orange.dao.model.OrangeProduct;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    void create(String userId,ProductForm form);

    void update(ProductUpadateForm form);

    OrangeProduct detail(String productId);

    void remove(String productId);
}