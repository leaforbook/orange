package com.leaforbook.orange.common.dao.model;

import com.leaforbook.orange.util.BasicModel;

import java.util.Date;

public class CommonInvitation extends BasicModel {
    private String invitationId;

    private Integer totalCount;

    private Integer availableCount;


    public String getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(String invitationId) {
        this.invitationId = invitationId;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(Integer availableCount) {
        this.availableCount = availableCount;
    }

}