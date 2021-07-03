package com.nguyenhongdang.service.admin;

import java.util.List;

import com.nguyenhongdang.dto.ProductTypeDTO;
import com.nguyenhongdang.entity.ProductTypeEntity;

public interface IProductTypeService {
	ProductTypeEntity getOne(long id);
	List<ProductTypeEntity> findAll();
	ProductTypeDTO save(ProductTypeDTO dto);
	ProductTypeDTO update(ProductTypeDTO dto);
	void delete(long[] ids);
	String getNameByCode(String code);
}
