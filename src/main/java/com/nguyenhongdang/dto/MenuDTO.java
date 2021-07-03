package com.nguyenhongdang.dto;

import java.util.List;

import com.nguyenhongdang.entity.BrandEntity;
import com.nguyenhongdang.entity.KhoangGiaEntity;
import com.nguyenhongdang.entity.LoaiDayEntity;
import com.nguyenhongdang.entity.ProductTypeEntity;

import lombok.Data;

@Data
public class MenuDTO {
	private List<KhoangGiaEntity> khoangGias;
	private List<LoaiDayEntity> loaiDays;
	private List<ProductTypeEntity> loaiSps;
	private List<BrandEntity> brands;
}
