package com.nguyenhongdang.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenhongdang.entity.SupplierEntity;
import com.nguyenhongdang.repository.ISupplierRepository;
import com.nguyenhongdang.service.admin.ISupplierService;
import com.nguyenhongdang.utils.VNCharacterUtils;

@Service
public class SupplierService implements ISupplierService {

	@Autowired
	private ISupplierRepository repository;
	@Autowired
	private VNCharacterUtils Vncharacter;
	
	@Override
	public SupplierEntity getOne(long id) {		
		return repository.getOne(id);
	}

	@Override
	public List<SupplierEntity> findAll() {
		List<SupplierEntity> entities = repository.findAll();
		return entities;
	}

	@Override
	@Transactional
	public SupplierEntity save(SupplierEntity entity) {
		entity.setCode(Vncharacter.removeAccent(entity.getName()));
		return repository.save(entity);
	}

	@Override
	@Transactional
	public SupplierEntity update(SupplierEntity entity) {
		SupplierEntity oldEntity = repository.getOne(entity.getId());
		oldEntity.setName(entity.getName());
		oldEntity.setCode(Vncharacter.removeAccent(entity.getName()));
		oldEntity.setAddress(entity.getAddress());
		oldEntity.setEmail(entity.getEmail());
		oldEntity.setPhone(entity.getPhone());
		return repository.save(oldEntity);
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for(long id : ids) {
			repository.deleteById(id);
		}
		
	}

	@Override
	public SupplierEntity findOneByCode(String code) {
		return repository.findByCode(code);
	}

}
