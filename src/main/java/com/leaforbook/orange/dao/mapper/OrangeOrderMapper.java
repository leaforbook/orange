package com.leaforbook.orange.dao.mapper;

import com.leaforbook.orange.dao.model.OrangeOrder;
import com.leaforbook.orange.dao.model.OrangeOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrangeOrderMapper {
    long countByExample(OrangeOrderExample example);

    int deleteByExample(OrangeOrderExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(OrangeOrder record);

    int insertSelective(OrangeOrder record);

    List<OrangeOrder> selectByExample(OrangeOrderExample example);

    OrangeOrder selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") OrangeOrder record, @Param("example") OrangeOrderExample example);

    int updateByExample(@Param("record") OrangeOrder record, @Param("example") OrangeOrderExample example);

    int updateByPrimaryKeySelective(OrangeOrder record);

    int updateByPrimaryKey(OrangeOrder record);
}