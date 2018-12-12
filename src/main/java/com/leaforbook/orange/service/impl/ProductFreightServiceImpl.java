package com.leaforbook.orange.service.impl;

import com.alibaba.fastjson.JSON;
import com.leaforbook.orange.controller.form.*;
import com.leaforbook.orange.dao.mapper.OrangeProductFreightMapper;
import com.leaforbook.orange.dao.model.OrangeProductFreight;
import com.leaforbook.orange.dao.model.OrangeProductFreightExample;
import com.leaforbook.orange.service.ProductFreightService;
import com.leaforbook.orange.util.DataStatus;
import com.leaforbook.orange.util.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class ProductFreightServiceImpl implements ProductFreightService {

    @Autowired
    private OrangeProductFreightMapper freightMapper;

    @Autowired
    private SnowFlake snowFlake;

    @Override
    public void create(String userId, ProductFreightListForm form) {

        for(ProductFreightForm singleForm:form.getList()) {
            OrangeProductFreightExample example = new OrangeProductFreightExample();
            example.createCriteria().andProductIdEqualTo(singleForm.getProductId());
            List<OrangeProductFreight>  dataInDB = freightMapper.selectByExample(example);

            boolean flag = false;
            if(dataInDB!=null&&dataInDB.size()>0) {
                for(OrangeProductFreight freight:dataInDB) {
                    if(this.isAttributeValueEquals(singleForm.getAttributeValue(),freight.getAttributeValue())
                            &&this.isProvinceEquals(singleForm.getProvinceId(),freight.getProvinceId())) {
                        flag = true;
                        break;
                    }
                }
            }


            if(!flag) {
                this.insertProductFreight(userId,singleForm);
            }

        }

    }


    @Override
    public void update(String userId, ProductFreightUpdateListForm form) {

        //把该产品的所有运费信息都置为不可用
        OrangeProductFreightExample freightExample = new OrangeProductFreightExample();
        freightExample.createCriteria().andProductIdEqualTo(form.getList().get(0).getProductId());
        OrangeProductFreight productFreight = new OrangeProductFreight();
        productFreight.setDataStatus(DataStatus.UNAVAILABLE.getValue());
        freightMapper.updateByExampleSelective(productFreight,freightExample);

        List<ProductFreightUpdateForm> list = form.getList();
        for(ProductFreightUpdateForm singleForm:list) {

            OrangeProductFreightExample example = new OrangeProductFreightExample();
            example.createCriteria().andProductIdEqualTo(singleForm.getProductId());
            List<OrangeProductFreight>  dataInDB = freightMapper.selectByExample(example);

            boolean flag = false;
            String freightId = null;
            if(dataInDB!=null&&dataInDB.size()>0) {
                for(OrangeProductFreight freight:dataInDB) {
                    if(this.isAttributeValueEquals(singleForm.getAttributeValue(),freight.getAttributeValue())
                            &&this.isProvinceEquals(singleForm.getProvinceId(),freight.getProvinceId())) {
                        flag = true;
                        freightId = freight.getFreightId();
                        break;
                    }
                }
            }

            if(!flag) {
                this.insertProductFreight(userId,singleForm);
            } else {
                OrangeProductFreight freight = new OrangeProductFreight();
                BeanUtils.copyProperties(singleForm,freight);
                freight.setByUpdate(userId);
                freight.setDateUpdate(new Date());
                freight.setFreightId(freightId);

                freightMapper.updateByPrimaryKeySelective(freight);
            }

        }



    }

    @Override
    public List<OrangeProductFreight> get(ProductFreightGetForm form) {
        OrangeProductFreightExample example = new OrangeProductFreightExample();
        example.createCriteria().andProductIdEqualTo(form.getProductId())
                .andDataStatusEqualTo(DataStatus.AVAILABLE.getValue());
        List<OrangeProductFreight> list = freightMapper.selectByExample(example);

        return list;
    }

    @Override
    public void delete(String productId) {
        OrangeProductFreightExample example = new OrangeProductFreightExample();
        example.createCriteria().andProductIdEqualTo(productId);
        OrangeProductFreight freight = new OrangeProductFreight();
        freight.setDataStatus(DataStatus.UNAVAILABLE.getValue());
        freight.setDateUpdate(new Date());
        freightMapper.updateByExampleSelective(freight,example);
    }


    private boolean isAttributeValueEquals(String attribute1,String attribute2) {
        return JSON.parseObject(attribute1).equals(JSON.parseObject(attribute2));
    }

    private boolean isProvinceEquals(String province1,String province2) {
        String[] arr1 = province1.split(",");
        String[] arr2 = province2.split(",");
        Set<String> s1 = new HashSet<String>();
        Set<String> s2 = new HashSet<String>();
        for(String s:arr1) {
            s1.add(s);
        }
        for(String s:arr2) {
            s2.add(s);
        }
        return s1.equals(s2);
    }

    private void insertProductFreight(String userId,ProductFreightForm form) {
        OrangeProductFreight freight = new OrangeProductFreight();
        BeanUtils.copyProperties(form,freight);

        this.insertProductFreight(userId,freight);
    }

    private void insertProductFreight(String userId,ProductFreightUpdateForm form) {
        OrangeProductFreight freight = new OrangeProductFreight();
        BeanUtils.copyProperties(form,freight);

        this.insertProductFreight(userId,freight);
    }

    private void insertProductFreight(String userId,OrangeProductFreight freight) {

        freight.setFreightId(snowFlake.getId());
        freight.setByCreate(userId);
        freight.setByUpdate(userId);
        freight.setDateCreate(new Date());
        freight.setDateUpdate(new Date());
        freight.setDataStatus(DataStatus.AVAILABLE.getValue());

        freightMapper.insertSelective(freight);
    }
}
