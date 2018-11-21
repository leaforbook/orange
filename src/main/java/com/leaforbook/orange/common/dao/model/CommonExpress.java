package com.leaforbook.orange.common.dao.model;

import java.util.Date;

public class CommonExpress {
    private String expressId;

    private String type;

    private String name;

    private Date dateCreate;

    private Date dateUpdate;

    private String byCreate;

    private String byUpdate;

    private String dataStatus;

    public String getExpressId() {
        return expressId;
    }

    public void setExpressId(String expressId) {
        this.expressId = expressId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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