package com.saesig.domain.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleResourceRepository extends JpaRepository<RoleResource, Long>, CustomRoleResourceRepository {
    public List<RoleResource> findAllByRoleIdAndResourceIdIn(Long roleId, List<Long> resourceId);

    public List<RoleResource> findAllByRoleId(Long roleId);

    @Query("SELECT r FROM RoleResource r join fetch r.role where r.resource.id = :resourceId")
    public List<RoleResource> findAllByResourceId(@Param("resourceId") Long resourceId);
}
