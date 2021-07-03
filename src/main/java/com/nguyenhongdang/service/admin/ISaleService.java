package com.nguyenhongdang.service.admin;

import java.util.List;

import com.nguyenhongdang.entity.SaleEntity;

public interface ISaleService {
	SaleEntity getOne(long id);

	List<SaleEntity> findAll();

	SaleEntity save(SaleEntity entity);

	SaleEntity update(SaleEntity entity);

	void delete(long[] ids);
}
