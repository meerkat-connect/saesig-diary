package saesigDiary.file;

import org.springframework.data.jpa.repository.JpaRepository;
import saesigDiary.domain.File;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File,Long> {
    public Optional<File> findBySavedName(String savedFileName);
}
