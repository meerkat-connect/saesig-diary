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
    public List<Resource> findAllByIdUsingCTE(Long id) {
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
}
