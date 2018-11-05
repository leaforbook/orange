package com.leaforbook.orange.service;

import com.leaforbook.orange.controller.form.ProductForm;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    void create(String userId,ProductForm form);
}