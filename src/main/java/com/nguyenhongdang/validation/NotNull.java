package com.nguyenhongdang.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.nguyenhongdang.validation.validator.NotNullValidator;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotNullValidator.class)
public @interface NotNull {
	String message() default "Bạn chưa chọn sản phẩm nào,hãy chọn sản phẩm !";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
