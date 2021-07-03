package com.nguyenhongdang.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.nguyenhongdang.validation.validator.UniqueChatLieuMatValidator;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueChatLieuMatValidator.class)
public @interface UniqueChatLieuMat {
	String message() default "Tên chất liệu đã tồn tại !";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
