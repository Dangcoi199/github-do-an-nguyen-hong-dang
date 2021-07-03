package com.nguyenhongdang.service.admin;

import java.util.List;

import com.nguyenhongdang.entity.KhoangGiaEntity;

public interface IKhoangGiaService {
	KhoangGiaEntity getOne(long id);

	List<KhoangGiaEntity> findAll();

	KhoangGiaEntity save(KhoangGiaEntity entity);
	
	KhoangGiaEntity update(KhoangGiaEntity entity);

	void delete(long[] ids);
	
	String getNameByCode(String code);
}
