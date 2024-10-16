package com.saesig.domain.role;

import com.saesig.global.menu.ResourceItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class CustomResourceRepositoryImpl implements CustomResourceRepository {
    private final EntityManager em;

    @Override
    public void changeDepth(Long id) {
        String recursiveQueryString =
                "WITH RECURSIVE resource_cte(id, depth) AS (" +
                        "  SELECT id, 0 AS depth FROM resource WHERE id = :id " +
                        "  UNION ALL " +
                        "  SELECT r.id, c.depth + 1 FROM resource_cte c JOIN resource r ON c.id = r.upper_id" +
                        ") SELECT id, depth FROM resource_cte";

        Query recursiveQuery = em.createNativeQuery(recursiveQueryString).setParameter("id", id);

        // 결과를 가져와서 리소스 테이블을 업데이트
        List<Object[]> results = recursiveQuery.getResultList();
        for (Object[] result : results) {
            Long resourceId = Long.parseLong(result[0].toString());
            Integer depth = Integer.parseInt(result[1].toString());

            // 리소스 테이블 업데이트
            em.createQuery("UPDATE Resource SET depth = :depth WHERE id = :resourceId")
                    .setParameter("depth", depth.intValue())
                    .setParameter("resourceId", resourceId.longValue())
                    .executeUpdate();
        }
    }

    @Override
    public void delete(Long id) {
        // 순서 재정렬
        String sortOrderQuery =
                "UPDATE resource r1 " +
                        "JOIN (SELECT ord, upper_id FROM resource WHERE id = :id) r2 " +
                        "ON r1.upper_id = r2.upper_id " +
                        "SET r1.ord = r1.ord - 1 " +
                        "WHERE r1.ord > r2.ord";

        em.createNativeQuery(sortOrderQuery)
                .setParameter("id", id)
                .executeUpdate();

        // 자원 제거
        String deleteResourcesQuery =
                "DELETE FROM resource WHERE id IN (" +
                        "WITH RECURSIVE resource_cte(id) AS (" +
                        "  SELECT id FROM resource WHERE id = :id " +
                        "  UNION ALL " +
                        "  SELECT r.id FROM resource_cte c JOIN resource r ON c.id = r.upper_id" +
                        ") SELECT id FROM resource_cte)";
        em.createNativeQuery(deleteResourcesQuery)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public List<ResourceItem> findAllEnabled(String category) {
        String query = """
                     WITH RECURSIVE resource_cte AS (         
                        SELECT
                            id,
                            upper_id,
                            name,
                            url,
                            depth,
                            ord,
                            style_class,
                            category,
                            CAST(LPAD(ord, 4,'0') AS CHAR(255)) AS tree_ord,
                            type,
                            http_method,
                            is_enabled,
                            name AS tree_name,
                            is_login_disallowed              
                        FROM
                            resource              
                        WHERE
                            upper_id = 0          
                            AND is_enabled = 'Y'          
                            AND category = :category
       
                        UNION ALL
        
                        SELECT
                            a.id,
                            a.upper_id,
                            a.name,
                            a.url,
                            a.depth,
                            a.ord,
                            a.style_class,
                            a.category,
                            CAST(CONCAT(b.tree_ord, LPAD(a.ord, 4,'0')) AS CHAR(255)) AS tree_ord,
                            a.type,
                            a.http_method,
                            a.is_enabled,
                            CONCAT(b.name, ' > ', a.name) AS tree_name,
                            a.is_login_disallowed              
                        FROM
                            resource a              
                        INNER JOIN resource_cte b              
                            ON a.upper_id = b.id              
                        WHERE
                            a.is_enabled = 'Y'          
                            AND a.category = :category     
                        )  
                        SELECT
                            *      
                        FROM
                            resource_cte
                        ORDER BY
                            tree_ord, depth
                """;

        List<Object[]> enabledResources = em.createNativeQuery(query).setParameter("category", ResourceCategory.ADMIN.toString()).getResultList();
        enabledResources.stream().map(enabledResource -> {
             return ResourceItem.builder()
                    .id((Long) enabledResource[0])
                    .upperId((Long) enabledResource[1])
                    .name((String) enabledResource[2])
                    .url((String) enabledResource[3])
                    .depth((Integer) enabledResource[4])
                    .ord((Integer) enabledResource[5])
                    .styleClass((String) enabledResource[6])
                    .category((String) enabledResource[7])
                    .treeOrd((String) enabledResource[8])
                    .type((String) enabledResource[9])
                    .httpMethod((String) enabledResource[10])
                    .isEnabled((Character) enabledResource[11])
                    .treeName((String) enabledResource[12])
                    .isLoginDisallowed((Character) enabledResource[13])
                    .build();
        }).collect(Collectors.toList());

        return List.of();
    }
}
