package com.leaforbook.orange.dao.model;

import com.leaforbook.orange.util.BasicModel;

import java.util.Date;

public class OrangeProductPrice extends BasicModel {
    private String priceId;

    private String productId;

    private Long inPrice;

    private Long outMinPrice;

    private Long outMaxPrice;

    private String attributeJson;

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getInPrice() {
        return inPrice;
    }

    public void setInPrice(Long inPrice) {
        this.inPrice = inPrice;
    }

    public Long getOutMinPrice() {
        return outMinPrice;
    }

    public void setOutMinPrice(Long outMinPrice) {
        this.outMinPrice = outMinPrice;
    }

    public Long getOutMaxPrice() {
        return outMaxPrice;
    }

    public void setOutMaxPrice(Long outMaxPrice) {
        this.outMaxPrice = outMaxPrice;
    }

    public String getAttributeJson() {
        return attributeJson;
    }

    public void setAttributeJson(String attributeJson) {
        this.attributeJson = attributeJson;
    }
}