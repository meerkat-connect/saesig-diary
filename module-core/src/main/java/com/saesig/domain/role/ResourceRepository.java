package com.saesig.domain.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long>, CustomResourceRepository {

    @Query("SELECT r FROM Resource AS r left join fetch r.parentResource")
    List<Resource> findAll();

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Resource r SET r.ord=r.ord-1 WHERE r.parentResource.id=:parentId AND r.ord > :originalOrd AND r.ord <= :newOrd")
    void decreaseOrder(@Param("parentId") Long parentId, @Param("newOrd") Integer newOrd, @Param("originalOrd") Integer originalOrd);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Resource r SET r.ord=r.ord+1 WHERE r.parentResource.id=:parentId AND r.ord >= :newOrd AND r.ord < :originalOrd")
    void increaseOrder(@Param("parentId") Long parentId, @Param("newOrd") Integer newOrd, @Param("originalOrd") Integer originalOrd);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Resource r SET r.ord=r.ord-1 WHERE r.parentResource.id=:parentId AND r.ord > :ord")
    void decreaseOrderOfOriginalParentResource(@Param("parentId") Long parentId, @Param("ord") Integer ord);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Resource r SET r.ord=r.ord+1 WHERE r.parentResource.id=:parentId AND r.ord >= :ord")
    void increaseOrderOfNewParentResource(@Param("parentId") Long parentId, @Param("ord") Integer ord);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Resource r SET r.ord=:ord, r.parentResource.id=:parentId, r.depth =:depth + 1 WHERE r.id=:id")
    void changeParentId(@Param("id") Long id, @Param("parentId") Long parentId, @Param("ord") Integer ord, @Param("depth") Integer depth);
}
