package com.leaforbook.orange.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leaforbook.orange.common.dao.model.CommonResource;
import com.leaforbook.orange.common.service.CommonResourceService;
import com.leaforbook.orange.controller.form.OrderIDForm;
import com.leaforbook.orange.controller.form.OrderForm;
import com.leaforbook.orange.controller.form.OrderQueryForm;
import com.leaforbook.orange.controller.form.OrderStatusForm;
import com.leaforbook.orange.controller.vo.OrangeOrderVO;
import com.leaforbook.orange.dao.mapper.*;
import com.leaforbook.orange.dao.model.*;
import com.leaforbook.orange.dict.OrderStatus;
import com.leaforbook.orange.service.OrderService;
import com.leaforbook.orange.util.DataStatus;
import com.leaforbook.orange.util.ResourceEnum;
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

    @Autowired
    private CommonResourceService commonResourceService;

    @Autowired
    private OrangeProductMapper productMapper;

    @Autowired
    private OrangeProductPriceMapper priceMapper;

    @Autowired
    private OrangeProductFreightMapper freightMapper;

    @Autowired
    private OrangeCustomAddressMapper addressMapper;

    @Override
    public String create(String userId, OrderForm form) {
        OrangeOrder order = new OrangeOrder();
        String orderId = snowFlake.getId();
        order.setOrderId(orderId);
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

        this.setResource(orderId,userId);

        return orderId;
    }

    private void setResource(String orderId,String userId) {
        CommonResource resource = new CommonResource();
        resource.setUserId(userId);
        resource.setResourceType(ResourceEnum.ORDER_CREATE.getResourceType());
        resource.setResourceId(orderId);
        commonResourceService.insert(resource);
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

        commonResourceService.deleteResource(form.getOrderId());
    }

    @Override
    public OrangeOrder detail(OrderIDForm form) {
        OrangeOrder order = orderMapper.selectByPrimaryKey(form.getOrderId());
        return order;
    }

    @Override
    public Page<OrangeOrder> query(String userId,OrderQueryForm form) {
        OrangeOrderExample example = new OrangeOrderExample();
        OrangeOrderExample.Criteria criteria = example.createCriteria();
        criteria.andDataStatusEqualTo(DataStatus.AVAILABLE.getValue()).andUserIdEqualTo(userId);
        if(StringUtils.isNotEmpty(form.getOrderStatus())) {
            criteria.andOrderStatusEqualTo(form.getOrderStatus());
        }
        example.setOrderByClause(" date_update desc ");

        PageHelper.offsetPage((form.getPageNum()-1)*form.getPageSize(),form.getPageSize());
        Page<OrangeOrder> data = (Page<OrangeOrder>) orderMapper.selectByExample(example);

        return data;
    }

    @Override
    public Page<OrangeOrderVO> queryForAll(String userId, OrderQueryForm form) {
        Page<OrangeOrder> data = this.query(userId,form);
        Page<OrangeOrderVO> result = new Page<>();
        for(OrangeOrder order:data) {

            OrangeOrderVO vo = new OrangeOrderVO();
            BeanUtils.copyProperties(order,vo);
            String productId = order.getProductId();
            String priceId = order.getPriceId();
            String freightId = order.getFreightId();
            String addressId = order.getAddressId();
            OrangeProduct product = productMapper.selectByPrimaryKey(productId);
            vo.setProductName(product.getProductName());
            OrangeProductPrice price = priceMapper.selectByPrimaryKey(priceId);
            vo.setPrice(price.getAttributeValue());
            OrangeProductFreight freight = freightMapper.selectByPrimaryKey(freightId);
            vo.setFreight(freight.getAttributeValue());
            if(StringUtils.isNotEmpty(addressId)) {
                OrangeCustomAddress address = addressMapper.selectByPrimaryKey(addressId);
                vo.setName(address.getName());
            }

            result.add(vo);
        }
        return result;
    }
}
