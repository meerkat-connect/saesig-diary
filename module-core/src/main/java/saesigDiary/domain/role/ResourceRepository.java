package saesigDiary.domain.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    @Query("SELECT r FROM Resource AS r left join fetch r.parentResource")
    public List<Resource> findAllWithRecursive();

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Resource r SET r.ord=r.ord-1 WHERE r.parentResource.id=:parentId AND r.ord >:ord")
    void decreaseOrder(@Param("parentId") Long parentId, @Param("ord") Integer ord);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Resource r SET r.ord=r.ord+1 WHERE r.parentResource.id=:parentId AND r.ord >:ord")
    void increaseOrder(@Param("parentId") Long parentId, @Param("ord") Integer ord);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Resource r SET r.ord=:ord, r.parentResource.id=:parentId, r.depth =:depth + 1 WHERE r.id=:id")
    void changeParentId(@Param("id") Long id, @Param("parentId") Long parentId,@Param("ord") Integer ord, @Param("depth") Integer depth);

    @Query(nativeQuery = true, value=
            "WITH RECURSIVE resource_cte (id, name, url) AS (" +
                "SELECT id, name, url FROM resource where upper_id is null " +
                "UNION ALL " +
                "SELECT b.id, b.name, b.url FROM resource_cte AS a " +
                    "JOIN resource AS b ON a.id = b.upper_id) " +
            "SELECT * FROM resource_cte"
    )
    List<TestResourceDto> testRecursive(@Param("id") Long id);

    @Query(nativeQuery = true, value= "SELECT * FROM resource")
    List<Resource> testRecursive2();

}
