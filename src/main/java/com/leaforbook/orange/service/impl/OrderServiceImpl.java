package com.leaforbook.orange.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leaforbook.orange.controller.form.OrderIDForm;
import com.leaforbook.orange.controller.form.OrderForm;
import com.leaforbook.orange.controller.form.OrderQueryForm;
import com.leaforbook.orange.controller.form.OrderStatusForm;
import com.leaforbook.orange.dao.mapper.OrangeOrderMapper;
import com.leaforbook.orange.dao.model.OrangeOrder;
import com.leaforbook.orange.dao.model.OrangeOrderExample;
import com.leaforbook.orange.dict.OrderStatus;
import com.leaforbook.orange.service.OrderService;
import com.leaforbook.orange.util.DataStatus;
import com.leaforbook.orange.util.SnowFlake;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrangeOrderMapper orderMapper;

    @Autowired
    private SnowFlake snowFlake;

    @Override
    public void create(String userId, OrderForm form) {
        OrangeOrder order = new OrangeOrder();
        order.setOrderId(snowFlake.getId());
        order.setUserId(userId);
        order.setByCreate(userId);
        order.setByUpdate(userId);
        order.setDataStatus(DataStatus.AVAILABLE.getValue());
        order.setDateCreate(new Date());
        order.setDateUpdate(new Date());

        if(StringUtils.isEmpty(form.getAddressId())) {
            order.setOrderStatus(OrderStatus.NO_NEED_SEND.getValue());
        } else {
            order.setOrderStatus(OrderStatus.UN_SEND.getValue());
        }

        BeanUtils.copyProperties(form,order);

        orderMapper.insertSelective(order);
    }

    @Override
    public void updateStatus(OrderStatusForm form) {
        OrangeOrder order = new OrangeOrder();
        BeanUtils.copyProperties(form,order);
        order.setDateUpdate(new Date());
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public void delete(OrderIDForm form) {
        OrangeOrder order = new OrangeOrder();
        order.setOrderId(form.getOrderId());
        order.setDataStatus(DataStatus.UNAVAILABLE.getValue());
        order.setDateUpdate(new Date());
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public OrangeOrder detail(OrderIDForm form) {
        OrangeOrder order = orderMapper.selectByPrimaryKey(form.getOrderId());
        return order;
    }

    @Override
    public Page<OrangeOrder> query(OrderQueryForm form) {
        OrangeOrderExample example = new OrangeOrderExample();
        OrangeOrderExample.Criteria criteria = example.createCriteria();
        criteria.andDataStatusEqualTo(DataStatus.AVAILABLE.getValue());
        if(StringUtils.isNotEmpty(form.getOrderStatus())) {
            criteria.andOrderStatusEqualTo(form.getOrderStatus());
        }

        PageHelper.offsetPage(form.getPageNum()-1,form.getPageSize());
        Page<OrangeOrder> data = (Page<OrangeOrder>) orderMapper.selectByExample(example);

        return data;
    }
}
