package com.leaforbook.orange.dao.mapper;

import com.leaforbook.orange.dao.model.OrangeProductFreight;
import com.leaforbook.orange.dao.model.OrangeProductFreightExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrangeProductFreightMapper {
    long countByExample(OrangeProductFreightExample example);

    int deleteByExample(OrangeProductFreightExample example);

    int deleteByPrimaryKey(String freightId);

    int insert(OrangeProductFreight record);

    int insertSelective(OrangeProductFreight record);

    List<OrangeProductFreight> selectByExampleWithBLOBs(OrangeProductFreightExample example);

    List<OrangeProductFreight> selectByExample(OrangeProductFreightExample example);

    OrangeProductFreight selectByPrimaryKey(String freightId);

    int updateByExampleSelective(@Param("record") OrangeProductFreight record, @Param("example") OrangeProductFreightExample example);

    int updateByExampleWithBLOBs(@Param("record") OrangeProductFreight record, @Param("example") OrangeProductFreightExample example);

    int updateByExample(@Param("record") OrangeProductFreight record, @Param("example") OrangeProductFreightExample example);

    int updateByPrimaryKeySelective(OrangeProductFreight record);

    int updateByPrimaryKeyWithBLOBs(OrangeProductFreight record);

    int updateByPrimaryKey(OrangeProductFreight record);
}