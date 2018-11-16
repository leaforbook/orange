package com.leaforbook.orange.common.dao.model;

import java.util.Date;

public class CommonResource {
    private String id;

    private String userId;

    private String resourceType;

    private String resourceId;

    private String resourceUrl;

    private Date dateCreate;

    private Date dateUpdate;

    private String byCreate;

    private String byUpdate;

    private String dataStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
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