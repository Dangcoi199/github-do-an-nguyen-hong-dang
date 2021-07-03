package com.nguyenhongdang.conveter;

import org.springframework.stereotype.Component;

import com.nguyenhongdang.dto.BrandDTO;
import com.nguyenhongdang.entity.BrandEntity;

@Component
public class BrandConveter {
	public BrandDTO toDTO(BrandEntity entity) {
		BrandDTO dto = new BrandDTO();
		dto.setBrandName(entity.getBrandName());
		dto.setCode(entity.getCode());
		dto.setImage(entity.getImage());
		dto.setShortDescription(entity.getShortDescription());
		dto.setContent(entity.getContent());
		return dto;
	}

	public BrandEntity toEntity(BrandDTO dto) {
		BrandEntity entity = new BrandEntity();
		entity.setBrandName(dto.getBrandName());
		entity.setCode(dto.getCode());
		entity.setImage(dto.getImage());
		entity.setShortDescription(dto.getShortDescription());
		entity.setContent(dto.getContent());
		return entity;
	}

	public BrandEntity toEntity(BrandEntity entity, BrandDTO dto) {
		entity.setBrandName(dto.getBrandName());
		entity.setCode(dto.getCode());
		entity.setImage(dto.getImage());
		entity.setShortDescription(dto.getShortDescription());
		entity.setContent(dto.getContent());
		return entity;
	}
}
