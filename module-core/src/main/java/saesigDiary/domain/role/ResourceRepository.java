package saesigDiary.domain.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    @Query("select r from Resource r where r.parentResource is null")
    List<Resource> findParentResources();
}
