package com.nguyenhongdang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenhongdang.entity.ProductTypeEntity;

public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, Long> {
	ProductTypeEntity findByTypeName(String typeName);
	ProductTypeEntity findByCode(String code);
}
