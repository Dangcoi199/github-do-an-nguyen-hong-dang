package com.nguyenhongdang.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.nguyenhongdang.validation.validator.UniqueDateBeginValidator;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueDateBeginValidator.class)
public @interface UniqueDateBegin {
	String message() default " Đang tồn tại mã giảm giá khác !";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
