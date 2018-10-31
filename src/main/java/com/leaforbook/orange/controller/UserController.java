package com.leaforbook.orange.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orange/user")
@Slf4j
@Api(value = "用户信息维护", description = "用户信息维护")
public class UserController {

    @PostMapping("/get")
    @ApiOperation(value = "获取用户", notes = "")
    public String getUser(@RequestBody User user) {
        System.out.println(user.getUsername());
        return user.getUsername();
    }

}

@Data
class User{
    String username;
}
