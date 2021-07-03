package com.nguyenhongdang.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenhongdang.conveter.ProductTypeConveter;
import com.nguyenhongdang.dto.ProductTypeDTO;
import com.nguyenhongdang.entity.ProductTypeEntity;
import com.nguyenhongdang.repository.ProductTypeRepository;
import com.nguyenhongdang.service.admin.IProductTypeService;
import com.nguyenhongdang.utils.VNCharacterUtils;

@Service
public class ProductTypeService implements IProductTypeService {

	@Autowired
	private ProductTypeRepository productTypeRepository;
	@Autowired
	private ProductTypeConveter conveter;
	@Autowired
	private VNCharacterUtils Vncharacter;
	@Override
	public List<ProductTypeEntity> findAll() {
		List<ProductTypeEntity> productTypes = productTypeRepository.findAll();
		return productTypes;
	}
	@Override
	@Transactional
	public ProductTypeDTO save(ProductTypeDTO dto) {
		ProductTypeEntity entity = conveter.toEntity(dto);
		entity.setCode(Vncharacter.removeAccent(entity.getTypeName()));
		entity = productTypeRepository.save(entity);
		return conveter.toDTO(entity);
	}
	@Override
	@Transactional
	public ProductTypeDTO update(ProductTypeDTO dto) {
		ProductTypeEntity entity = productTypeRepository.getOne(dto.getId());
		entity = conveter.toEntity(entity, dto);
		entity.setCode(Vncharacter.removeAccent(entity.getTypeName()));
		entity = productTypeRepository.save(entity);
		return conveter.toDTO(entity);
	}
	@Override
	@Transactional
	public void delete(long[] ids) {
		for(long id : ids) {
			productTypeRepository.deleteById(id);
		}		
	}
	@Override
	public ProductTypeEntity getOne(long id) {
		ProductTypeEntity entity = productTypeRepository.getOne(id);
		return entity;
	}
	@Override
	public String getNameByCode(String code) {
		ProductTypeEntity entity = productTypeRepository.findByCode(code);
		return entity.getTypeName();
	}

}
