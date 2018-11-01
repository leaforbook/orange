package com.leaforbook.common.dao.mapper;

import com.leaforbook.common.dao.model.CommonUser;
import com.leaforbook.common.dao.model.CommonUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommonUserMapper {
    long countByExample(CommonUserExample example);

    int deleteByExample(CommonUserExample example);

    int deleteByPrimaryKey(String userId);

    int insert(CommonUser record);

    int insertSelective(CommonUser record);

    List<CommonUser> selectByExample(CommonUserExample example);

    CommonUser selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") CommonUser record, @Param("example") CommonUserExample example);

    int updateByExample(@Param("record") CommonUser record, @Param("example") CommonUserExample example);

    int updateByPrimaryKeySelective(CommonUser record);

    int updateByPrimaryKey(CommonUser record);
}