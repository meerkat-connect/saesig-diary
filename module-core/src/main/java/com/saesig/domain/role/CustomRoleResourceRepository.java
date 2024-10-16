package com.saesig.domain.role;

import java.util.List;

public interface CustomRoleResourceRepository {

    List<RoleResourceResponseDto> findMappedResources(Long roleId);

    void delete(Long resourceId);
}
