package com.leaforbook.orange.util;

import lombok.Data;

@Data
public class BasicResponse {
    private String code = "0";
    private String msg = "成功";
    private Object data;

    public BasicResponse() {

    }

    public BasicResponse(String code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BasicResponse(String code,String msg,Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BasicResponse(Object data) {
        this.data = data;
    }
}
