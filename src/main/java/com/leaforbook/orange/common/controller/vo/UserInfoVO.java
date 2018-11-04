package com.leaforbook.orange.common.controller.vo;

import com.leaforbook.orange.util.BasicResponse;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoVO implements Serializable {
    private String userId;

    private String userName;

    private String realName;

    private String telephone;
}
