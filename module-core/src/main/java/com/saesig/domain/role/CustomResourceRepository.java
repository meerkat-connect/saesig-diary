package com.saesig.domain.role;

import java.util.List;

public interface CustomResourceRepository {
    List<ResourceCteDto> findAllByIdUsingCTE (Long id);

    void changeDepth(Long id);

    void delete(Long id);
}
