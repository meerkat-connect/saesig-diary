package com.saesig.config.auth.formLogin;

import com.saesig.domain.member.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
@Slf4j
public class CustomUserDetails implements UserDetails {

    @Getter
    private final Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        member.getMemberRoles().forEach(memberRole -> authorities.add((GrantedAuthority) memberRole.getRole()::getName));

        for (GrantedAuthority authority : authorities) {
            log.info("authority = {} ", authority.getAuthority());
        }

        return authorities;
    }

    @Override
    public String getPassword() {

        log.info("memberRole : {} ", member.getPassword());
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
