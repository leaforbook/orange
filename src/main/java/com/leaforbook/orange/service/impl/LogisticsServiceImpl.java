package com.leaforbook.orange.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.leaforbook.orange.common.service.ExpressService;
import com.leaforbook.orange.controller.form.*;
import com.leaforbook.orange.dao.mapper.OrangeLogisticsMapper;
import com.leaforbook.orange.dao.model.OrangeLogistics;
import com.leaforbook.orange.dao.model.OrangeLogisticsExample;
import com.leaforbook.orange.dao.model.OrangeOrder;
import com.leaforbook.orange.dict.OrderStatus;
import com.leaforbook.orange.service.LogisticsService;
import com.leaforbook.orange.service.OrderService;
import com.leaforbook.orange.util.BasicBusinessException;
import com.leaforbook.orange.util.DataStatus;
import com.leaforbook.orange.util.ExceptionEnum;
import com.leaforbook.orange.util.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class LogisticsServiceImpl implements LogisticsService {

    @Autowired
    private OrangeLogisticsMapper logisticsMapper;

    @Autowired
    private SnowFlake snowFlake;

    @Autowired
    private ExpressService expressService;

    @Autowired
    private OrderService orderService;

    @Override
    @Transactional
    public void create(String userId, LogisticsForm form) {

        List<String> orderIdList = form.getOrderIdList();

        Set<String> addressIdSet = new HashSet<>();
        for(String orderId:orderIdList) {
            OrderIDForm orderIDForm = new OrderIDForm();
            orderIDForm.setOrderId(orderId);
            OrangeOrder order = orderService.detail(orderIDForm);
            if(order==null||!order.getUserId().equals(userId)) {
                throw new BasicBusinessException(ExceptionEnum.HAS_NO_ORDER);
            }
            if(!OrderStatus.UN_SEND.equals(order.getOrderStatus())) {
                throw new BasicBusinessException(ExceptionEnum.ONT_UNSEND_ORDER_LOGISTICS);
            }
            addressIdSet.add(order.getAddressId());
        }

        if(addressIdSet.size()>1) {
            throw new BasicBusinessException(ExceptionEnum.ONE_ADDRESS_ONE_LOGISTICS);
        }

        for(String orderId:orderIdList) {
            OrangeLogisticsExample example = new OrangeLogisticsExample();
            example.createCriteria().andOrderIdEqualTo(orderId).andDataStatusEqualTo(DataStatus.AVAILABLE.getValue());
            long count = logisticsMapper.countByExample(example);
            if(count>0l) {
                throw new BasicBusinessException(ExceptionEnum.ONE_ORDER_ONE_LOGISTICS);
            }

            OrangeLogistics logistics = new OrangeLogistics();
            logistics.setOrderId(orderId);
            logistics.setType(form.getType());
            logistics.setPostid(form.getPostid());

            logistics.setByCreate(userId);
            logistics.setByUpdate(userId);
            logistics.setDateCreate(new Date());
            logistics.setDateUpdate(new Date());
            logistics.setLogisticsId(snowFlake.getId());
            logistics.setDataStatus(DataStatus.AVAILABLE.getValue());
            logisticsMapper.insertSelective(logistics);

            //标记订单为已发货
            OrderStatusForm orderStatusForm = new OrderStatusForm();
            orderStatusForm.setOrderId(orderId);
            orderStatusForm.setOrderStatus(OrderStatus.SENDING.getValue());
            orderService.updateStatus(orderStatusForm);
        }

    }

    @Override
    public JSONObject get(LogisticsSingleForm form) {
        OrangeLogisticsExample example = new OrangeLogisticsExample();
        example.createCriteria().andDataStatusEqualTo(DataStatus.AVAILABLE.getValue()).andOrderIdEqualTo(form.getOrderId());
        List<OrangeLogistics> list = logisticsMapper.selectByExample(example);

        if(list==null||list.size()<=0) {
            return null;
        }
        JSONObject json = expressService.getExpressInfo(list.get(0).getType(),list.get(0).getPostid());
        return json;
    }

    @Override
    public void update(LogisticsUpdateForm form) {
        OrangeLogisticsExample example = new OrangeLogisticsExample();
        example.createCriteria().andOrderIdEqualTo(form.getOrderId());
        OrangeLogistics logistics = new OrangeLogistics();
        logistics.setType(form.getType());
        logistics.setPostid(form.getPostid());
        logistics.setDateUpdate(new Date());
        logisticsMapper.updateByExampleSelective(logistics,example);
    }

}
