package com.leaforbook.orange.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
        String json = restTemplate.getForObject(url,String.class);
        return JSON.parseObject(json);
    }

    @Override
    public List<CommonExpress> getExpressList(String expressId, String name, String inCommonUse) {
        CommonExpressExample expressExample = new CommonExpressExample();
        CommonExpressExample.Criteria criteria = expressExample.createCriteria();
        criteria.andDataStatusEqualTo(DataStatus.AVAILABLE.getValue());
        if(StringUtils.isNotEmpty(expressId)) {
            criteria.andExpressIdLike("%"+expressId+"%");
        }
        if(StringUtils.isNotEmpty(name)) {
            criteria.andNameLike("%"+name+"%");
        }
        if(StringUtils.isNotEmpty(inCommonUse)) {
            criteria.andInCommonUseEqualTo(inCommonUse);
        }

        List<CommonExpress>  list = expressMapper.selectByExample(expressExample);
        return list;
    }

}
