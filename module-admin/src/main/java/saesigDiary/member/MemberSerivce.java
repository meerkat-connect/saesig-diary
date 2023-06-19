package saesigDiary.member;

import java.util.List;

public interface MemberSerivce {

    List<MemberDto> selectMemberList(MemberDto memberDto) throws Exception;

    public List<MemberDto> findAll() throws Exception;

    public MemberDto findByEmail(String email) throws Exception;
}
