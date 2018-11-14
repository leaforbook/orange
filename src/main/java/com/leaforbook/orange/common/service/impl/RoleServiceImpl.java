package com.leaforbook.orange.common.service.impl;

import com.leaforbook.orange.common.dao.mapper.CommonRoleMapper;
import com.leaforbook.orange.common.dao.model.CommonRole;
import com.leaforbook.orange.common.service.RoleService;
import com.leaforbook.orange.util.DataStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private CommonRoleMapper roleMapper;

    @Override
    public void create(String roleId, String roleDesc) {
        CommonRole role = roleMapper.selectByPrimaryKey(roleId);

        CommonRole record = new CommonRole();
        record.setRoleId(roleId);
        record.setRoleName(roleId);
        record.setRoleDesc(roleDesc);
        record.setRoleStatus(DataStatus.AVAILABLE.getValue());

        if(role!=null) {
            roleMapper.updateByPrimaryKeySelective(record);
        } else {
            roleMapper.insert(record);
        }

    }

    @Override
    public CommonRole get(String roleId) {
        CommonRole role = roleMapper.selectByPrimaryKey(roleId);
        return role;
    }

    @Override
    public void remove(String roleId) {
        CommonRole role = new CommonRole();
        role.setRoleId(roleId);
        role.setRoleStatus(DataStatus.UNAVAILABLE.getValue());
        role.setDateUpdate(new Date());
        roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public void update(CommonRole role) {
        roleMapper.updateByPrimaryKeySelective(role);
    }
}
