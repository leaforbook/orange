package com.leaforbook.orange.dao.mapper;

import com.leaforbook.orange.dao.model.OrangeProduct;
import com.leaforbook.orange.dao.model.TmpTable;

import java.util.List;

public interface OrangeProductExtendMapper {
    List<OrangeProduct> query(OrangeProduct params);
    void createTmpTable(String tableName);
    void insertTmpTable(TmpTable table);
    void dropTmpTable(String tableName);
}
