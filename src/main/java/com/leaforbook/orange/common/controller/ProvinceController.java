package com.leaforbook.orange.common.controller;

import com.github.pagehelper.Page;
import com.leaforbook.orange.common.controller.vo.ProvinceVO;
import com.leaforbook.orange.common.service.ProvinceService;
import com.leaforbook.orange.controller.form.CustomAddressQueryForm;
import com.leaforbook.orange.dao.model.OrangeCustomAddress;
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
import java.util.List;

@RestController
@RequestMapping("/common/province")
@Slf4j
@Api(value = "省份信息维护", description = "省份信息维护")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @PostMapping("/query")
    @ApiOperation(value = "获取省份信息列表", notes = "")
    public BasicResponse query(@Session UserInfo userInfo) {

        if(userInfo == null) {
            throw new BasicBusinessException(ExceptionEnum.UNLOGIN);
        }

        List<ProvinceVO> list = provinceService.query();

        return new BasicResponse(list);
    }
}
