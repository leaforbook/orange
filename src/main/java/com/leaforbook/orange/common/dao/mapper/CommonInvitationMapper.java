package com.leaforbook.orange.common.dao.mapper;

import com.leaforbook.orange.common.dao.model.CommonInvitation;
import com.leaforbook.orange.common.dao.model.CommonInvitationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommonInvitationMapper {
    long countByExample(CommonInvitationExample example);

    int deleteByExample(CommonInvitationExample example);

    int deleteByPrimaryKey(String invitationId);

    int insert(CommonInvitation record);

    int insertSelective(CommonInvitation record);

    List<CommonInvitation> selectByExample(CommonInvitationExample example);

    CommonInvitation selectByPrimaryKey(String invitationId);

    int updateByExampleSelective(@Param("record") CommonInvitation record, @Param("example") CommonInvitationExample example);

    int updateByExample(@Param("record") CommonInvitation record, @Param("example") CommonInvitationExample example);

    int updateByPrimaryKeySelective(CommonInvitation record);

    int updateByPrimaryKey(CommonInvitation record);
}