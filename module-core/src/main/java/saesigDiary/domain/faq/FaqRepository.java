package saesigDiary.domain.faq;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FaqRepository extends JpaRepository<Faq, Long> {

    /* join fetch를 사용하는 경우 count query를 만들어내지 못함. @EntityGraph 또는 countQuery 사용? */
    @Query(value = "SELECT f FROM Faq f left join fetch f.createdBy",
            countQuery = "SELECT count(f) FROM Faq f")
    Page<Faq> findAllWithMember(Pageable pageable);
}
