package com.leaforbook.orange.common.service.impl;

import com.leaforbook.orange.common.dao.mapper.CommonUserRoleMapper;
import com.leaforbook.orange.common.dao.model.CommonUserRole;
import com.leaforbook.orange.common.dao.model.CommonUserRoleExample;
import com.leaforbook.orange.common.service.UserRoleService;
import com.leaforbook.orange.util.DataStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private CommonUserRoleMapper userRoleMapper;

    @Override
    public void create(String userId, String roleId) {
        CommonUserRoleExample example = new CommonUserRoleExample();
        example.createCriteria().andRoleIdEqualTo(roleId).andUserIdEqualTo(userId);
        long count = userRoleMapper.countByExample(example);

        CommonUserRole userRole = new CommonUserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        userRole.setUserRoleStatus(DataStatus.AVAILABLE.getValue());

        if(count>0) {
            userRoleMapper.updateByPrimaryKeySelective(userRole);
        }else {
            userRoleMapper.insert(userRole);
        }
    }

    @Override
    public void remove(String roleId) {
        CommonUserRoleExample example = new CommonUserRoleExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        CommonUserRole userRole = new CommonUserRole();
        userRole.setUserRoleStatus(DataStatus.UNAVAILABLE.getValue());
        userRole.setDateUpdate(new Date());
        userRoleMapper.updateByExampleSelective(userRole,example);
    }

    @Override
    public boolean hasRole(String userId, String roleId) {
        CommonUserRoleExample example = new CommonUserRoleExample();
        example.createCriteria().andRoleIdEqualTo(roleId).andUserIdEqualTo(userId)
                .andUserRoleStatusEqualTo(DataStatus.AVAILABLE.getValue());
        long count = userRoleMapper.countByExample(example);
        if(count>0l) {
            return true;
        }
        return false;
    }
}
