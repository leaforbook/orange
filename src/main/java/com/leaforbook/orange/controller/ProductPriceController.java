package com.leaforbook.orange.controller;

import com.leaforbook.orange.controller.form.ProductPriceGetForm;
import com.leaforbook.orange.controller.form.ProductPriceListForm;
import com.leaforbook.orange.controller.form.ProductPriceUpdateListForm;
import com.leaforbook.orange.dao.model.OrangeProductPrice;
import com.leaforbook.orange.service.ProductPriceService;
import com.leaforbook.orange.util.BasicResponse;
import com.leaforbook.orange.util.HasResource;
import com.leaforbook.orange.util.Session;
import com.leaforbook.orange.util.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orange/price")
@Slf4j
@Api(value = "价格信息维护", description = "价格信息维护")
public class ProductPriceController {

    @Autowired
    private ProductPriceService productPriceService;

    @PostMapping("/create")
    @ApiOperation(value = "创建产品的价格信息", notes = "")
    @HasResource(resourceType = "PRC",resourceId = "productId")
    public BasicResponse create(@RequestBody @Valid ProductPriceListForm form,
                                @Session UserInfo userInfo) {

        productPriceService.create(userInfo.getUserId(),form);

        return new BasicResponse();
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新产品的价格信息", notes = "")
    @HasResource(resourceType = "PRC",resourceId = "productId")
    public BasicResponse update(@RequestBody @Valid ProductPriceUpdateListForm form,
                                @Session UserInfo userInfo) {

        productPriceService.update(userInfo.getUserId(),form);

        return new BasicResponse();
    }


    @PostMapping("/get")
    @ApiOperation(value = "获取产品的运费信息", notes = "")
    @HasResource(resourceType = "PRU",resourceId = "productId")
    public BasicResponse get(@RequestBody @Valid ProductPriceGetForm form) {

        List<OrangeProductPrice> freightList = productPriceService.get(form);

        return new BasicResponse(freightList);
    }

}
