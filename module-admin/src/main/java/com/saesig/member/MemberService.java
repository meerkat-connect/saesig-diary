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

    public MemberDetailResponseDto findById(Long id) {
        return memberAdminRepository
                .findDetailById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

    public DataTablesResponseDto findAdoptedList(Long id, RequestDto request) {

        Integer pageNum = request.getStart() / request.getLength();
        PageRequest of = PageRequest.of(pageNum, request.getLength(), Sort.by(Sort.Direction.DESC, "created_at"));

        Page<AdoptedListResponseDto> adoptedList = memberAdminRepository.findAdoptedList(id, request, of);

        return new DataTablesResponseDto(adoptedList, adoptedList.getContent());
    }

    public DataTablesResponseDto findAdoptionList(Long id, RequestDto request) {
        Integer pageNum = request.getStart() / request.getLength();
        PageRequest of = PageRequest.of(pageNum, request.getLength(), Sort.by(Sort.Direction.DESC, "created_at"));

        Page<AdoptionListResponseDto> adoptionsList = memberAdminRepository.findAdoptionList(id, request, of);

        return new DataTablesResponseDto(adoptionsList, adoptionsList.getContent());
    }

    public DataTablesResponseDto findReportList(Long id, RequestDto request) {
        Integer pageNum = request.getStart() / request.getLength();
        PageRequest of = PageRequest.of(pageNum, request.getLength(), Sort.by(Sort.Direction.DESC, "created_at"));

        Page<ReportResponseDto> adoptionsList = memberAdminRepository.findReportList(id, request, of);

        return new DataTablesResponseDto(adoptionsList, adoptionsList.getContent());
    }

    public DataTablesResponseDto findBlockList(Long id, RequestDto request) {
        Integer pageNum = request.getStart() / request.getLength();
        PageRequest of = PageRequest.of(pageNum, request.getLength(), Sort.by(Sort.Direction.DESC, "created_at"));

        Page<BlockResponseDto> blockList = memberAdminRepository.findBlockList(id, request, of);

        return new DataTablesResponseDto(blockList, blockList.getContent());
    }
}
