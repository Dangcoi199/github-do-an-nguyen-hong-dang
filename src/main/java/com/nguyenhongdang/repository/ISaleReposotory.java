package com.nguyenhongdang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenhongdang.entity.SaleEntity;

public interface ISaleReposotory extends JpaRepository<SaleEntity,Long> {
	SaleEntity findByCode(String code);
}
