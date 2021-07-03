package com.nguyenhongdang.service.admin;

import java.util.List;

import com.nguyenhongdang.entity.SupplierEntity;

public interface ISupplierService {
	SupplierEntity getOne(long id);
	
	SupplierEntity findOneByCode(String code);

	List<SupplierEntity> findAll();

	SupplierEntity save(SupplierEntity entity);

	SupplierEntity update(SupplierEntity entity);

	void delete(long[] ids);
}
