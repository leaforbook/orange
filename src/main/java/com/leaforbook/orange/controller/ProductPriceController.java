package com.leaforbook.orange.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orange/price")
@Slf4j
@Api(value = "价格信息维护", description = "价格信息维护")
public class ProductPriceController {
}
