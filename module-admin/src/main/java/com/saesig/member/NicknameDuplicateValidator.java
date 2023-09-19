package com.saesig.member;

import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class NicknameDuplicateValidator implements ConstraintValidator<NicknameDuplicate, String> {
    private final MemberAdminRepository memberAdminRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.hasText(value) && !memberAdminRepository.existsByNickname(value);
    }
}
