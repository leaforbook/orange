package com.leaforbook.orange.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import com.leaforbook.orange.util.ResourceEnum;
import com.leaforbook.orange.service.ProductService;
import com.leaforbook.orange.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void update(String userId,ProductUpadateForm form) {

        OrangeProduct product = new OrangeProduct();
        BeanUtils.copyProperties(form,product);
        OrangeProductExample example = new OrangeProductExample();
        example.createCriteria().andUserIdEqualTo(userId).andProductIdEqualTo(form.getProductId());
        int count = productMapper.updateByExampleSelective(product,example);
        if(count==0) {
            throw new BasicBusinessException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    @Override
    public OrangeProduct detail(String userId,String productId) {

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
    }

    @Override
    public void remove(String userId, String productId) {
        this.removeResource(userId,productId);
    }

    @Override
    public PageInfo<OrangeProduct> query(String userId,ProductQueryForm form) {
        PageHelper.offsetPage(form.getPageNum(),form.getPageSize());
        OrangeProduct params = new OrangeProduct();
        params.setUserId(userId);
        params.setProductId(form.getProductId());
        params.setProductName(form.getProductName());
        return (PageInfo<OrangeProduct>)productExtendMapper.query(params);
    }

    @Override
    public void share(String productId, String userName) {
        UserInfo user = userService.getUserByName(userName);
        this.setResource(productId,user.getUserId(), ResourceEnum.PRODUCT_USE.getResourceType());
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
