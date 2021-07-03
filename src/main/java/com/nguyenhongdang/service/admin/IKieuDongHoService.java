package com.nguyenhongdang.service.admin;

import java.util.List;

import com.nguyenhongdang.entity.KieuDongHoEntity;

public interface IKieuDongHoService {
	KieuDongHoEntity getOne(long id);

	List<KieuDongHoEntity> findAll();

	KieuDongHoEntity save(KieuDongHoEntity entity);

	KieuDongHoEntity update(KieuDongHoEntity entity);

	void delete(long[] ids);
}
