package com.nguyenhongdang.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenhongdang.entity.KhoangGiaEntity;
import com.nguyenhongdang.repository.IKhoangGiaRepository;
import com.nguyenhongdang.service.admin.IKhoangGiaService;
import com.nguyenhongdang.utils.VNCharacterUtils;

@Service
public class KhoanGiaService implements IKhoangGiaService {

	@Autowired
	private IKhoangGiaRepository khoangGiaRepository;
	@Autowired
	private VNCharacterUtils Vncharacter;
	@Override
	public KhoangGiaEntity getOne(long id) {
		return khoangGiaRepository.getOne(id);
	}

	@Override
	public List<KhoangGiaEntity> findAll() {
		List<KhoangGiaEntity> entities = khoangGiaRepository.findAll();
		return entities;
	}

	@Override
	@Transactional
	public KhoangGiaEntity save(KhoangGiaEntity entity) {
		entity.setCode(Vncharacter.removeAccent(entity.getName()));
		entity = khoangGiaRepository.save(entity);
		return entity;
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for(long id : ids) {
			khoangGiaRepository.deleteById(id);
		}
		
	}

	@Override
	@Transactional
	public KhoangGiaEntity update(KhoangGiaEntity entity) {
		KhoangGiaEntity oldEntity = khoangGiaRepository.getOne(entity.getId());
		oldEntity.setName(entity.getName());
		oldEntity.setCode(Vncharacter.removeAccent(entity.getName()));
		oldEntity.setMocDau(entity.getMocDau());
		oldEntity.setMocCuoi(entity.getMocCuoi());
		oldEntity = khoangGiaRepository.save(oldEntity);
		return oldEntity;
	}

	@Override
	public String getNameByCode(String code) {
		KhoangGiaEntity entity = khoangGiaRepository.findByCode(code);
		return entity.getName();
	}

}
