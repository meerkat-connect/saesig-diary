package com.saesig.global.file;

import com.saesig.domain.file.FileGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileGroupRepository extends JpaRepository<FileGroup, Long> {
}
