package com.leaforbook.orange.dao.model;

import java.util.Date;

public class OrangeProductPrice {
    private String priceId;

    private String productId;

    private String productClass;

    private String level;

    private Double weight;

    private Long inPrice;

    private Long outMinPrice;

    private Long outMaxPrice;

    private Date dateCreate;

    private Date dateUpdate;

    private String byCreate;

    private String byUpdate;

    private String dataStatus;

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

    public String getProductClass() {
        return productClass;
    }

    public void setProductClass(String productClass) {
        this.productClass = productClass;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
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
}