package com.saesig.api.file;

import org.springframework.data.jpa.repository.JpaRepository;
import com.saesig.domain.file.FileGroup;

public interface FileGroupRepository extends JpaRepository<FileGroup, Long> {
}
