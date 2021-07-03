package com.nguyenhongdang.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenhongdang.entity.BaoHanhEntity;
import com.nguyenhongdang.repository.IBaoHanhRepository;
import com.nguyenhongdang.service.admin.IBaoHanhService;
import com.nguyenhongdang.utils.VNCharacterUtils;
@Service
public class BaoHanhService implements IBaoHanhService{
	@Autowired
	private IBaoHanhRepository baoHanhRepo;
	@Autowired
	private VNCharacterUtils Vncharacter;

	@Override
	public BaoHanhEntity getOne(long id) {
		return baoHanhRepo.getOne(id);
	}

	@Override
	public List<BaoHanhEntity> findAll() {
		List<BaoHanhEntity> entities = baoHanhRepo.findAll();
		return entities;
	}

	@Override
	@Transactional
	public BaoHanhEntity save(BaoHanhEntity entity) {
		entity.setCode(Vncharacter.removeAccent(entity.getName()));
		return baoHanhRepo.save(entity);
	}

	@Override
	@Transactional
	public BaoHanhEntity update(BaoHanhEntity entity) {
		BaoHanhEntity oldBH = baoHanhRepo.getOne(entity.getId());
		oldBH.setName(entity.getName());
		oldBH.setCode(Vncharacter.removeAccent(entity.getName()));
		return baoHanhRepo.save(oldBH);
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for(long id : ids) {
			baoHanhRepo.deleteById(id);
		}
		
	}
}
