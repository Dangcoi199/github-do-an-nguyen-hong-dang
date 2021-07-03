package com.nguyenhongdang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenhongdang.entity.KhoangGiaEntity;

public interface IKhoangGiaRepository extends JpaRepository<KhoangGiaEntity, Long> {
	KhoangGiaEntity findByName(String name);
	KhoangGiaEntity findByCode(String code);
}
