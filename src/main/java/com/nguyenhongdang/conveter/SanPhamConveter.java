package com.nguyenhongdang.conveter;

import org.springframework.stereotype.Component;

import com.nguyenhongdang.dto.SanPhamDTO;
import com.nguyenhongdang.entity.SanPhamEntity;

@Component
public class SanPhamConveter {
	public SanPhamEntity toEntity(SanPhamDTO dto) {
		SanPhamEntity entity = new SanPhamEntity();
		entity.setName(dto.getName());
		entity.setCode(dto.getCode());
		entity.setImage(dto.getImage());
		entity.setPrice(dto.getPrice());
		entity.setContent(dto.getContent());
		entity.setDoChiuNuoc(dto.getDoChiuNuoc());
		entity.setDuongKinhMat(dto.getDuongKinhMat());
		return entity;
	}

	public SanPhamEntity toEntity(SanPhamEntity entity, SanPhamDTO dto) {
		entity.setName(dto.getName());
		entity.setCode(dto.getCode());
		entity.setPrice(dto.getPrice());
		entity.setContent(dto.getContent());
		entity.setDoChiuNuoc(dto.getDoChiuNuoc());
		entity.setDuongKinhMat(dto.getDuongKinhMat());
		return entity;
	}
}
