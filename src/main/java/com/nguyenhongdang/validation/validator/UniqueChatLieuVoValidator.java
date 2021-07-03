package com.nguyenhongdang.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.nguyenhongdang.entity.ChatLieuVoEntity;
import com.nguyenhongdang.repository.IChatLieuVoRepository;
import com.nguyenhongdang.validation.UniqueChatLieuVo;

public class UniqueChatLieuVoValidator implements ConstraintValidator<UniqueChatLieuVo, String> {
	@Autowired
	private IChatLieuVoRepository chatLieuVoRepository;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		ChatLieuVoEntity entity = chatLieuVoRepository.findByMaterial(value);
		if(entity == null) {
			return true;
		}
		return false;
	}
}
