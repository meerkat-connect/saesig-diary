package saesigDiary.file;

import org.springframework.data.jpa.repository.JpaRepository;
import saesigDiary.domain.FileGroup;

public interface FileGroupRepository extends JpaRepository<FileGroup, Long> {
}
