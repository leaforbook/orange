package com.leaforbook.orange.dao.mapper;

import com.leaforbook.orange.dao.model.OrangeCustomAddress;
import com.leaforbook.orange.dao.model.OrangeCustomAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrangeCustomAddressMapper {
    long countByExample(OrangeCustomAddressExample example);

    int deleteByExample(OrangeCustomAddressExample example);

    int deleteByPrimaryKey(String addressId);

    int insert(OrangeCustomAddress record);

    int insertSelective(OrangeCustomAddress record);

    List<OrangeCustomAddress> selectByExample(OrangeCustomAddressExample example);

    OrangeCustomAddress selectByPrimaryKey(String addressId);

    int updateByExampleSelective(@Param("record") OrangeCustomAddress record, @Param("example") OrangeCustomAddressExample example);

    int updateByExample(@Param("record") OrangeCustomAddress record, @Param("example") OrangeCustomAddressExample example);

    int updateByPrimaryKeySelective(OrangeCustomAddress record);

    int updateByPrimaryKey(OrangeCustomAddress record);
}