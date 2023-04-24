package saesigDiary.member;

import java.util.List;

public interface MemberSerivce {
    public List<MemberDto> findAll() throws Exception;

    public MemberDto findByEmail(String email) throws Exception;
}
