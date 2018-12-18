package com.leaforbook.orange.controller;

import com.github.pagehelper.Page;
import com.leaforbook.orange.controller.form.CustomAddressIdForm;
import com.leaforbook.orange.controller.form.CustomAddressForm;
import com.leaforbook.orange.controller.form.CustomAddressQueryForm;
import com.leaforbook.orange.controller.form.CustomAddressUpdateForm;
import com.leaforbook.orange.dao.model.OrangeCustomAddress;
import com.leaforbook.orange.service.CustomAddressService;
import com.leaforbook.orange.util.*;
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
@RequestMapping("/orange/address")
@Slf4j
@Api(value = "客户地址信息维护", description = "客户地址信息维护")
public class CustomAddressController {

    @Autowired
    private CustomAddressService customAddressService;

    @PostMapping("/create")
    @ApiOperation(value = "创建客户的收货地址信息", notes = "")
    public BasicResponse create(@RequestBody @Valid CustomAddressForm form,
                                @Session UserInfo userInfo) {

        String addressId = customAddressService.create(userInfo.getUserId(),form);

        return new BasicResponse(addressId);
    }


    @PostMapping("/update")
    @ApiOperation(value = "更新客户的收货地址信息", notes = "")
    @HasResource(resourceType = "CAC",resourceId = "addressId")
    public BasicResponse update(@RequestBody @Valid CustomAddressUpdateForm form) {

        customAddressService.update(form);

        return new BasicResponse();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除客户的收货地址信息", notes = "")
    @HasResource(resourceType = "CAC",resourceId = "addressId")
    @HasSession(key = "verifyPassword")
    public BasicResponse delete(@RequestBody @Valid CustomAddressIdForm form) {

        customAddressService.delete(form);

        return new BasicResponse();
    }

    @PostMapping("/get")
    @ApiOperation(value = "获取客户的收货地址信息", notes = "")
    @HasResource(resourceType = "CAC",resourceId = "addressId")
    public BasicResponse get(@RequestBody @Valid CustomAddressIdForm form) {

        OrangeCustomAddress address = customAddressService.get(form);

        return new BasicResponse(address);
    }

    @PostMapping("/query")
    @ApiOperation(value = "获取客户的收货地址信息列表", notes = "")
    public BasicResponse query(@RequestBody @Valid CustomAddressQueryForm form,
                               @Session UserInfo userInfo) {

        Page<OrangeCustomAddress> data = customAddressService.query(userInfo.getUserId(),form);

        return new BasicResponse(data);
    }
}
