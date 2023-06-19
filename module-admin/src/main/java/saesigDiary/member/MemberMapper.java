package saesigDiary.member;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    List<MemberDto> selectMemberList(MemberDto memberDto) throws Exception;
    public List<MemberDto> findAll() throws Exception;

    public MemberDto findByEmail(String email) throws Exception;


}
