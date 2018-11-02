package com.leaforbook.orange.common.auth;

import com.leaforbook.orange.util.BasicResponse;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo extends BasicResponse implements Serializable {
    private String userId;

    private String userName;

    private String realName;

    private String telephone;
}
