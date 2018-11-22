package com.leaforbook.orange.dict;

public enum OrderStatus {
    NO_NEED_SEND("无需发货","1"),
    UN_SEND("未发货","2"),
    SENDING("已发货","3"),
    COMPLETED("已完成","4"),
    RETURNING("退货中","5"),
    RETURN_COMPLETED("退货完成","6"),
    ;

    private String key;
    private String value;
    OrderStatus(String key,String value) {
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
