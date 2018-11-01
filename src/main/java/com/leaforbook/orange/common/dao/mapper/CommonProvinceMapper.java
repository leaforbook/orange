package com.leaforbook.orange.common.dao.mapper;

import com.leaforbook.orange.common.dao.model.CommonProvince;
import com.leaforbook.orange.common.dao.model.CommonProvinceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommonProvinceMapper {
    long countByExample(CommonProvinceExample example);

    int deleteByExample(CommonProvinceExample example);

    int deleteByPrimaryKey(String provinceId);

    int insert(CommonProvince record);

    int insertSelective(CommonProvince record);

    List<CommonProvince> selectByExample(CommonProvinceExample example);

    CommonProvince selectByPrimaryKey(String provinceId);

    int updateByExampleSelective(@Param("record") CommonProvince record, @Param("example") CommonProvinceExample example);

    int updateByExample(@Param("record") CommonProvince record, @Param("example") CommonProvinceExample example);

    int updateByPrimaryKeySelective(CommonProvince record);

    int updateByPrimaryKey(CommonProvince record);
}