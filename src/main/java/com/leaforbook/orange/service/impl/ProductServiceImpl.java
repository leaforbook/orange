package com.leaforbook.orange.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leaforbook.orange.common.dao.model.CommonResource;
import com.leaforbook.orange.common.service.CommonResourceService;
import com.leaforbook.orange.common.service.UserService;
import com.leaforbook.orange.controller.form.*;
import com.leaforbook.orange.dao.mapper.OrangeProductExtendMapper;
import com.leaforbook.orange.dao.mapper.OrangeProductFreightMapper;
import com.leaforbook.orange.dao.mapper.OrangeProductMapper;
import com.leaforbook.orange.dao.mapper.OrangeProductPriceMapper;
import com.leaforbook.orange.dao.model.*;
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

import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private OrangeProductPriceMapper productPriceMapper;

    @Autowired
    private OrangeProductFreightMapper productFreightMapper;

    @Override
    @Transactional
    public String create(String userId, ProductForm form) {
        OrangeProduct product = new OrangeProduct();
        BeanUtils.copyProperties(form,product);
        product.setUserId(userId);
        String productId = snowFlake.getId();
        product.setProductId(productId);
        productMapper.insertSelective(product);

        this.setResource(productId,userId, ResourceEnum.PRODUCT_CREATE.getResourceType());
        this.setResource(productId,userId, ResourceEnum.PRODUCT_USE.getResourceType());

        this.createPriceTable(form.getPriceAttribute(),productId,userId);
        this.createFreightTable(form.getFreightAttribute(),productId,userId);


        return productId;

    }

    @Override
    @Transactional
    public String copy(String userId, ProductCopyForm form) {

        String productId = form.getProductId();
        String newProductId = snowFlake.getId();
        OrangeProduct product = productMapper.selectByPrimaryKey(productId);
        ProductPriceGetForm priceGetForm = new ProductPriceGetForm();
        priceGetForm.setAll(true);
        priceGetForm.setProductId(productId);
        List<OrangeProductPrice> priceList = productPriceService.get(priceGetForm);
        ProductFreightGetForm freightGetForm = new ProductFreightGetForm();
        freightGetForm.setProductId(productId);
        List<OrangeProductFreight> freightList = productFreightService.get(freightGetForm);

        OrangeProduct newProduct = new OrangeProduct();
        BeanUtils.copyProperties(product,newProduct);
        newProduct.setProductId(newProductId);
        newProduct.setUserId(userId);
        newProduct.setByCreate(userId);
        newProduct.setDateCreate(new Date());
        newProduct.setByUpdate(userId);
        newProduct.setDateUpdate(new Date());
        newProduct.setDataStatus(DataStatus.AVAILABLE.getValue());
        productMapper.insertSelective(newProduct);

        for(OrangeProductPrice price:priceList) {
            String newPriceId = snowFlake.getId();
            price.setProductId(newProductId);
            price.setDateUpdate(new Date());
            price.setDateCreate(new Date());
            price.setByUpdate(userId);
            price.setByCreate(userId);
            price.setPriceId(newPriceId);
            productPriceMapper.insertSelective(price);
        }

        for(OrangeProductFreight freight:freightList) {
            String newFreightId = snowFlake.getId();
            freight.setProductId(newProductId);
            freight.setDateUpdate(new Date());
            freight.setDateCreate(new Date());
            freight.setByUpdate(userId);
            freight.setByCreate(userId);
            freight.setFreightId(newFreightId);
            productFreightMapper.insertSelective(freight);
        }

        this.setResource(newProductId,userId, ResourceEnum.PRODUCT_CREATE.getResourceType());
        this.setResource(newProductId,userId, ResourceEnum.PRODUCT_USE.getResourceType());
        this.removeResource(userId,productId);

        return newProductId;
    }

    private void createPriceTable(String priceAttribute,String productId,String userId) {
        List<String> valueGroup = this.getValueGroup(priceAttribute);
        ProductPriceListForm listForm = new ProductPriceListForm();
        List<ProductPriceForm> list = new ArrayList<>();

        for(String attribute:valueGroup) {
            ProductPriceForm form = new ProductPriceForm();
            form.setSetOrNot(true);
            form.setAttributeValue(attribute);
            form.setProductId(productId);
            list.add(form);
            listForm.setList(list);
            productPriceService.create(userId,listForm);
        }
    }

    private void createFreightTable(String freightAttribute,String productId,String userId) {
        List<String> valueGroup = this.getValueGroup(freightAttribute);
        ProductFreightListForm listForm = new ProductFreightListForm();
        List<ProductFreightForm> list = new ArrayList<>();

        for(String attribute:valueGroup) {
            ProductFreightForm form = new ProductFreightForm();
            form.setSetOrNot(true);
            form.setAttributeValue(attribute);
            form.setProductId(productId);
            list.add(form);
            listForm.setList(list);
            productFreightService.create(userId,listForm);
        }
    }


    private List<String> getValueGroup(String attribute) {
        JSONArray arr = JSON.parseArray(attribute);

        List<List<String>> valueList = new ArrayList<>();

        try {
            for(int i=0;i<arr.size();i++) {
                Object obj =  arr.get(i);
                if(obj==null) {
                    continue;
                }

                JSONObject jsonObject = JSON.parseObject(obj.toString());
                Object value = jsonObject.get("value");
                if(value==null) {
                    continue;
                }

                JSONArray jsonValue = JSON.parseArray(value.toString());
                List<String> values = new ArrayList<String>();
                for(int j=0;j<jsonValue.size();j++) {
                    if(jsonValue.get(j)!=null) {
                        values.add(jsonValue.get(j).toString());
                    }
                }

                if(values.size()>0) {
                    valueList.add(values);
                }
            }
        }catch (Throwable e) {
            log.error("属性json解析错误",e);
        }

        List<String> valueGroup = new ArrayList<>();


        if(valueList.size()==0) {
            valueGroup.add("统一价");
        } else {
            for(int i=0;i<valueList.size();i++) {
                List<String> values = valueList.get(i);
                List<String> valueGroupTemp = new ArrayList<>();
                for(int j=0;j<values.size();j++) {

                    if(valueGroup.size()==0) {

                        valueGroupTemp.add(values.get(j));
                    } else {
                        for(int k=0;k<valueGroup.size();k++) {
                            String temp = valueGroup.get(k) +"*"+values.get(j);
                            valueGroupTemp.add(temp);
                        }

                    }
                }
                valueGroup = valueGroupTemp;
            }
        }

        log.info("getValueGroup"+JSON.toJSONString(valueGroup));

        return valueGroup;
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

        if(!(StringUtils.isNotEmpty(form.getFreightAttribute())
                &&this.isAttributeEquals(form.getFreightAttribute(),productInDB.getFreightAttribute()))) {
            productFreightService.delete(form.getProductId());
            this.createFreightTable(form.getFreightAttribute(),form.getProductId(),userId);
        }

        if(!(StringUtils.isNotEmpty(form.getPriceAttribute())
                &&this.isAttributeEquals(form.getPriceAttribute(),productInDB.getPriceAttribute()))) {
            productPriceService.delete(form.getProductId());

            this.createPriceTable(form.getPriceAttribute(),form.getProductId(),userId);
        }
    }

    private boolean isAttributeEquals(String attribute1,String attribute2) {
        return JSON.parseArray(attribute1).equals(JSON.parseArray(attribute2));
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


        PageHelper.offsetPage((form.getPageNum()-1)*form.getPageSize(),form.getPageSize());
        OrangeProduct params = new OrangeProduct();
        params.setProductId(form.getProductId());
        params.setProductName(form.getProductName());
        params.setTableName(tableName);
        Page<OrangeProduct> result = (Page<OrangeProduct>)productExtendMapper.query(params);

        productExtendMapper.dropTmpTable(table);

        return result;
    }

    @Override
    public String share(String productId, String userName) {
        String[] userArr = userName.split(";");

        String result = "";
        for(String singleUserName:userArr) {
            UserInfo user = userService.getUserByName(singleUserName);
            if(user==null) {
                result += "("+singleUserName+" 不存在);";
                continue;
            }
            boolean flag = this.setResource(productId,user.getUserId(), ResourceEnum.PRODUCT_USE.getResourceType());

            if(!flag) {
                result += "("+singleUserName+" 已授权);";
            }
        }

        if(StringUtils.isNotEmpty(result)) {
            result += "——用户名不存在的授权不会成功，已授权过的授权也不会成功，注意用户名之间用半角分号隔开";
        } else {
            result = "全部授权成功！";
        }


        return result;
    }

    @Override
    public boolean isCreater(String productId,String userId) {
        return commonResourceService.hasResource(userId,ResourceEnum.PRODUCT_CREATE.getResourceType(),productId);
    }

    private boolean setResource(String productId,String userId,String resourceType) {
        CommonResource resource = new CommonResource();
        resource.setUserId(userId);
        resource.setResourceType(resourceType);
        resource.setResourceId(productId);

        boolean flag = commonResourceService.hasResource(userId,resourceType,productId);
        if(!flag) {
            commonResourceService.insert(resource);
            return true;
        }

        return false;
    }

    private void deleteResource(String productId) {
        commonResourceService.deleteResource(productId);
    }

    private void removeResource(String userId, String productId) {
        commonResourceService.removeResource(userId,productId);
    }

}
