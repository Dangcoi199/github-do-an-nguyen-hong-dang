package com.nguyenhongdang.service.admin;

import java.util.List;

import com.nguyenhongdang.entity.LoaiDayEntity;

public interface ILoaiDayService {
	LoaiDayEntity getOne(long id);

	List<LoaiDayEntity> findAll();

	LoaiDayEntity save(LoaiDayEntity entity);

	LoaiDayEntity update(LoaiDayEntity entity);

	void delete(long[] ids);
	
	String getNameByCode(String code);
}
