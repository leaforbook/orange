package com.leaforbook.orange.common.dao.mapper;

import com.leaforbook.orange.common.dao.model.CommonExpress;
import com.leaforbook.orange.common.dao.model.CommonExpressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommonExpressMapper {
    long countByExample(CommonExpressExample example);

    int deleteByExample(CommonExpressExample example);

    int deleteByPrimaryKey(String expressId);

    int insert(CommonExpress record);

    int insertSelective(CommonExpress record);

    List<CommonExpress> selectByExample(CommonExpressExample example);

    CommonExpress selectByPrimaryKey(String expressId);

    int updateByExampleSelective(@Param("record") CommonExpress record, @Param("example") CommonExpressExample example);

    int updateByExample(@Param("record") CommonExpress record, @Param("example") CommonExpressExample example);

    int updateByPrimaryKeySelective(CommonExpress record);

    int updateByPrimaryKey(CommonExpress record);
}