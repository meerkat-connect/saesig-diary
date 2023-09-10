package com.saesig.domain.role;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRoleRepository extends JpaRepository<MemberRole, Long> {
    Page<MemberRole> findByRoleId(Long roleId, Pageable pageable);

    @Query("SELECT mr " +
            "FROM member_role mr left join mr.member left join mr.role " +
            "WHERE mr.role.id = :roleId " +
                "AND mr.member.id IN (:memberIds)")
    List<MemberRole> findAll(@Param("roleId") Long roleId, @Param("memberIds") List<Long> memberIds);
}
