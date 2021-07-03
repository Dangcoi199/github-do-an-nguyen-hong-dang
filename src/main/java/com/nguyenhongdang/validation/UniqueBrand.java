package com.nguyenhongdang.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.nguyenhongdang.validation.validator.UniqueBrandValidator;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueBrandValidator.class)
public @interface UniqueBrand {
	String message() default "Tên thương hiệu đã tồn tại !";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
