package com.leaforbook.orange.dao.mapper;

import com.leaforbook.orange.dao.model.OrangeLogistics;
import com.leaforbook.orange.dao.model.OrangeLogisticsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrangeLogisticsMapper {
    long countByExample(OrangeLogisticsExample example);

    int deleteByExample(OrangeLogisticsExample example);

    int deleteByPrimaryKey(String logisticsId);

    int insert(OrangeLogistics record);

    int insertSelective(OrangeLogistics record);

    List<OrangeLogistics> selectByExample(OrangeLogisticsExample example);

    OrangeLogistics selectByPrimaryKey(String logisticsId);

    int updateByExampleSelective(@Param("record") OrangeLogistics record, @Param("example") OrangeLogisticsExample example);

    int updateByExample(@Param("record") OrangeLogistics record, @Param("example") OrangeLogisticsExample example);

    int updateByPrimaryKeySelective(OrangeLogistics record);

    int updateByPrimaryKey(OrangeLogistics record);
}