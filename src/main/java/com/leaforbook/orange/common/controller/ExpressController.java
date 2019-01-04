package com.leaforbook.orange.common.controller;

import com.github.pagehelper.Page;
import com.leaforbook.orange.common.controller.form.ExpressForm;
import com.leaforbook.orange.common.dao.model.CommonExpress;
import com.leaforbook.orange.common.service.ExpressService;
import com.leaforbook.orange.util.BasicResponse;
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
@RequestMapping("/common/express")
@Slf4j
@Api(value = "快递公司信息维护", description = "快递公司信息维护")
public class ExpressController {

    @Autowired
    private ExpressService expressService;

    @PostMapping("/get")
    @ApiOperation(value = "获取快递公司信息", notes = "")
    public BasicResponse getExpressList(@RequestBody @Valid ExpressForm form) {
        Page<CommonExpress> list = expressService.getExpressList(form);
        return new BasicResponse(list);
    }
}
