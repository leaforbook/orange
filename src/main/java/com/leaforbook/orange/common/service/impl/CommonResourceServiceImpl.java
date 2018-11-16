package com.leaforbook.orange.common.service.impl;

import com.leaforbook.orange.common.dao.mapper.CommonResourceMapper;
import com.leaforbook.orange.common.dao.model.CommonResource;
import com.leaforbook.orange.common.dao.model.CommonResourceExample;
import com.leaforbook.orange.common.service.CommonResourceService;
import com.leaforbook.orange.util.DataStatus;
import com.leaforbook.orange.util.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CommonResourceServiceImpl implements CommonResourceService {

    @Autowired
    private CommonResourceMapper commonResourceMapper;

    @Autowired
    private SnowFlake snowFlake;

    @Override
    public void insert(CommonResource resource) {
        resource.setId(snowFlake.getId());
        resource.setByCreate("SYS");
        resource.setByUpdate("SYS");
        resource.setDateCreate(new Date());
        resource.setDateUpdate(new Date());
        resource.setDataStatus(DataStatus.AVAILABLE.getValue());

        commonResourceMapper.insertSelective(resource);
    }

    @Override
    public void delete(CommonResource resource) {

    }

    @Override
    public void deleteResource(String resourceId) {
        CommonResourceExample example = new CommonResourceExample();
        example.createCriteria().andResourceIdEqualTo(resourceId);
        CommonResource resource = new CommonResource();
        resource.setDataStatus(DataStatus.UNAVAILABLE.getValue());
        resource.setDateUpdate(new Date());
        commonResourceMapper.updateByExampleSelective(resource,example);
    }

    @Override
    public void update(CommonResource resource) {

    }

    @Override
    public boolean hasResource(String userId, String resourceType, String resourceId) {
        CommonResourceExample example = new CommonResourceExample();
        example.createCriteria().andResourceIdEqualTo(resourceId)
                .andUserIdEqualTo(userId)
                .andResourceTypeEqualTo(resourceType)
                .andDataStatusEqualTo(DataStatus.AVAILABLE.getValue());
        long count = commonResourceMapper.countByExample(example);
        if(count>0) {
            return true;
        }
        return false;
    }

    @Override
    public void removeResource(String userId, String resourceId) {
        CommonResourceExample example = new CommonResourceExample();
        example.createCriteria().andResourceIdEqualTo(resourceId).andUserIdEqualTo(userId);
        CommonResource resource = new CommonResource();
        resource.setDataStatus(DataStatus.UNAVAILABLE.getValue());
        resource.setDateUpdate(new Date());
        commonResourceMapper.updateByExampleSelective(resource,example);
    }

    @Override
    public List<CommonResource> select(String userId, String resourceType) {
        CommonResourceExample example = new CommonResourceExample();
        example.createCriteria().andUserIdEqualTo(userId)
                .andResourceTypeEqualTo(resourceType)
                .andDataStatusEqualTo(DataStatus.AVAILABLE.getValue());

        return commonResourceMapper.selectByExample(example);
    }
}
