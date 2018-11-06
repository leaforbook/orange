package com.leaforbook.orange.util;

public enum ExceptionEnum {
    UNLOGIN("000001","用户未登录"),
    GET_USRINFO_FAILURE("000002","服务器异常"),
    LOGIN_EXPIRE("000003","登录已失效"),
    INVITATION_INVALID("000004","邀请码无效"),
    USERNAME_USED("000005","用户名被占用"),
    PASSWORD_DIFFER("000006","两次密码输入不一致"),
    LOGIN_AGAIN("000007","不能多处登录"),
    USER_NON_EXISTENT("000008","用户不存在"),
    PASSWORD_WRONG("000009","密码错误"),
    NOT_XIAOYILIN("000010","不可以，你不是肖一林"),
    PROOF_WRONG("000011","修改密码凭据错误"),
    UPDATE_FAILURE("000012","未更新数据"),
    DELETE_FAILURE("000013","未删除数据"),
    ;

    private String code;
    private String message;

    ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
