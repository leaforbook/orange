package com.leaforbook.orange.common.dao.mapper;

import com.leaforbook.orange.common.dao.model.CommonResource;
import com.leaforbook.orange.common.dao.model.CommonResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommonResourceMapper {
    long countByExample(CommonResourceExample example);

    int deleteByExample(CommonResourceExample example);

    int deleteByPrimaryKey(String id);

    int insert(CommonResource record);

    int insertSelective(CommonResource record);

    List<CommonResource> selectByExample(CommonResourceExample example);

    CommonResource selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CommonResource record, @Param("example") CommonResourceExample example);

    int updateByExample(@Param("record") CommonResource record, @Param("example") CommonResourceExample example);

    int updateByPrimaryKeySelective(CommonResource record);

    int updateByPrimaryKey(CommonResource record);
}