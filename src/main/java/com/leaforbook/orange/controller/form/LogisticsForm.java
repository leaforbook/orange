package com.leaforbook.orange.controller.form;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class LogisticsForm {
    @Size(min = 1)
    List<String> orderIdList;

    @NotBlank(message = "快递公司不能为空")
    private String type;

    @NotBlank(message = "快递单号不能为空")
    private String postid;
}
