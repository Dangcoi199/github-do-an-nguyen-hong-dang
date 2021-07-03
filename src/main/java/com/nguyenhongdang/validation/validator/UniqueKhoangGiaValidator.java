package com.nguyenhongdang.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.nguyenhongdang.entity.KhoangGiaEntity;
import com.nguyenhongdang.repository.IKhoangGiaRepository;
import com.nguyenhongdang.validation.UniqueKhoangGia;

public class UniqueKhoangGiaValidator implements ConstraintValidator<UniqueKhoangGia, String> {

	@Autowired
	private IKhoangGiaRepository khoangGiaRepository;
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		KhoangGiaEntity  entity = khoangGiaRepository.findByName(value);
		if(entity == null) {
			return true;
		}
		return false;
	}

}
