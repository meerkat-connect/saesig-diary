package com.saesig.member;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NicknameDuplicateValidator.class)
@Target( ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NicknameDuplicate {
    String message() default "닉네임이 중복됩니다.";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
