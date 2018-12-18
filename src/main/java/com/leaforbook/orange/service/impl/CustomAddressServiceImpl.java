package com.leaforbook.orange.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leaforbook.orange.common.dao.model.CommonResource;
import com.leaforbook.orange.common.service.CommonResourceService;
import com.leaforbook.orange.controller.form.CustomAddressIdForm;
import com.leaforbook.orange.controller.form.CustomAddressForm;
import com.leaforbook.orange.controller.form.CustomAddressQueryForm;
import com.leaforbook.orange.controller.form.CustomAddressUpdateForm;
import com.leaforbook.orange.dao.mapper.OrangeCustomAddressMapper;
import com.leaforbook.orange.dao.model.OrangeCustomAddress;
import com.leaforbook.orange.dao.model.OrangeCustomAddressExample;
import com.leaforbook.orange.service.CustomAddressService;
import com.leaforbook.orange.util.DataStatus;
import com.leaforbook.orange.util.ResourceEnum;
import com.leaforbook.orange.util.SnowFlake;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CustomAddressServiceImpl implements CustomAddressService {

    @Autowired
    private OrangeCustomAddressMapper addressMapper;

    @Autowired
    private SnowFlake snowFlake;

    @Autowired
    private CommonResourceService commonResourceService;

    @Override
    public String create(String userId, CustomAddressForm form) {
        OrangeCustomAddress address = new OrangeCustomAddress();
        BeanUtils.copyProperties(form,address);
        address.setUserId(userId);
        address.setByCreate(userId);
        address.setByUpdate(userId);
        address.setDateCreate(new Date());
        address.setDateUpdate(new Date());
        String addressId = snowFlake.getId();
        address.setAddressId(addressId);
        addressMapper.insertSelective(address);

        this.createResource(userId,addressId);

        return addressId;
    }

    private void createResource(String userId,String addressId) {
        CommonResource resource = new CommonResource();
        resource.setUserId(userId);
        resource.setResourceId(addressId);
        resource.setResourceType(ResourceEnum.CUSTOMER_ADDRESS_CREATE.getResourceType());
        commonResourceService.insert(resource);
    }

    @Override
    public void update(CustomAddressUpdateForm form) {
        OrangeCustomAddress address = new OrangeCustomAddress();
        BeanUtils.copyProperties(form,address);
        address.setDateUpdate(new Date());
        addressMapper.updateByPrimaryKeySelective(address);
    }

    @Override
    public void delete(CustomAddressIdForm form) {
        OrangeCustomAddressExample example = new OrangeCustomAddressExample();
        example.createCriteria().andAddressIdEqualTo(form.getAddressId());
        OrangeCustomAddress address = new OrangeCustomAddress();
        address.setDateUpdate(new Date());
        address.setDataStatus(DataStatus.UNAVAILABLE.getValue());
        addressMapper.updateByExampleSelective(address,example);

        this.delete(form.getAddressId());
    }

    private void delete(String addressId) {
        commonResourceService.deleteResource(addressId);
    }

    @Override
    public OrangeCustomAddress get(CustomAddressIdForm form) {
        OrangeCustomAddress address = addressMapper.selectByPrimaryKey(form.getAddressId());

        return address;
    }

    @Override
    public Page<OrangeCustomAddress> query(String userId, CustomAddressQueryForm form) {

        OrangeCustomAddressExample example = new OrangeCustomAddressExample();

        if(StringUtils.isNotEmpty(form.getQueryParams())) {
            example.or(example.createCriteria().andNameLike("%"+ form.getQueryParams() +"%").andUserIdEqualTo(userId).andDataStatusEqualTo(DataStatus.AVAILABLE.getValue()));
            example.or(example.createCriteria().andAddressLike("%"+ form.getQueryParams() +"%").andUserIdEqualTo(userId).andDataStatusEqualTo(DataStatus.AVAILABLE.getValue()));
            example.or(example.createCriteria().andTelephoneLike("%"+ form.getQueryParams() +"%").andUserIdEqualTo(userId).andDataStatusEqualTo(DataStatus.AVAILABLE.getValue()));
            example.or(example.createCriteria().andProvinceNameLike("%"+ form.getQueryParams() +"%").andUserIdEqualTo(userId).andDataStatusEqualTo(DataStatus.AVAILABLE.getValue()));
        } else {
            example.createCriteria().andUserIdEqualTo(userId).andDataStatusEqualTo(DataStatus.AVAILABLE.getValue());
        }

        example.setOrderByClause(" date_update desc ");

        PageHelper.offsetPage((form.getPageNum()-1)*form.getPageSize(),form.getPageSize());
        Page<OrangeCustomAddress> page = (Page<OrangeCustomAddress>)addressMapper.selectByExample(example);

        return page;
    }


}
