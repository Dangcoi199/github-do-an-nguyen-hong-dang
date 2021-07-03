package com.nguyenhongdang.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.nguyenhongdang.entity.SupplierEntity;
import com.nguyenhongdang.repository.ISupplierRepository;
import com.nguyenhongdang.validation.UniqueSupplier;

public class UniqueSupplierValidator implements ConstraintValidator<UniqueSupplier, String> {

	@Autowired
	private ISupplierRepository repository;
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		SupplierEntity entity = repository.findByName(value);
		if(entity == null) {
			return true;
		}
		return false;
	}

}
