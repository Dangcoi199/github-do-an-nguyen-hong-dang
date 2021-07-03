package com.nguyenhongdang.service.admin.impl;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenhongdang.conveter.SliderConveter;
import com.nguyenhongdang.dto.SliderDTO;
import com.nguyenhongdang.entity.SliderEntity;
import com.nguyenhongdang.repository.ISliderRepository;
import com.nguyenhongdang.service.admin.ISliderService;
import com.nguyenhongdang.utils.UploadFile;

@SuppressWarnings("deprecation")
@Service
public class SliderService implements ISliderService {

	@Autowired
	private ISliderRepository repository;
	@Autowired
	private SliderConveter conveter;
	@Autowired
	private UploadFile uploads;
	@Autowired
	EntityManager entityManager;
	
	@Override
	public SliderEntity getOne(long id) {
		// TODO Auto-generated method stub
		return repository.getOne(id);
	}

	@Override
	public List<SliderEntity> findAll() {
		List<SliderEntity> entities = repository.findAll();
		return entities;
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for(long id :ids) {
			repository.deleteById(id);
		}
		
	}

	@Override
	@Transactional
	public SliderEntity save(SliderDTO dto, HttpServletRequest request) throws IOException {
		SliderEntity entity = conveter.toEntity(dto);
		uploads.saveFile(dto.getBytes(), request, "slider", dto.getImages());
		entity = repository.save(entity);
		return entity;
	}

	@Override
	@Transactional
	public SliderEntity update(SliderDTO dto, HttpServletRequest request) throws IOException {
		SliderEntity oldEntity = repository.getOne(dto.getId());
		String oldImage = oldEntity.getImages();
		oldEntity = conveter.toEntity(oldEntity, dto);
		if(dto.getImages() == null) {
			oldEntity.setImages(oldImage);
		}else {
			uploads.saveFile(dto.getBytes(), request, "slider", dto.getImages());
		}
		oldEntity = repository.save(oldEntity);
		return oldEntity;
	}

	@Override
	public List<SliderEntity> getSlider() {
		String hql = " FROM SliderEntity u ORDER BY u.id DESC";
		Session sesstion = entityManager.unwrap(Session.class);
		Query<SliderEntity> query = sesstion.createQuery(hql, SliderEntity.class);
		query.setMaxResults(5);
		return query.getResultList();
	}

}
