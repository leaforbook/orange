package com.leaforbook.orange.service.impl;

import com.leaforbook.orange.controller.form.ProductForm;
import com.leaforbook.orange.dao.mapper.OrangeProductMapper;
import com.leaforbook.orange.dao.model.OrangeProduct;
import com.leaforbook.orange.service.ProductService;
import com.leaforbook.orange.util.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private SnowFlake snowFlake;

    @Autowired
    private OrangeProductMapper productMapper;

    @Override
    public void create(String userId, ProductForm form) {
        OrangeProduct product = new OrangeProduct();
        BeanUtils.copyProperties(form,product);
        product.setUserId(userId);
        product.setProductId(snowFlake.getId());
        productMapper.insertSelective(product);
    }


}
