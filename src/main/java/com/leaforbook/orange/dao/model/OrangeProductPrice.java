package com.leaforbook.orange.dao.model;

import com.alibaba.fastjson.JSONObject;
import io.micrometer.core.instrument.util.StringUtils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class OrangeProductPrice {
    private String priceId;

    private String productId;

    private BigDecimal inPrice;

    private BigDecimal outMinPrice;

    private BigDecimal outMaxPrice;

    private Date dateCreate;

    private Date dateUpdate;

    private String byCreate;

    private String byUpdate;

    private String dataStatus;

    private String attributeJson;

    private String display;

    public String getDisplay() {
        JSONObject jsonObject = JSONObject.parseObject(attributeJson);
        Collection<Object> collection =  jsonObject.values();
        String display = null;
        for(Object obj:collection) {
            display += obj.toString() + " * ";
        }

        if(StringUtils.isNotEmpty(display)) {
            display = display.substring(0,display.length()-3);
        }

        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

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

    public BigDecimal getInPrice() {
        return inPrice;
    }

    public void setInPrice(BigDecimal inPrice) {
        this.inPrice = inPrice;
    }

    public BigDecimal getOutMinPrice() {
        return outMinPrice;
    }

    public void setOutMinPrice(BigDecimal outMinPrice) {
        this.outMinPrice = outMinPrice;
    }

    public BigDecimal getOutMaxPrice() {
        return outMaxPrice;
    }

    public void setOutMaxPrice(BigDecimal outMaxPrice) {
        this.outMaxPrice = outMaxPrice;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getByCreate() {
        return byCreate;
    }

    public void setByCreate(String byCreate) {
        this.byCreate = byCreate;
    }

    public String getByUpdate() {
        return byUpdate;
    }

    public void setByUpdate(String byUpdate) {
        this.byUpdate = byUpdate;
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getAttributeJson() {
        return attributeJson;
    }

    public void setAttributeJson(String attributeJson) {
        this.attributeJson = attributeJson;
    }
}