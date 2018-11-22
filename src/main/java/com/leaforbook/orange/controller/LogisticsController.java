package com.leaforbook.orange.controller;

import com.alibaba.fastjson.JSONObject;
import com.leaforbook.orange.controller.form.LogisticsForm;
import com.leaforbook.orange.controller.form.LogisticsSingleForm;
import com.leaforbook.orange.controller.form.LogisticsUpdateForm;
import com.leaforbook.orange.service.LogisticsService;
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

@RestController
@RequestMapping("/orange/logistics")
@Slf4j
@Api(value = "物流信息维护", description = "物流信息维护")
public class LogisticsController {

    @Autowired
    private LogisticsService logisticsService;

    @PostMapping("/create")
    @ApiOperation(value = "增加订单的物流信息", notes = "")
    public BasicResponse create(@RequestBody @Valid LogisticsForm form,
                                @Session UserInfo userInfo) {

        logisticsService.create(userInfo.getUserId(),form);

        return new BasicResponse();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改订单的物流信息", notes = "")
    @HasResource(resourceType = "ORC",resourceId = "orderId")
    public BasicResponse update(@RequestBody @Valid LogisticsUpdateForm form,
                                @Session UserInfo userInfo) {

        logisticsService.update(form);

        return new BasicResponse();
    }

    @PostMapping("/get")
    @ApiOperation(value = "获取订单的物流信息", notes = "")
    @HasResource(resourceType = "ORC",resourceId = "orderId")
    public BasicResponse get(LogisticsSingleForm form) {
        JSONObject obj = logisticsService.get(form);
        return new BasicResponse(obj);
    }

}
