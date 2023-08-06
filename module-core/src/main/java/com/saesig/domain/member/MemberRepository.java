package com.saesig.domain.member;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Page<Member> findAll(Pageable pageable);

    @Query("SELECT m FROM member m WHERE m.email=:email ")
    Optional<Member> findByEmail(@Param("email") String email);
}
