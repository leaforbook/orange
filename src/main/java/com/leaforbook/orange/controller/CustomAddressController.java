package com.leaforbook.orange.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orange/address")
@Slf4j
@Api(value = "客户地址信息维护", description = "客户地址信息维护")
public class CustomAddressController {
}
