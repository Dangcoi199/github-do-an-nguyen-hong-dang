package com.nguyenhongdang.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.nguyenhongdang.validation.validator.NowValidator;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NowValidator.class)
public @interface Now {
	String message() default "Nhập ngày sau ngày hiện tại !";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
