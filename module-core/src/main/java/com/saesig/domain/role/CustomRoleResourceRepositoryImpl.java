package com.saesig.domain.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class CustomRoleResourceRepositoryImpl implements CustomRoleResourceRepository {
    private final EntityManager em;

    @Override
    public List<RoleResourceResponseDto> findMappedResources(Long roleId) {
        String nativeQueryString =
                "SELECT a.name, a.url, b.role_id, a.upper_id, a.type, a.id " +
                        "FROM resource a " +
                        "LEFT OUTER JOIN ( " +
                        "SELECT * FROM role_resource where role_id = :id " +
                        ") b on a.id = b.resource_id";

        Query nativeQuery = em.createNativeQuery(nativeQueryString).setParameter("id", roleId);
        List<Object[]> resultList = nativeQuery.getResultList();

        List<RoleResourceResponseDto> collect = resultList.stream()
                .map(roleResource -> {
                    return new RoleResourceResponseDto(
                            (String) roleResource[0],
                            (String) roleResource[1],
                            roleResource[2] == null ? 'N' : 'Y',
                            roleResource[3] == null ? null : ((BigInteger) roleResource[3]).longValue(),
                            (String) roleResource[4],
                            ((BigInteger) roleResource[5]).longValue()
                    );
                })
                .collect(Collectors.toList());

        return collect;
    }

    @Override
    public void delete(Long resourceId) {
        String deleteRoleResourceQuery =
                "DELETE FROM role_resource WHERE resource_id IN (" +
                        "WITH RECURSIVE resource_cte(id) AS (" +
                        "  SELECT id FROM resource WHERE id = :id " +
                        "  UNION ALL " +
                        "  SELECT r.id FROM resource_cte c JOIN resource r ON c.id = r.upper_id" +
                        ") SELECT id FROM resource_cte)";

        em.createNativeQuery(deleteRoleResourceQuery)
                .setParameter("id", resourceId)
                .executeUpdate();
    }
}
