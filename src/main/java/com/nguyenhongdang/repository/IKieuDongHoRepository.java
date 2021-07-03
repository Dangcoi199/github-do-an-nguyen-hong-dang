package com.nguyenhongdang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenhongdang.entity.KieuDongHoEntity;

public interface IKieuDongHoRepository extends JpaRepository<KieuDongHoEntity, Long> {
	KieuDongHoEntity findByName(String name);
	KieuDongHoEntity findByCode(String code);
}
