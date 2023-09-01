package com.saesig.member;

import com.saesig.common.RequestDto;
import com.saesig.role.DataTablesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberAdminRepository memberAdminRepository;

    public DataTablesResponseDto findAll(MemberRequestDto memberRequestDto) {
        Integer pageNum = memberRequestDto.getStart() / memberRequestDto.getLength();
        PageRequest of = PageRequest.of(pageNum, memberRequestDto.getLength(), Sort.by(Sort.Direction.DESC, "created_at"));

        Page<MemberResponseDto> members = memberAdminRepository.findAll(memberRequestDto, of);

        return new DataTablesResponseDto(members, members.getContent());
    }

    public MemberResponseDto findById(Long id) {
//        Faq faq = faqRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("FAQ 아이디가 존재하지 않습니다."));
        return null;
    }

    public DataTablesResponseDto findAdoptedList(Long id, RequestDto request) {

        Integer pageNum = request.getStart() / request.getLength();
        PageRequest of = PageRequest.of(pageNum, request.getLength(), Sort.by(Sort.Direction.DESC, "created_at"));

        Page<AdoptedListDto> adoptionHistorys = memberAdminRepository.findAdoptedList(id, request,of);

        return new DataTablesResponseDto(adoptionHistorys, adoptionHistorys.getContent());
    }

}
