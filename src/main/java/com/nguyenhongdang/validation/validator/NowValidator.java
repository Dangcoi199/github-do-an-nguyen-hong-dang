package com.nguyenhongdang.validation.validator;

import java.text.SimpleDateFormat;
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
		SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd");
		String nowStr= formatter.format(now);
		Date now1 = stringToDate.stringtoDate(nowStr);
		if(date.before(now1) == false) {
			return true;
		}
		return false;
	}

}
