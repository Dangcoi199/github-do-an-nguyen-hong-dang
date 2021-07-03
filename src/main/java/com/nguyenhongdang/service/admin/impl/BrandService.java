package com.nguyenhongdang.service.admin.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenhongdang.conveter.BrandConveter;
import com.nguyenhongdang.dto.BrandDTO;
import com.nguyenhongdang.entity.BrandEntity;
import com.nguyenhongdang.repository.IBrandRepository;
import com.nguyenhongdang.service.admin.IBrandService;
import com.nguyenhongdang.utils.UploadFile;
import com.nguyenhongdang.utils.VNCharacterUtils;

@Service
public class BrandService implements IBrandService {
	@Autowired
	private IBrandRepository repository;
	@Autowired
	private BrandConveter conveter;
	@Autowired
	private VNCharacterUtils Vncharacter;
	@Autowired
	private UploadFile uploadFile;
	

	@Override
	public BrandEntity getOne(long id) {
		return repository.getOne(id);
	}

	@Override
	public List<BrandEntity> findAll() {
		List<BrandEntity> entities = repository.findAll();
		return entities;
	}

	@Override
	@Transactional
	public BrandEntity save(BrandDTO dto ,HttpServletRequest request) throws IOException {
		BrandEntity entity = conveter.toEntity(dto);
		entity.setCode(Vncharacter.removeAccent(entity.getBrandName()));
		uploadFile.saveFile(dto.getBytes(), request, "brand", dto.getImage());
		entity = repository.save(entity);
		return entity;
	}

	@Override
	@Transactional
	public BrandEntity update(BrandDTO dto,HttpServletRequest request) throws IOException {
		BrandEntity oldEntity = repository.getOne(dto.getId());
		String oldImage= oldEntity.getImage();
		oldEntity = conveter.toEntity(oldEntity, dto);
		oldEntity.setCode(Vncharacter.removeAccent(oldEntity.getBrandName()));
		if(dto.getImage() == null) {
			oldEntity.setImage(oldImage);
		}else {
			uploadFile.saveFile(dto.getBytes(), request, "brand", dto.getImage());
		}
		oldEntity = repository.save(oldEntity);
		return oldEntity;
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for (long id : ids) {
			repository.deleteById(id);
		}
	}

	@Override
	public BrandEntity findByCode(String code) {
		return repository.findByCode(code);
	}
	

}
