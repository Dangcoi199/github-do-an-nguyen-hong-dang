package com.nguyenhongdang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenhongdang.entity.DanhGiaEntity;

public interface IDanhGiaRepository extends JpaRepository<DanhGiaEntity, Long> {
	
}
