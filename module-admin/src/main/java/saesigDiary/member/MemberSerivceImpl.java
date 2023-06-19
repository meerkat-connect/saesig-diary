package saesigDiary.member;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberSerivceImpl implements MemberSerivce{

    private final MemberMapper memberMapper;

    @Override
    public List<MemberDto> selectMemberList(MemberDto memberDto) throws Exception {
        return memberMapper.selectMemberList(memberDto);
    }

    @Override
    public List<MemberDto> findAll() throws Exception {
        return memberMapper.findAll();
    }

    @Override
    public MemberDto findByEmail(String email) throws Exception {
        return memberMapper.findByEmail(email);
    }
}
