package com.leaforbook.orange.util;

import lombok.Data;

import java.util.Date;

@Data
public class BasicModel {
    private Date dateCreate = new Date();

    private Date dateUpdate = new Date();

    private String byCreate = "SYS";

    private String byUpdate = "SYS";

    private String dataStatus = DataStatus.AVAILABLE.getValue();
}
