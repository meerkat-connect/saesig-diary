package com.saesig.role;

import lombok.Getter;
import com.saesig.domain.member.Member;
import com.saesig.domain.role.MemberRole;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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
