package com.saesig.member;

import com.saesig.common.RequestDto;
import com.saesig.domain.member.Member;
import com.saesig.role.DataTablesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;

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

    @Transactional
    public Long generateTemporaryPassword(Long id) {
        Member member = memberAdminRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));


        String newPassword = generateTemporaryPassword(10);
        member.setTemporaryPassword(member.getPassword(), newPassword);
        memberAdminRepository.save(member);

        return member.getId();
    }

    private String generateTemporaryPassword(int size) {
        SecureRandom random = new SecureRandom();
        StringBuffer buffer = new StringBuffer();
        String chars[] = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,0,1,2,3,4,5,6,7,8,9,!,@,#,$,%,^,*,(,)".split(",");

        for (int i = 0; i < size; i++) {
            buffer.append(chars[random.nextInt(chars.length)]);
        }

        return buffer.toString();
    }

    public boolean isNicknameDuplicate(String nickname) {
        return memberAdminRepository.existsByNickname(nickname);
    }
}
