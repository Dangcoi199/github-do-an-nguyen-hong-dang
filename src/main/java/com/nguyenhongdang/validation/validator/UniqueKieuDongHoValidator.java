package com.nguyenhongdang.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.nguyenhongdang.entity.KieuDongHoEntity;
import com.nguyenhongdang.repository.IKieuDongHoRepository;
import com.nguyenhongdang.validation.UniqueKieuDongHo;

public class UniqueKieuDongHoValidator implements ConstraintValidator<UniqueKieuDongHo, String> {
	@Autowired
	private IKieuDongHoRepository repository;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		KieuDongHoEntity entity = repository.findByName(value);
		if (entity == null) {
			return true;
		}
		return false;
	}

}
