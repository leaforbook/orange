package com.leaforbook.orange.dao.model;

import com.leaforbook.orange.util.BasicModel;

import java.util.Date;

public class OrangeProduct extends BasicModel {
    private String productId;

    private String userId;

    private String productName;

    private String productDesc;

    private String priceAttribute;

    private String freightAttribute;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getPriceAttribute() {
        return priceAttribute;
    }

    public void setPriceAttribute(String priceAttribute) {
        this.priceAttribute = priceAttribute;
    }

    public String getFreightAttribute() {
        return freightAttribute;
    }

    public void setFreightAttribute(String freightAttribute) {
        this.freightAttribute = freightAttribute;
    }
}