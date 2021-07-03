package com.nguyenhongdang.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.nguyenhongdang.entity.BrandEntity;
import com.nguyenhongdang.repository.IBrandRepository;
import com.nguyenhongdang.validation.UniqueBrand;

public class UniqueBrandValidator implements ConstraintValidator<UniqueBrand, String> {

	@Autowired
	private IBrandRepository repository;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		BrandEntity entity = repository.findByBrandName(value);
		if (entity == null) {
			return true;
		}
		return false;
	}

}
