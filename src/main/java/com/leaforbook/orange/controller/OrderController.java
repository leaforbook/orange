package com.leaforbook.orange.controller;

import com.github.pagehelper.Page;
import com.leaforbook.orange.controller.form.OrderIDForm;
import com.leaforbook.orange.controller.form.OrderForm;
import com.leaforbook.orange.controller.form.OrderQueryForm;
import com.leaforbook.orange.controller.form.OrderStatusForm;
import com.leaforbook.orange.dao.model.OrangeOrder;
import com.leaforbook.orange.service.OrderService;
import com.leaforbook.orange.util.BasicResponse;
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
@RequestMapping("/orange/order")
@Slf4j
@Api(value = "订单信息维护", description = "订单信息维护")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    @ApiOperation(value = "增加订单信息", notes = "")
    public BasicResponse create(@RequestBody @Valid OrderForm form,
                                @Session UserInfo userInfo) {

        orderService.create(userInfo.getUserId(),form);

        return new BasicResponse();
    }

    @PostMapping("/updateStatus")
    @ApiOperation(value = "修改订单状态信息", notes = "")
    public BasicResponse updateStatus(@RequestBody @Valid OrderStatusForm form) {

        orderService.updateStatus(form);

        return new BasicResponse();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除订单信息", notes = "")
    public BasicResponse delete(@RequestBody @Valid OrderIDForm form) {

        orderService.delete(form);

        return new BasicResponse();
    }

    @PostMapping("/detail")
    @ApiOperation(value = "查看订单详情信息", notes = "")
    public BasicResponse detail(@RequestBody @Valid OrderIDForm form) {

        OrangeOrder order = orderService.detail(form);

        return new BasicResponse(order);
    }

    @PostMapping("/query")
    @ApiOperation(value = "查询订单列表信息", notes = "")
    public BasicResponse query(@RequestBody @Valid OrderQueryForm form) {

        Page<OrangeOrder> data = orderService.query(form);

        return new BasicResponse(data);
    }

}
