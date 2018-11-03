package com.leaforbook.orange.common.dao.model;

import com.leaforbook.orange.util.BasicModel;

import java.util.Date;

public class CommonProvince  extends BasicModel {
    private String provinceId;

    private String provinceName;



    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }


}