package com.leaforbook.orange.service;

import com.github.pagehelper.PageInfo;
import com.leaforbook.orange.controller.form.ProductForm;
import com.leaforbook.orange.controller.form.ProductQueryForm;
import com.leaforbook.orange.controller.form.ProductUpadateForm;
import com.leaforbook.orange.dao.model.OrangeProduct;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    void create(String userId,ProductForm form);

    void update(String userId,ProductUpadateForm form);

    OrangeProduct detail(String userId,String productId);

    void remove(String userId,String productId);

    PageInfo<OrangeProduct> query(String userId,ProductQueryForm form);

    void share(String productId, String userName);
}