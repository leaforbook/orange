package com.leaforbook.orange.common.controller;

import com.leaforbook.orange.common.auth.UserInfo;
import com.leaforbook.orange.common.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/common/user")
@Slf4j
@Api(value = "用户信息维护", description = "用户信息维护")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/get")
    @ApiOperation(value = "获取用户", notes = "")
    public UserInfo getUserInfo(HttpServletRequest request) {
        return userService.getUserInfo(request);
    }

}
