package com.leaforbook.orange.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leaforbook.orange.common.service.RoleService;
import com.leaforbook.orange.common.service.UserRoleService;
import com.leaforbook.orange.controller.form.ProductForm;
import com.leaforbook.orange.controller.form.ProductQueryForm;
import com.leaforbook.orange.controller.form.ProductUpadateForm;
import com.leaforbook.orange.dao.mapper.OrangeProductExtendMapper;
import com.leaforbook.orange.dao.mapper.OrangeProductMapper;
import com.leaforbook.orange.dao.model.OrangeProduct;
import com.leaforbook.orange.dao.model.OrangeProductExample;
import com.leaforbook.orange.dict.RoleEnum;
import com.leaforbook.orange.service.ProductService;
import com.leaforbook.orange.util.BasicBusinessException;
import com.leaforbook.orange.util.DataStatus;
import com.leaforbook.orange.util.ExceptionEnum;
import com.leaforbook.orange.util.SnowFlake;
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
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public void create(String userId, ProductForm form) {
        OrangeProduct product = new OrangeProduct();
        BeanUtils.copyProperties(form,product);
        product.setUserId(userId);
        String productId = snowFlake.getId();
        product.setProductId(productId);
        productMapper.insertSelective(product);

        this.setRole(productId,userId);
    }

    @Override
    public void update(String userId,ProductUpadateForm form) {

        OrangeProduct product = new OrangeProduct();
        BeanUtils.copyProperties(form,product);
        OrangeProductExample example = new OrangeProductExample();
        example.createCriteria().andUserIdEqualTo(userId).andProductIdEqualTo(form.getProductId());
        int count = productMapper.updateByExampleWithBLOBs(product,example);
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
    public void remove(String userId,String productId) {

        OrangeProduct product = new OrangeProduct();
        product.setDataStatus(DataStatus.UNAVAILABLE.getValue());
        OrangeProductExample example = new OrangeProductExample();
        example.createCriteria().andUserIdEqualTo(userId).andProductIdEqualTo(productId);
        int count = productMapper.updateByExampleWithBLOBs(product,example);
        if(count==0) {
            throw new BasicBusinessException(ExceptionEnum.DELETE_FAILURE);
        }

        this.deleteRole(productId);
    }

    @Override
    public PageInfo<OrangeProduct> query(String userId,ProductQueryForm form) {
        PageHelper.offsetPage(form.getPageNum(),form.getPageSize());
        OrangeProduct params = new OrangeProduct();
        params.setUserId(userId);
        params.setProductId(form.getProductId());
        params.setProductName(form.getProductName());
        PageInfo<OrangeProduct> datas = (PageInfo<OrangeProduct>)productExtendMapper.query(params);
        return datas;
    }

    private void setRole(String productId,String userId) {
        roleService.create(RoleEnum.PRODUCT_CREATE.getRoleName()+productId
                ,productId+RoleEnum.PRODUCT_CREATE.getRoleDesc());
        roleService.create(RoleEnum.PRODUCT_USE.getRoleName()+productId
                ,productId+RoleEnum.PRODUCT_USE.getRoleDesc());

        userRoleService.create(userId,RoleEnum.PRODUCT_CREATE.getRoleName()+productId);
        userRoleService.create(userId,RoleEnum.PRODUCT_USE.getRoleName()+productId);
    }

    private void deleteRole(String productId) {
        roleService.remove(RoleEnum.PRODUCT_CREATE.getRoleName()+productId);
        roleService.remove(RoleEnum.PRODUCT_USE.getRoleName()+productId);

        userRoleService.remove(RoleEnum.PRODUCT_CREATE.getRoleName()+productId);
        userRoleService.remove(RoleEnum.PRODUCT_USE.getRoleName()+productId);
    }

}
