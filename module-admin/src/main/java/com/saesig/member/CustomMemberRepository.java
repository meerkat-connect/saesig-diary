package com.saesig.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomMemberRepository {
    Page<MemberResponseDto> findAll(MemberRequestDto memberRequestDto, Pageable pageable);
}
