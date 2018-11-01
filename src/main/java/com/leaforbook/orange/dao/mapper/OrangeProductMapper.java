package com.leaforbook.orange.dao.mapper;

import com.leaforbook.orange.dao.model.OrangeProduct;
import com.leaforbook.orange.dao.model.OrangeProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrangeProductMapper {
    long countByExample(OrangeProductExample example);

    int deleteByExample(OrangeProductExample example);

    int deleteByPrimaryKey(String productId);

    int insert(OrangeProduct record);

    int insertSelective(OrangeProduct record);

    List<OrangeProduct> selectByExample(OrangeProductExample example);

    OrangeProduct selectByPrimaryKey(String productId);

    int updateByExampleSelective(@Param("record") OrangeProduct record, @Param("example") OrangeProductExample example);

    int updateByExample(@Param("record") OrangeProduct record, @Param("example") OrangeProductExample example);

    int updateByPrimaryKeySelective(OrangeProduct record);

    int updateByPrimaryKey(OrangeProduct record);
}