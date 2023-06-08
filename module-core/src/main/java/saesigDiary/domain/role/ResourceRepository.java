package saesigDiary.domain.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    @Query("SELECT r FROM Resource AS r left join fetch r.parentResource")
    public List<Resource> findAllWithRecursive();

}
