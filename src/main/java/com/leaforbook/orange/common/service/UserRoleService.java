package com.leaforbook.orange.common.service;

import org.springframework.stereotype.Service;

@Service
public interface UserRoleService {
    void create(String userId,String roleId);
    void remove(String roleId);
    boolean hasRole(String userId,String roleId);
}
