package saesigDiary.File;

import org.springframework.data.jpa.repository.JpaRepository;
import saesigDiary.domain.File;

public interface FileRepository extends JpaRepository<File, Long> {
}
