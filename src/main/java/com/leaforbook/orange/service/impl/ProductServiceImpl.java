package com.leaforbook.orange.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leaforbook.orange.common.dao.model.CommonResource;
import com.leaforbook.orange.common.service.CommonResourceService;
import com.leaforbook.orange.common.service.UserService;
import com.leaforbook.orange.controller.form.ProductForm;
import com.leaforbook.orange.controller.form.ProductQueryForm;
import com.leaforbook.orange.controller.form.ProductUpadateForm;
import com.leaforbook.orange.dao.mapper.OrangeProductExtendMapper;
import com.leaforbook.orange.dao.mapper.OrangeProductMapper;
import com.leaforbook.orange.dao.model.OrangeProduct;
import com.leaforbook.orange.dao.model.OrangeProductExample;
import com.leaforbook.orange.dao.model.TmpTable;
import com.leaforbook.orange.service.ProductFreightService;
import com.leaforbook.orange.service.ProductPriceService;
import com.leaforbook.orange.util.ResourceEnum;
import com.leaforbook.orange.service.ProductService;
import com.leaforbook.orange.util.*;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private SnowFlake snowFlake;

    @Autowired
    private OrangeProductMapper productMapper;

    @Autowired
    private OrangeProductExtendMapper productExtendMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private CommonResourceService commonResourceService;

    @Autowired
    private ProductFreightService productFreightService;

    @Autowired
    private ProductPriceService productPriceService;

    @Override
    public void create(String userId, ProductForm form) {
        OrangeProduct product = new OrangeProduct();
        BeanUtils.copyProperties(form,product);
        product.setUserId(userId);
        String productId = snowFlake.getId();
        product.setProductId(productId);
        productMapper.insertSelective(product);

        this.setResource(productId,userId, ResourceEnum.PRODUCT_CREATE.getResourceType());
        this.setResource(productId,userId, ResourceEnum.PRODUCT_USE.getResourceType());
    }

    @Override
    @Transactional
    public void update(String userId,ProductUpadateForm form) {

        OrangeProduct product = new OrangeProduct();
        BeanUtils.copyProperties(form,product);
        OrangeProductExample example = new OrangeProductExample();
        example.createCriteria().andUserIdEqualTo(userId).andProductIdEqualTo(form.getProductId());

        OrangeProduct productInDB = productMapper.selectByPrimaryKey(form.getProductId());

        int count = productMapper.updateByExampleSelective(product,example);
        if(count<=0) {
            throw new BasicBusinessException(ExceptionEnum.UPDATE_FAILURE);
        }

        if(StringUtils.isNotEmpty(form.getFreightAttribute())
                &&this.isAttributeEquals(form.getFreightAttribute(),productInDB.getFreightAttribute())) {
            productFreightService.delete(form.getProductId());
        }

        if(StringUtils.isNotEmpty(form.getPriceAttribute())
                &&this.isAttributeEquals(form.getPriceAttribute(),productInDB.getPriceAttribute())) {
            productPriceService.delete(form.getProductId());
        }
    }

    private boolean isAttributeEquals(String attribute1,String attribute2) {
        return JSON.parseObject(attribute1).equals(JSON.parseObject(attribute2));
    }

    @Override
    public OrangeProduct detail(String productId) {

        OrangeProductExample example = new OrangeProductExample();
        example.createCriteria().andProductIdEqualTo(productId).andDataStatusEqualTo(DataStatus.AVAILABLE.getValue());
        List<OrangeProduct> list = productMapper.selectByExampleWithBLOBs(example);
        if(list==null||list.size()==0) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public void delete(String userId,String productId) {

        OrangeProduct product = new OrangeProduct();
        product.setDataStatus(DataStatus.UNAVAILABLE.getValue());
        OrangeProductExample example = new OrangeProductExample();
        example.createCriteria().andUserIdEqualTo(userId).andProductIdEqualTo(productId);
        int count = productMapper.updateByExampleSelective(product,example);
        if(count==0) {
            throw new BasicBusinessException(ExceptionEnum.DELETE_FAILURE);
        }

        this.deleteResource(productId);

        productFreightService.delete(productId);
        productPriceService.delete(productId);
    }

    @Override
    public void remove(String userId, String productId) {
        this.removeResource(userId,productId);
    }

    @Override
    public Page<OrangeProduct> query(String userId,ProductQueryForm form) {
        List<CommonResource> resources = commonResourceService.select(userId,ResourceEnum.PRODUCT_USE.getResourceType());
        String tableName = "T"+snowFlake.getId();
        TmpTable table = new TmpTable();
        table.setTableName(tableName);
        productExtendMapper.createTmpTable(table);

        for(CommonResource resource:resources) {
            table.setId(resource.getResourceId());
            productExtendMapper.insertTmpTable(table);
        }


        PageHelper.offsetPage(form.getPageNum()-1,form.getPageSize());
        OrangeProduct params = new OrangeProduct();
        params.setProductId(form.getProductId());
        params.setProductName(form.getProductName());
        params.setTableName(tableName);
        Page<OrangeProduct> result = (Page<OrangeProduct>)productExtendMapper.query(params);

        productExtendMapper.dropTmpTable(table);

        return result;
    }

    @Override
    public void share(String productId, String userName) {
        String[] userArr = userName.split(";");

        for(String singleUserName:userArr) {
            UserInfo user = userService.getUserByName(singleUserName);
            this.setResource(productId,user.getUserId(), ResourceEnum.PRODUCT_USE.getResourceType());
        }

    }

    private void setResource(String productId,String userId,String resourceType) {
        CommonResource resource = new CommonResource();
        resource.setUserId(userId);
        resource.setResourceType(resourceType);
        resource.setResourceId(productId);
        commonResourceService.insert(resource);
    }

    private void deleteResource(String productId) {
        commonResourceService.deleteResource(productId);
    }

    private void removeResource(String userId, String productId) {
        commonResourceService.removeResource(userId,productId);
    }

}
