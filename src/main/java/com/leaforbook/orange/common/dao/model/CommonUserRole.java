package com.leaforbook.orange.common.dao.model;

import com.leaforbook.orange.util.BasicModel;

import java.util.Date;

public class CommonUserRole  extends BasicModel {
    private String userId;

    private String roleId;

    private String userRoleStatus;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserRoleStatus() {
        return userRoleStatus;
    }

    public void setUserRoleStatus(String userRoleStatus) {
        this.userRoleStatus = userRoleStatus;
    }

}