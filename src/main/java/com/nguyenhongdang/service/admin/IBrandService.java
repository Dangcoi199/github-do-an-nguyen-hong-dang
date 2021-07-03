package com.nguyenhongdang.service.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.nguyenhongdang.dto.BrandDTO;
import com.nguyenhongdang.entity.BrandEntity;

public interface IBrandService {
	BrandEntity getOne(long id);

	List<BrandEntity> findAll();

	BrandEntity save(BrandDTO dto , HttpServletRequest request) throws IOException;

	BrandEntity update(BrandDTO dto ,HttpServletRequest request) throws IOException;

	void delete(long[] ids);
	
	BrandEntity findByCode(String code);
}
