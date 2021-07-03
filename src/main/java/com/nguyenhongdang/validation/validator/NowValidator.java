package com.nguyenhongdang.validation.validator;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.nguyenhongdang.utils.StringToDate;
import com.nguyenhongdang.validation.Now;

public class NowValidator implements ConstraintValidator<Now, String> {
	@Autowired
	private StringToDate stringToDate;
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Date date = stringToDate.stringtoDate(value);
		Date now = new Date();
		if(date.before(now) == false) {
			return true;
		}
		return false;
	}

}
