package com.leaforbook.orange.controller;

import com.leaforbook.orange.controller.form.*;
import com.leaforbook.orange.dao.model.OrangeProductFreight;
import com.leaforbook.orange.service.ProductFreightService;
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
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/orange/freight")
@Slf4j
@Api(value = "运费信息维护", description = "运费信息维护")
public class ProductFreightController {

    @Autowired
    private ProductFreightService productFreightService;

    @PostMapping("/create")
    @ApiOperation(value = "创建产品的运费信息", notes = "")
    @HasResource(resourceType = "PRC",resourceId = "productId")
    public BasicResponse create(@RequestBody @Valid ProductFreightListForm form,
                                @Session UserInfo userInfo) {

        productFreightService.create(userInfo.getUserId(),form);

        return new BasicResponse();
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新产品的运费信息", notes = "")
    @HasResource(resourceType = "PRC",resourceId = "productId")
    public BasicResponse update(@RequestBody @Valid ProductFreightUpdateListForm form,
                                @Session UserInfo userInfo) {

        productFreightService.update(userInfo.getUserId(),form);

        return new BasicResponse();
    }

    @PostMapping("/get")
    @ApiOperation(value = "获取产品的运费信息", notes = "")
    @HasResource(resourceType = "PRU",resourceId = "productId")
    public BasicResponse get(@RequestBody @Valid ProductFreightGetForm form) {

        List<OrangeProductFreight> freightList = productFreightService.get(form);

        return new BasicResponse(freightList);
    }


}
