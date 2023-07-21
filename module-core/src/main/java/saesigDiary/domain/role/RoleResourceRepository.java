package saesigDiary.domain.role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleResourceRepository extends JpaRepository< RoleResource, Long>, CustomRoleResourceRepository {
    public List<RoleResource> findAllByRoleIdAndResourceIdIn(Long roleId, List<Long> resourceId);

}
