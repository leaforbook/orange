package com.leaforbook.orange.service;

import com.github.pagehelper.Page;
import com.leaforbook.orange.controller.form.ProductCopyForm;
import com.leaforbook.orange.controller.form.ProductForm;
import com.leaforbook.orange.controller.form.ProductQueryForm;
import com.leaforbook.orange.controller.form.ProductUpadateForm;
import com.leaforbook.orange.dao.model.OrangeProduct;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    String create(String userId,ProductForm form);

    void update(String userId,ProductUpadateForm form);

    OrangeProduct detail(String productId);

    void delete(String userId,String productId);

    void remove(String userId,String productId);

    Page<OrangeProduct> query(String userId, ProductQueryForm form);

    String share(String productId, String userName);

    boolean isCreater(String productId,String userId);

    String copy(String userId, ProductCopyForm form);
}