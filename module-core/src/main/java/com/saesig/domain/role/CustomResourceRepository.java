package com.saesig.domain.role;

import com.saesig.global.menu.ResourceItem;

import java.util.List;

public interface CustomResourceRepository {
    void changeDepth(Long id);

    void delete(Long id);

    List<ResourceItem> findAllEnabled(String category);
}
