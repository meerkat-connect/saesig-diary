package saesigDiary.domain.role;

import java.util.List;

public interface CustomResourceRepository {
    List<Resource> findAllByIdUsingCTE (Long id);
}
