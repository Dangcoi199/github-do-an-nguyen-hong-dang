package com.nguyenhongdang.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.nguyenhongdang.entity.LoaiDayEntity;
import com.nguyenhongdang.repository.ILoaiDayRepository;
import com.nguyenhongdang.validation.UniqueLoaiDay;

public class UniqueLoaiDayValidator implements ConstraintValidator<UniqueLoaiDay, String> {
	@Autowired
	private ILoaiDayRepository repository;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		LoaiDayEntity entity = repository.findByLoaiDay(value);
		if (entity == null) {

			return true;
		}
		return false;
	}

}
