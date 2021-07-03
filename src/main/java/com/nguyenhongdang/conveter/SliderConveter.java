package com.nguyenhongdang.conveter;

import org.springframework.stereotype.Component;

import com.nguyenhongdang.dto.SliderDTO;
import com.nguyenhongdang.entity.SliderEntity;

@Component
public class SliderConveter {
	public SliderDTO toDTO(SliderEntity entity) {
		SliderDTO dto = new SliderDTO();
		dto.setName(entity.getName());
		dto.setImages(entity.getImages());
		return dto;
	}

	public SliderEntity toEntity(SliderDTO dto) {
		SliderEntity entity = new SliderEntity();
		entity.setName(dto.getName());
		entity.setImages(dto.getImages());
		return entity;
	}

	public SliderEntity toEntity(SliderEntity entity, SliderDTO dto) {
		entity.setName(dto.getName());
		entity.setImages(dto.getImages());
		return entity;
	}
}
