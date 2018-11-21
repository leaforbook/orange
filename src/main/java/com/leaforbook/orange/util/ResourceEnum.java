package com.leaforbook.orange.util;

public enum ResourceEnum {

    PRODUCT_CREATE("PRC","_产品创建者"),
    PRODUCT_USE("PRU","_产品使用者"),
    CUSTOMER_ADDRESS_CREATE("CAC","_客户收货地址创建者"),
    ;

    private String resourceType;
    private String resourceDesc;

    ResourceEnum(String resourceType, String resourceDesc) {
        this.resourceDesc = resourceDesc;
        this.resourceType = resourceType;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceDesc() {
        return resourceDesc;
    }

    public void setResourceDesc(String resourceDesc) {
        this.resourceDesc = resourceDesc;
    }
}
