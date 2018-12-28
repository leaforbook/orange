package com.leaforbook.orange.service.impl;

import com.leaforbook.orange.controller.form.*;
import com.leaforbook.orange.dao.mapper.OrangeProductPriceMapper;
import com.leaforbook.orange.dao.model.OrangeProductPrice;
import com.leaforbook.orange.dao.model.OrangeProductPriceExample;
import com.leaforbook.orange.service.ProductPriceService;
import com.leaforbook.orange.util.DataStatus;
import com.leaforbook.orange.util.SnowFlake;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
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

        for(ProductPriceForm singleForm:form.getList()) {
            OrangeProductPriceExample example = new OrangeProductPriceExample();
            example.createCriteria().andProductIdEqualTo(singleForm.getProductId());
            List<OrangeProductPrice> dataInDB = productPriceMapper.selectByExample(example);

            boolean flag = false;
            if(dataInDB!=null&&dataInDB.size()>0) {
                for(OrangeProductPrice price:dataInDB) {
                    if(StringUtils.isNotEmpty(singleForm.getAttributeValue())&&singleForm.getAttributeValue().equals(price.getAttributeValue())) {
                        flag = true;
                        break;
                    }
                }
            }

            if(!flag) {
                this.insertProductPrice(userId,singleForm);
            }
        }

    }

    @Override
    public void update(String userId, ProductPriceUpdateListForm form) {
        //把该产品的所有价格信息都置为不可用
        OrangeProductPriceExample priceExample = new OrangeProductPriceExample();
        priceExample.createCriteria().andProductIdEqualTo(form.getList().get(0).getProductId());
        OrangeProductPrice productPrice = new OrangeProductPrice();
        productPrice.setDataStatus(DataStatus.UNAVAILABLE.getValue());
        productPriceMapper.updateByExampleSelective(productPrice,priceExample);

        List<ProductPriceUpdateForm> list = form.getList();
        for(ProductPriceUpdateForm singleForm:list) {

            OrangeProductPriceExample example = new OrangeProductPriceExample();
            example.createCriteria().andProductIdEqualTo(singleForm.getProductId());
            List<OrangeProductPrice>  dataInDB = productPriceMapper.selectByExample(example);

            boolean flag = false;
            String priceId = null;
            if(dataInDB!=null&&dataInDB.size()>0) {
                for(OrangeProductPrice price:dataInDB) {
                    if(StringUtils.isNotEmpty(singleForm.getAttributeValue())&&singleForm.getAttributeValue().equals(price.getAttributeValue())) {
                        flag = true;
                        priceId = price.getPriceId();
                        break;
                    }
                }
            }

            if(!flag) {
                this.insertProductPrice(userId,singleForm);
            } else {
                this.updateProductPrice(userId,priceId,singleForm);
            }

        }
    }

    @Override
    public List<OrangeProductPrice> get(ProductPriceGetForm form) {
        OrangeProductPriceExample example = new OrangeProductPriceExample();
        OrangeProductPriceExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(form.getProductId())
                .andDataStatusEqualTo(DataStatus.AVAILABLE.getValue());
        if(!form.isAll()) {
            criteria.andIsGroundingEqualTo("1");
        }

        List<OrangeProductPrice> list = productPriceMapper.selectByExample(example);
        return list;
    }

    @Override
    public void delete(String productId) {
        OrangeProductPriceExample example = new OrangeProductPriceExample();
        example.createCriteria().andProductIdEqualTo(productId);
        OrangeProductPrice price = new OrangeProductPrice();
        price.setDataStatus(DataStatus.UNAVAILABLE.getValue());
        price.setDateUpdate(new Date());
        productPriceMapper.updateByExampleSelective(price,example);
    }

    @Override
    public OrangeProductPrice detail(ProductPriceDetailForm form) {
        return productPriceMapper.selectByPrimaryKey(form.getPriceId());
    }

    private void insertProductPrice(String userId, ProductPriceForm singleForm) {
        OrangeProductPrice price = new OrangeProductPrice();
        BeanUtils.copyProperties(singleForm,price);
        String isGrounding = null;
        if(singleForm.isSetOrNot()) {
            isGrounding = "1";
        }else {
            isGrounding = "2";
            price.setInPrice(new BigDecimal(0.00));
            price.setOutMinPrice(new BigDecimal(0.00));
        }
        price.setIsGrounding(isGrounding);

        this.insertProductPrice(userId,price);
    }


    private void insertProductPrice(String userId, ProductPriceUpdateForm singleForm) {
        OrangeProductPrice price = new OrangeProductPrice();
        BeanUtils.copyProperties(singleForm,price);
        String isGrounding = null;
        if(singleForm.isSetOrNot()) {
            isGrounding = "1";
        }else {
            isGrounding = "2";
            price.setInPrice(new BigDecimal(0.00));
            price.setOutMinPrice(new BigDecimal(0.00));
        }
        price.setIsGrounding(isGrounding);

        this.insertProductPrice(userId,price);
    }


    private void updateProductPrice(String userId, String priceId,ProductPriceUpdateForm singleForm) {
        OrangeProductPrice price = new OrangeProductPrice();
        BeanUtils.copyProperties(singleForm,price);
        price.setByUpdate(userId);
        price.setDateUpdate(new Date());
        price.setPriceId(priceId);
        price.setDataStatus(DataStatus.AVAILABLE.getValue());
        String isGrounding = null;
        if(singleForm.isSetOrNot()) {
            isGrounding = "1";
        }else {
            isGrounding = "2";
            price.setInPrice(new BigDecimal(0.00));
            price.setOutMinPrice(new BigDecimal(0.00));
        }
        price.setIsGrounding(isGrounding);

        productPriceMapper.updateByPrimaryKeySelective(price);
    }

    private void insertProductPrice(String userId,OrangeProductPrice price) {
        price.setPriceId(snowFlake.getId());
        price.setByCreate(userId);
        price.setByUpdate(userId);
        price.setDateCreate(new Date());
        price.setDateUpdate(new Date());
        price.setDataStatus(DataStatus.AVAILABLE.getValue());

        productPriceMapper.insertSelective(price);
    }



}
