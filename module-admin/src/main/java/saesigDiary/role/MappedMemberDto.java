package saesigDiary.role;

import lombok.Getter;
import saesigDiary.domain.member.Member;

@Getter
public class MappedMemberDto {
    private Long id;
    private String email;
    private String nickname;

    public MappedMemberDto(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
    }
}
