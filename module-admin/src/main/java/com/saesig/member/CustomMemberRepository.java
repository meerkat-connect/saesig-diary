package com.saesig.member;

import com.saesig.common.RequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomMemberRepository {
    Page<MemberResponseDto> findAll(MemberRequestDto memberRequestDto, Pageable pageable);

    Page<AdoptedListDto> findAdoptedList(Long id, RequestDto request, Pageable pageable);
}
