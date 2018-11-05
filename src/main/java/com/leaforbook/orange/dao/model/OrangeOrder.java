package com.leaforbook.orange.dao.model;

import com.leaforbook.orange.util.BasicModel;

import java.util.Date;

public class OrangeOrder extends BasicModel {
    private String orderId;

    private String userId;

    private String productId;

    private String priceId;

    private String freightId;

    private String addressId;

    private Long amount;

    private Long actualUnitPrice;

    private Long totalPrice;

    private Long actualFreight;

    private Long totalCost;

    private Date deliveryDate;

    private String orderStatus;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }

    public String getFreightId() {
        return freightId;
    }

    public void setFreightId(String freightId) {
        this.freightId = freightId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getActualUnitPrice() {
        return actualUnitPrice;
    }

    public void setActualUnitPrice(Long actualUnitPrice) {
        this.actualUnitPrice = actualUnitPrice;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getActualFreight() {
        return actualFreight;
    }

    public void setActualFreight(Long actualFreight) {
        this.actualFreight = actualFreight;
    }

    public Long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Long totalCost) {
        this.totalCost = totalCost;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

}