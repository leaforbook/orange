package com.leaforbook.orange.common.service;

import com.alibaba.fastjson.JSONObject;
import com.leaforbook.orange.common.dao.model.CommonExpress;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpressService {
    JSONObject getExpressInfo(String com,String postid);
    List<CommonExpress> getExpressList(String expressId,String name,String inCommonUse);
}
