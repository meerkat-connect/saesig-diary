package com.saesig.member;

import com.saesig.domain.member.MemberRepository;
import com.saesig.role.DataTablesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public DataTablesResponseDto findAll(MemberRequestDto request) {

        return null;
    }

    public MemberResponseDto findById(Long id) {
        return null;
    }
}
