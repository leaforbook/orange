package com.leaforbook.orange.util;

public enum DataStatus {
    AVAILABLE("可用","1"),UNAVAILABLE("不可用","2");

    private String key;
    private String value;
    DataStatus(String key,String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
