package com.nguyenhongdang.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.nguyenhongdang.entity.ProductTypeEntity;
import com.nguyenhongdang.repository.ProductTypeRepository;
import com.nguyenhongdang.validation.UniqueTypeName;

public class UniqueTypeNameValidator implements ConstraintValidator<UniqueTypeName, String> {
	@Autowired
	private ProductTypeRepository productTypeRepository;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		ProductTypeEntity entity = productTypeRepository.findByTypeName(value);
		if(entity == null) {
			return true;
		}
		return false;
	}
}
