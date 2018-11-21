package com.leaforbook.orange;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leaforbook.orange.common.service.ExpressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrangeApplication.class)
@WebAppConfiguration
public class ExpressTest {

    @Autowired
    private ExpressService expressService;

    @Test
    public void getExpressInfo() {
        JSONObject obj = expressService.getExpressInfo("shentong","3384225715737");
        System.out.println("--------------------"+ JSON.toJSONString(obj));
    }
}
