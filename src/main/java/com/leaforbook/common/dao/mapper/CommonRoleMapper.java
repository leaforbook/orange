package com.leaforbook.common.dao.mapper;

import com.leaforbook.common.dao.model.CommonRole;
import com.leaforbook.common.dao.model.CommonRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommonRoleMapper {
    long countByExample(CommonRoleExample example);

    int deleteByExample(CommonRoleExample example);

    int deleteByPrimaryKey(String roleId);

    int insert(CommonRole record);

    int insertSelective(CommonRole record);

    List<CommonRole> selectByExample(CommonRoleExample example);

    CommonRole selectByPrimaryKey(String roleId);

    int updateByExampleSelective(@Param("record") CommonRole record, @Param("example") CommonRoleExample example);

    int updateByExample(@Param("record") CommonRole record, @Param("example") CommonRoleExample example);

    int updateByPrimaryKeySelective(CommonRole record);

    int updateByPrimaryKey(CommonRole record);
}