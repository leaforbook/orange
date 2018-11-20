package com.leaforbook.orange.controller.form;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ProductPriceUpdateListForm {
    @Size(min = 1)
    private List<@Valid ProductPriceUpdateForm> list;
}
