package com.nguyenhongdang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenhongdang.entity.SupplierEntity;

public interface ISupplierRepository extends JpaRepository<SupplierEntity, Long> {
	SupplierEntity findByName(String name);
	SupplierEntity findByCode(String code);
}
