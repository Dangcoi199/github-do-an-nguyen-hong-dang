package com.nguyenhongdang.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenhongdang.entity.KieuDongHoEntity;
import com.nguyenhongdang.repository.IKieuDongHoRepository;
import com.nguyenhongdang.service.admin.IKieuDongHoService;
import com.nguyenhongdang.utils.VNCharacterUtils;

@Service
public class KieuDongHoService implements IKieuDongHoService {

	@Autowired
	private IKieuDongHoRepository repository;
	@Autowired
	private VNCharacterUtils Vncharacter;
	@Override
	public KieuDongHoEntity getOne(long id) {
		return repository.getOne(id);
	}

	@Override
	public List<KieuDongHoEntity> findAll() {
		List<KieuDongHoEntity> entities = repository.findAll();
		return entities;
	}

	@Override
	@Transactional
	public KieuDongHoEntity save(KieuDongHoEntity entity) {
		entity.setCode(Vncharacter.removeAccent(entity.getName()));
		return repository.save(entity);
	}

	@Override
	@Transactional
	public KieuDongHoEntity update(KieuDongHoEntity entity) {
		KieuDongHoEntity oldEntity = repository.getOne(entity.getId());
		oldEntity.setName(entity.getName());
		oldEntity.setCode(Vncharacter.removeAccent(entity.getName()));
		return repository.save(oldEntity);
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for(long id :ids ) {
			repository.deleteById(id);
		}
		
	}

}
