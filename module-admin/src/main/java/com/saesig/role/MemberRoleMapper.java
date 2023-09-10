package com.saesig.role;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberRoleMapper {
    void insertMemberRoles(@Param("roleId") Long roleId, @Param("memberIds") List<Long> memberIds, @Param("sessionMemberId") Long sessionMemberId);
}
