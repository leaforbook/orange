package com.leaforbook.orange.controller.form;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ProductPriceListForm {
    @Size(min = 1)
    private List<@Valid ProductPriceForm> list;
}
