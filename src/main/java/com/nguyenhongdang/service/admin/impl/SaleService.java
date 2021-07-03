package com.nguyenhongdang.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenhongdang.entity.SaleEntity;
import com.nguyenhongdang.repository.ISaleReposotory;
import com.nguyenhongdang.service.admin.ISaleService;
import com.nguyenhongdang.utils.VNCharacterUtils;
@Service
public class SaleService implements ISaleService {

	@Autowired
	private ISaleReposotory repository;
	
	@Autowired
	private VNCharacterUtils Vncharacter;
	
	@Override
	public SaleEntity getOne(long id) {
		return repository.getOne(id);
	}

	@Override
	public List<SaleEntity> findAll() {
		List<SaleEntity> entities = repository.findAll();
		return entities;
	}

	@Override
	@Transactional
	public SaleEntity save(SaleEntity entity) {
		entity.setCode(Vncharacter.removeAccent(entity.getName()));
		entity = repository.save(entity);
		return entity;
	}

	@Override
	@Transactional
	public SaleEntity update(SaleEntity entity) {
		SaleEntity oldSale = repository.getOne(entity.getId());
		oldSale.setName(entity.getName());
		oldSale.setCode(Vncharacter.removeAccent(entity.getName()));
		oldSale.setPercent(entity.getPercent());
		oldSale = repository.save(oldSale);
		return oldSale;
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for(long id : ids) {
			repository.deleteById(id);
		}		
	}

}
