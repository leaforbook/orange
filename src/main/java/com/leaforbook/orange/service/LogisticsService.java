package com.leaforbook.orange.service;

import com.alibaba.fastjson.JSONObject;
import com.leaforbook.orange.controller.form.LogisticsForm;
import com.leaforbook.orange.controller.form.LogisticsSingleForm;
import com.leaforbook.orange.controller.form.LogisticsUpdateForm;
import org.springframework.stereotype.Service;

@Service
public interface LogisticsService {
    void create(String userId, LogisticsForm form);

    JSONObject get(LogisticsSingleForm form);

    void update(LogisticsUpdateForm form);
}
