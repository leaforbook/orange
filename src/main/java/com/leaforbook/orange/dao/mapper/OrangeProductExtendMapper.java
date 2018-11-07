package com.leaforbook.orange.dao.mapper;

import com.leaforbook.orange.dao.model.OrangeProduct;

import java.util.List;

public interface OrangeProductExtendMapper {
    List<OrangeProduct> query(OrangeProduct params);
}
