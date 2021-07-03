package com.nguyenhongdang.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenhongdang.entity.LoaiDayEntity;
import com.nguyenhongdang.repository.ILoaiDayRepository;
import com.nguyenhongdang.service.admin.ILoaiDayService;
import com.nguyenhongdang.utils.VNCharacterUtils;

@Service
public class LoaiDayService implements ILoaiDayService{
	@Autowired
	private ILoaiDayRepository repository;
	@Autowired
	private VNCharacterUtils Vncharacter;

	@Override
	public LoaiDayEntity getOne(long id) {
		return  repository.getOne(id);
	}

	@Override
	public List<LoaiDayEntity> findAll() {
		List<LoaiDayEntity> entities = repository.findAll();
		return entities;
	}

	@Override
	@Transactional
	public LoaiDayEntity save(LoaiDayEntity entity) {
		entity.setCode(Vncharacter.removeAccent(entity.getLoaiDay()));
		return repository.save(entity);
	}

	@Override
	@Transactional
	public LoaiDayEntity update(LoaiDayEntity entity) {
		LoaiDayEntity oldEntity = repository.getOne(entity.getId());
		oldEntity.setLoaiDay(entity.getLoaiDay());
		oldEntity.setCode(Vncharacter.removeAccent(entity.getLoaiDay()));
		oldEntity.setShortDescription(entity.getShortDescription());
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
	public String getNameByCode(String code) {
		LoaiDayEntity entity = repository.findByCode(code);
		return entity.getLoaiDay();
	}

}
