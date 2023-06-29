package saesigDiary.domain.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class CustomResourceRepositoryImpl implements CustomResourceRepository {
    private final EntityManager em;

    @Override
    public List<ResourceCteDto> findAllByIdUsingCTE(Long id) {
        String nativeQueryString =
                "WITH RECURSIVE resource_cte(id) AS (" +
                        "SELECT a.* FROM resource a where a.id = :id " +
                        "UNION ALL " +
                        "SELECT b.* FROM resource_cte AS a " +
                        "JOIN resource AS b ON a.id = b.upper_id) " +
                        "SELECT * FROM resource_cte";
        Query nativeQuery = em.createNativeQuery(nativeQueryString, Resource.class);
        nativeQuery.setParameter("id", id);

        return nativeQuery.getResultList();
    }

    @Override
    public void changeDepth(Long id) {
        String nativeQueryString =
                "WITH RECURSIVE resource_cte(id,depth) AS ( " +
                    "SELECT a.id, a.depth FROM resource a where a.id = :id " +
                    "UNION ALL " +
                    "SELECT b.id,a.depth + 1  FROM resource_cte AS a " +
                    "JOIN resource AS b ON a.id = b.upper_id) " +
                "UPDATE resource " +
                "SET resource.depth = (SELECT depth FROM resource_cte WHERE resource.id = resource_cte.id) " +
                "WHERE resource.id = (SELECT id FROM resource_cte WHERE resource.id = resource_cte.id)";

        Query nativeQuery = em.createNativeQuery(nativeQueryString).setParameter("id",id);
        int firstResult = nativeQuery.executeUpdate();
/*        List<Object[]> resultList = em.createNativeQuery(nativeQueryString).setParameter("id", id).getResultList();

        List<ResourceCteDto> collect = resultList.stream()
                .map(resource -> {
                    return new ResourceCteDto(
                            Long.parseLong(resource[0].toString()),
                            (String) resource[1],
                            (String) resource[2],
                            (Integer) resource[3],
                            (Integer) resource[4]
                    );
                })
                .collect(Collectors.toList());
        collect.stream().forEach(System.out::println);*/
    }
}
