package com.nguyenhongdang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenhongdang.entity.BrandEntity;

public interface IBrandRepository extends JpaRepository<BrandEntity, Long>{
	BrandEntity findByBrandName(String brandName);
	BrandEntity findByCode(String code);
}
