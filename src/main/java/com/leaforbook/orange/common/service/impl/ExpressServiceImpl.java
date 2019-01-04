package com.leaforbook.orange.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leaforbook.orange.common.controller.form.ExpressForm;
import com.leaforbook.orange.common.dao.mapper.CommonExpressMapper;
import com.leaforbook.orange.common.dao.model.CommonExpress;
import com.leaforbook.orange.common.dao.model.CommonExpressExample;
import com.leaforbook.orange.common.dict.CommonConstants;
import com.leaforbook.orange.common.service.ExpressService;
import com.leaforbook.orange.util.DataStatus;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class ExpressServiceImpl implements ExpressService {

    @Autowired
    private CommonExpressMapper expressMapper;

    @Override
    public JSONObject getExpressInfo(String com, String postid) {
        RestTemplate restTemplate=new RestTemplate();
        String url = CommonConstants.KUAIDI100_URL_1+com+CommonConstants.KUAIDI100_URL_2+postid;
        String json = null;
        try {
            json = restTemplate.getForObject(url,String.class);
        } catch (Throwable e) {

        }

        return JSON.parseObject(json);
    }

    @Override
    public Page<CommonExpress> getExpressList(ExpressForm form) {
        CommonExpressExample expressExample = new CommonExpressExample();
        CommonExpressExample.Criteria criteria = expressExample.createCriteria();
        criteria.andDataStatusEqualTo(DataStatus.AVAILABLE.getValue());
        if(StringUtils.isNotEmpty(form.getQueryParams())) {
            criteria.andNameLike("%"+form.getQueryParams()+"%");
        }
        if(StringUtils.isNotEmpty(form.getExpressId())) {
            criteria.andExpressIdEqualTo(form.getExpressId());
        }
        expressExample.setOrderByClause(" in_common_use desc ");

        PageHelper.offsetPage((form.getPageNum()-1)*form.getPageSize(),form.getPageSize());

        Page<CommonExpress>  list = (Page<CommonExpress>)expressMapper.selectByExample(expressExample);

        return list;
    }

}
