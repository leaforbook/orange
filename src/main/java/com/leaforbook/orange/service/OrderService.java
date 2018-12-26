package com.leaforbook.orange.service;

import com.github.pagehelper.Page;
import com.leaforbook.orange.controller.form.OrderIDForm;
import com.leaforbook.orange.controller.form.OrderForm;
import com.leaforbook.orange.controller.form.OrderQueryForm;
import com.leaforbook.orange.controller.form.OrderStatusForm;
import com.leaforbook.orange.controller.vo.OrangeOrderVO;
import com.leaforbook.orange.dao.model.OrangeOrder;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    String create(String userId, OrderForm form);

    void updateStatus(OrderStatusForm form);

    void delete(OrderIDForm form);

    OrangeOrder detail(OrderIDForm form);

    Page<OrangeOrder> query(String userId,OrderQueryForm form);

    Page<OrangeOrderVO> queryForAll(String userId, OrderQueryForm form);
}
