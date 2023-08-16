package com.saesig.api.file;

import org.springframework.data.jpa.repository.JpaRepository;
import com.saesig.domain.file.File;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File,Long> {
    public Optional<File> findBySavedName(String savedFileName);
}
