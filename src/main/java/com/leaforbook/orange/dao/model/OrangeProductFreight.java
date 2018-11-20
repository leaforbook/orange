package com.leaforbook.orange.dao.model;

import com.alibaba.fastjson.JSONObject;
import io.micrometer.core.instrument.util.StringUtils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class OrangeProductFreight {
    private String freightId;

    private String productId;

    private String provinceId;

    private BigDecimal freightPrice;

    private Date dateCreate;

    private Date dateUpdate;

    private String byCreate;

    private String byUpdate;

    private String dataStatus;

    private String attributeValue;

    private String display;

    public String getDisplay() {
        JSONObject jsonObject = JSONObject.parseObject(attributeValue);
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

    public String getFreightId() {
        return freightId;
    }

    public void setFreightId(String freightId) {
        this.freightId = freightId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public BigDecimal getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(BigDecimal freightPrice) {
        this.freightPrice = freightPrice;
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

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }
}