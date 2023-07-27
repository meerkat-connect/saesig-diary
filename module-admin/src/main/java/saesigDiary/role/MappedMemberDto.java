package saesigDiary.role;

import lombok.Getter;
import saesigDiary.domain.member.Member;
import saesigDiary.domain.role.MemberRole;

@Getter
public class MappedMemberDto {
    private Long id;
    private String email;
    private String nickname;

    public MappedMemberDto(MemberRole memberRole) {
        Member member = memberRole.getMember();
        this.id = member.getId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
    }

    public MappedMemberDto(Member member){
        this.id = member.getId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
    }
}
