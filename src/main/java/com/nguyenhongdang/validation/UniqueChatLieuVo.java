package com.nguyenhongdang.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.nguyenhongdang.validation.validator.UniqueChatLieuVoValidator;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueChatLieuVoValidator.class)
public @interface UniqueChatLieuVo {
	String message() default "Tên chất liệu đã tồn tại !";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
