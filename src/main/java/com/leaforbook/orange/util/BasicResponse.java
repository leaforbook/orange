package com.leaforbook.orange.util;

import lombok.Data;

@Data
public class BasicResponse {
    private String code = "0";
    private String msg = "成功";

    public BasicResponse() {

    }

    public BasicResponse(String code,String msg) {
        this.code = code;
        this.msg = msg;
    }
}
