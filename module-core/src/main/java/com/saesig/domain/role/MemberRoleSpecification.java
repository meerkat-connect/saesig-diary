package com.saesig.domain.role;

import org.springframework.data.jpa.domain.Specification;

public class MemberRoleSpecification {
    public static Specification<MemberRole> hasEmail(String email) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.<String>get("email"), "%" + email + "%");
    }

    public static Specification<MemberRole> hasNickname(String nickname) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.<String>get("nickname"), "%" + nickname + "%");
    }
}
