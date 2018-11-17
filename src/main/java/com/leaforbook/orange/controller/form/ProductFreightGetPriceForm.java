package com.leaforbook.orange.controller.form;


import com.leaforbook.orange.util.Json;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProductFreightGetPriceForm {

    @NotBlank(message = "产品ID不能为空")
    private String productId;

    @NotBlank(message = "省份ID不能为空")
    private String provinceId;

    @Json
    @NotBlank(message = "运费属性不能为空")
    private String attributeValue;

}
