package com.leaforbook.orange.util;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class PageForm {
    @Min(1)
    private int pageSize;
    @Min(1)
    private int pageNum;
}
