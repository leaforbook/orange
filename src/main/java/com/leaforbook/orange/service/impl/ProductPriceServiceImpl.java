package com.leaforbook.orange.service.impl;

import com.leaforbook.orange.controller.form.ProductPriceGetForm;
import com.leaforbook.orange.controller.form.ProductPriceListForm;
import com.leaforbook.orange.controller.form.ProductPriceUpdateListForm;
import com.leaforbook.orange.dao.mapper.OrangeProductPriceMapper;
import com.leaforbook.orange.dao.model.OrangeProductPrice;
import com.leaforbook.orange.service.ProductPriceService;
import com.leaforbook.orange.util.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductPriceServiceImpl implements ProductPriceService {

    @Autowired
    private OrangeProductPriceMapper productPriceMapper;

    @Autowired
    private SnowFlake snowFlake;

    @Override
    public void create(String userId, ProductPriceListForm form) {

    }

    @Override
    public void update(String userId, ProductPriceUpdateListForm form) {

    }

    @Override
    public List<OrangeProductPrice> get(ProductPriceGetForm form) {
        return null;
    }

    @Override
    public void delete(String productId) {

    }
}
