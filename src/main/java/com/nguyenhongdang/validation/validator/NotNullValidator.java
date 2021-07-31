package com.nguyenhongdang.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.nguyenhongdang.validation.NotNull;

public class NotNullValidator implements ConstraintValidator<NotNull,long[]> {

	@Override
	public boolean isValid(long[] value, ConstraintValidatorContext context) {
		if(value.length > 0) {
			return true;
		}
		return false;
	}

}
