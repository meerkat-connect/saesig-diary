package com.saesig.global.file;

import com.saesig.domain.file.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File,Long> {
    public Optional<File> findBySavedName(String savedFileName);

    public Optional<File> findByOriginName(String originFileName);
}
