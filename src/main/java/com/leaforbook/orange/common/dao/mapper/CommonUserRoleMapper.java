package com.leaforbook.orange.common.dao.mapper;

import com.leaforbook.orange.common.dao.model.CommonUserRole;
import com.leaforbook.orange.common.dao.model.CommonUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommonUserRoleMapper {
    long countByExample(CommonUserRoleExample example);

    int deleteByExample(CommonUserRoleExample example);

    int deleteByPrimaryKey(@Param("userId") String userId, @Param("roleId") String roleId);

    int insert(CommonUserRole record);

    int insertSelective(CommonUserRole record);

    List<CommonUserRole> selectByExample(CommonUserRoleExample example);

    CommonUserRole selectByPrimaryKey(@Param("userId") String userId, @Param("roleId") String roleId);

    int updateByExampleSelective(@Param("record") CommonUserRole record, @Param("example") CommonUserRoleExample example);

    int updateByExample(@Param("record") CommonUserRole record, @Param("example") CommonUserRoleExample example);

    int updateByPrimaryKeySelective(CommonUserRole record);

    int updateByPrimaryKey(CommonUserRole record);
}