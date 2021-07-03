package com.nguyenhongdang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenhongdang.entity.BaoHanhEntity;

public interface IBaoHanhRepository extends JpaRepository<BaoHanhEntity, Long> {
	BaoHanhEntity findByCode(String code);
}
