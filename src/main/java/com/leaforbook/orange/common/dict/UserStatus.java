package com.leaforbook.orange.common.dict;

public enum UserStatus {

    NORMAL("正常","1"),FROZEN("冻结","2");

    private String key;
    private String value;
    UserStatus(String key,String value) {
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
