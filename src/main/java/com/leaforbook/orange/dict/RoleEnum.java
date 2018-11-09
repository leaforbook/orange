package com.leaforbook.orange.dict;

public enum RoleEnum {

    PRODUCT_CREATE("PRC_","_产品创建者"),
    PRODUCT_USE("PRU_","_产品使用者"),
    ;

    private String roleName;
    private String roleDesc;

    RoleEnum(String roleName,String roleDesc) {
        this.roleDesc = roleDesc;
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
}
