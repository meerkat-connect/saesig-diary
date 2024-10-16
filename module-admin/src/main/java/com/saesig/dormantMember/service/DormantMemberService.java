package com.saesig.dormantMember.service;

import com.saesig.dormantMember.dto.DormantMemberRequestDto;
import com.saesig.dormantMember.dto.DormantMemberResponseDto;
import com.saesig.dormantMember.repository.DormantMemberRepository;
import com.saesig.role.DataTablesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DormantMemberService {
    private final DormantMemberRepository dormantMemberRepository;

    public DataTablesResponseDto findAll(DormantMemberRequestDto request) {
        Page<DormantMemberResponseDto> dormantMembers = dormantMemberRepository.findAll(request);
        return new DataTablesResponseDto(dormantMembers, dormantMembers.getContent());
    }


    public DormantMemberResponseDto findById(Long id) {
        return dormantMemberRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("휴면회원 일련번호가 존재하지 않습니다."));
    }

    public Optional<DormantMemberResponseDto> findByMemberId(Long memberId) {
        return dormantMemberRepository.findByMemberId(memberId);
    }

    @Transactional
    public void releaseDormantStatus(Long[] dormantMemberIds) {
        dormantMemberRepository.deleteDormant(dormantMemberIds);
    }
}
