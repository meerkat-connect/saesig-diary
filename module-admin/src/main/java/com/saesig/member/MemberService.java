package com.saesig.member;

import com.saesig.api.mail.MailDto;
import com.saesig.api.mail.MailService;
import com.saesig.common.RequestDto;
import com.saesig.domain.member.Member;
import com.saesig.domain.member.MemberStatus;
import com.saesig.domain.member.SignupMethod;
import com.saesig.dormantMember.dto.DormantMemberResponseDto;
import com.saesig.dormantMember.repository.DormantMemberRepository;
import com.saesig.role.DataTablesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberAdminRepository memberAdminRepository;
    private final DormantMemberRepository dormantMemberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    public DataTablesResponseDto findAll(MemberRequestDto memberRequestDto) {
        Integer pageNum = memberRequestDto.getStart() / memberRequestDto.getLength();
        PageRequest of = PageRequest.of(pageNum, memberRequestDto.getLength(), Sort.by(Sort.Direction.DESC, "created_at"));

        Page<MemberResponseDto> members = memberAdminRepository.findAll(memberRequestDto, of);

        return new DataTablesResponseDto(members, members.getContent());
    }

    public MemberDetailDto findById(Long id) {
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
        member.setTemporaryPassword(member.getPassword(), passwordEncoder.encode(newPassword));
        memberAdminRepository.save(member);

        String subject = "새식일기 임시 비밀번호 안내 이메일입니다.";
        String message = "안녕하세요. 새식일기 임시 비밀번호 안내 메일입니다. "
                + "\n" + "회원님의 임시 비밀번호는 아래와 같습니다. 로그인 후 반드시 비밀번호를 변경해주세요." + "\n";
        String fromAddress = "meerkat@gmail.com";

        MailDto mailDto = MailDto.builder()
                .toAddress(member.getEmail())
                .subject(subject)
                .message(message + newPassword)
                .template("/api/mail/tempPasswordTemplate")
                .fromAddress(fromAddress)
                .build();

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("messageTitle",subject);
        parameters.put("messageContent", message + newPassword);
        mailDto.setParameters(parameters);

        Map<String, String> images = new HashMap<>();
        images.put("main_logo", "static/api/main_logo.png");
        images.put("main_bg", "static/api/main_bg.png");
        images.put("bottom_logo", "static/api/bottom_logo.png");
        mailDto.setImages(images);

        mailService.sendMail(mailDto);

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

    public boolean isEmailDuplicate(String email) {
        return memberAdminRepository.existsByEmail(email);
    }

    public Optional<Member> findByNickname(String nickname) {
        return memberAdminRepository.findByNickname(nickname);
    }

    @Transactional
    public Long insertMember(MemberInsertDto memberInsertDto) {

        Member newMember = Member.builder().nickname(memberInsertDto.getNickname())
                .email(memberInsertDto.getEmail())
                .status(MemberStatus.NORMAL)
                .signupMethod(SignupMethod.EMAIL)
                .password(passwordEncoder.encode(memberInsertDto.getPassword()))
                .serviceAgreement("Y")
                .privacyAgreement("Y")
                .locationServiceAgreement("N")
                .marketingServiceAgreement("N")
                .build();

        Member savedMember = memberAdminRepository.save(newMember);

        return savedMember.getId();
    }

    @Transactional
    public Long updateMember(Long id, MemberUpdateDto memberUpdateDto) {
        Member member = memberAdminRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("회원 아이디가 존재하지 않습니다."));

        // 휴면 등록
        if(MemberStatus.DORMANCY.getKey().equals(memberUpdateDto.getStatus()) && !member.getStatus().getKey().equals(MemberStatus.DORMANCY.getKey())) {
            dormantMemberRepository.insertDormantMember(id);
        }

        // 휴면 해제
        if(!MemberStatus.DORMANCY.getKey().equals(memberUpdateDto.getStatus())) {
            Optional<DormantMemberResponseDto> dormantMember = dormantMemberRepository.findByMemberId(id);
            if(dormantMember.isPresent()) {
                dormantMemberRepository.deleteDormant(dormantMember.get().getId());
            }
        }

        member.setNickname(memberUpdateDto.getNickname());
        member.setStatus(MemberStatus.valueOf(memberUpdateDto.getStatus()));

        return member.getId();
    }

}
