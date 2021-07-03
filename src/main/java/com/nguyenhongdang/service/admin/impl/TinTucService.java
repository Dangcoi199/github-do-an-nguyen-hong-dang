package com.nguyenhongdang.service.admin.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenhongdang.constant.TinTucConstant;
import com.nguyenhongdang.dto.TinTucDTO;
import com.nguyenhongdang.entity.TinTucEntity;
import com.nguyenhongdang.repository.ITinTucRepository;
import com.nguyenhongdang.service.admin.ITinTucService;
import com.nguyenhongdang.utils.UploadFile;
import com.nguyenhongdang.utils.VNCharacterUtils;

@Service("tinTucServiceOfAdmin")
public class TinTucService implements ITinTucService {
	@Autowired
	EntityManager entityManager;
	@Autowired
	private ITinTucRepository repository;
	@Autowired
	private VNCharacterUtils vnCharacter;
	@Autowired
	private UploadFile uploadFile;

	@Override
	public List<TinTucEntity> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional
	public TinTucEntity save(TinTucDTO dto,HttpServletRequest request) {
		TinTucEntity entity = new TinTucEntity();
		entity.setTitle(dto.getTitle());
		entity.setCode(vnCharacter.removeAccent(dto.getTitle()));
		entity.setContent(dto.getContent());
		entity.setShortDescription(dto.getShortDescription());
		entity.setShowDetail(0);
		entity.setDanhMuc(dto.getDanhMuc());
		entity.setImage(dto.getImage());		
		try {
			uploadFile.saveFile(dto.getBytes(), request, "tintuc", dto.getImage());
			entity = repository.save(entity);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	@Transactional
	public TinTucEntity update(TinTucDTO dto,HttpServletRequest request) {
		TinTucEntity oldEntity = repository.getOne(dto.getId());
		String oldImage= oldEntity.getImage();
		oldEntity.setTitle(dto.getTitle());
		oldEntity.setCode(vnCharacter.removeAccent(dto.getTitle()));
		oldEntity.setContent(dto.getContent());
		oldEntity.setShortDescription(dto.getShortDescription());
		oldEntity.setDanhMuc(dto.getDanhMuc());
		if(dto.getImage() == null) {
			oldEntity.setImage(oldImage);
		}else {
			try {
				uploadFile.saveFile(dto.getBytes(), request, "tintuc", dto.getImage());
				oldEntity.setImage(dto.getImage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
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
	public TinTucEntity getOne(long id) {
		return repository.getOne(id);
	}
	@Override
	public List<String> getDanhMuc() {
		List<String> danhMucs = new ArrayList<>();
		danhMucs.add(TinTucConstant.tinTuc);
		danhMucs.add(TinTucConstant.reviewSP);
		danhMucs.add(TinTucConstant.chungNhan_ThuongHieu);
		danhMucs.add(TinTucConstant.gioiThieu);
		return danhMucs;
	}

}
