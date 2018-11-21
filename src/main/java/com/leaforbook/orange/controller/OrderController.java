package com.leaforbook.orange.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orange/order")
@Slf4j
@Api(value = "订单信息维护", description = "订单信息维护")
public class OrderController {

}
