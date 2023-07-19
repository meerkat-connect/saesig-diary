package saesigDiary.domain.role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleResourceRepository extends JpaRepository< RoleResource, Long>, CustomRoleResourceRepository {
}
