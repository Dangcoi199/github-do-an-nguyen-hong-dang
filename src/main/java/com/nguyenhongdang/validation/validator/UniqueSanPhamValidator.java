package com.nguyenhongdang.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.nguyenhongdang.entity.SanPhamEntity;
import com.nguyenhongdang.repository.ISanPhamRepository;
import com.nguyenhongdang.validation.UniqueProduct;

public class UniqueSanPhamValidator implements ConstraintValidator<UniqueProduct, String> {
	@Autowired
	private ISanPhamRepository repository;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		SanPhamEntity entity = repository.findByName(value);
		if (entity == null) {
			return true;
		}
		return false;
	}

}
