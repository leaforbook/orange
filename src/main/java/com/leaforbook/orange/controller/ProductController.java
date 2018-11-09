package com.leaforbook.orange.controller;

import com.github.pagehelper.PageInfo;
import com.leaforbook.orange.controller.form.ProductForm;
import com.leaforbook.orange.controller.form.ProductIdForm;
import com.leaforbook.orange.controller.form.ProductQueryForm;
import com.leaforbook.orange.controller.form.ProductUpadateForm;
import com.leaforbook.orange.dao.model.OrangeProduct;
import com.leaforbook.orange.service.ProductService;
import com.leaforbook.orange.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/orange/product")
@Slf4j
@Api(value = "产品信息维护", description = "产品信息维护")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    @ApiOperation(value = "创建产品信息", notes = "")
    public BasicResponse create(@RequestBody @Valid ProductForm form,
                                @Session UserInfo userInfo) {
        productService.create(userInfo.getUserId(),form);

        return new BasicResponse();
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新产品信息", notes = "")
    public BasicResponse update(@RequestBody @Valid ProductUpadateForm form,
                                @Session UserInfo userInfo) {

        productService.update(userInfo.getUserId(),form);

        return new BasicResponse();
    }

    @PostMapping("/query")
    @ApiOperation(value = "查询产品列表信息", notes = "")
    public BasicResponse query(@RequestBody @Valid ProductQueryForm form,
                               @Session UserInfo userInfo) {

        PageInfo<OrangeProduct> data =  productService.query(userInfo.getUserId(),form);

        return new BasicResponse(data);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "查询产品详情信息", notes = "")
    public BasicResponse detail(@RequestBody @Valid ProductIdForm form,
                                @Session UserInfo userInfo) {

        OrangeProduct product = productService.detail(userInfo.getUserId(),form.getProductId());

        return new BasicResponse(product);
    }

    @PostMapping("/remove")
    @ApiOperation(value = "移除产品信息", notes = "")
    public BasicResponse remove(@RequestBody @Valid ProductIdForm form,
                                @Session UserInfo userInfo) {

        productService.remove(userInfo.getUserId(),form.getProductId());

        return new BasicResponse();
    }
}