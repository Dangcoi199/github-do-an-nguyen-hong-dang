package com.nguyenhongdang.service.admin;

import java.util.List;

import com.nguyenhongdang.entity.BaoHanhEntity;

public interface IBaoHanhService {
	BaoHanhEntity getOne(long id);

	List<BaoHanhEntity> findAll();

	BaoHanhEntity save(BaoHanhEntity entity);

	BaoHanhEntity update(BaoHanhEntity entity);

	void delete(long[] ids);
}
