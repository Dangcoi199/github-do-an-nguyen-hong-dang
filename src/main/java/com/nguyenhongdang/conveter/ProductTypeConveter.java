package com.nguyenhongdang.conveter;

import org.springframework.stereotype.Component;

import com.nguyenhongdang.dto.ProductTypeDTO;
import com.nguyenhongdang.entity.ProductTypeEntity;

@Component
public class ProductTypeConveter {
	public ProductTypeDTO toDTO(ProductTypeEntity entity) {
		ProductTypeDTO dto = new ProductTypeDTO();
		dto.setTypeName(entity.getTypeName());
		dto.setCode(entity.getCode());
		dto.setShortDescription(entity.getShortDescription());
		return dto;
	}

	public ProductTypeEntity toEntity(ProductTypeDTO dto) {
		ProductTypeEntity entity = new ProductTypeEntity();
		entity.setTypeName(dto.getTypeName());
		entity.setCode(dto.getCode());
		entity.setShortDescription(dto.getShortDescription());
		return entity;
	}

	public ProductTypeEntity toEntity(ProductTypeEntity entity, ProductTypeDTO dto) {
		entity.setTypeName(dto.getTypeName());
		entity.setCode(dto.getCode());
		entity.setShortDescription(dto.getShortDescription());
		return entity;
	}
}
