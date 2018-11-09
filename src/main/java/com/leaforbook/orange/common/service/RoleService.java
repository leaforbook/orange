package com.leaforbook.orange.common.service;

import com.leaforbook.orange.common.dao.model.CommonRole;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    void create(String roleId,String roleDesc);
    CommonRole get(String roleId);
    void remove(String roleId);
}
