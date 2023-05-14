package saesigDiary.file;

import org.springframework.data.jpa.repository.JpaRepository;
import saesigDiary.domain.file.FileGroup;

public interface FileGroupRepository extends JpaRepository<FileGroup, Long> {
}
