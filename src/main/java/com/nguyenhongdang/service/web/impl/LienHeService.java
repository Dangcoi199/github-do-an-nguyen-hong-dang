package com.nguyenhongdang.service.web.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenhongdang.constant.TrangThaiLienHeConstant;
import com.nguyenhongdang.entity.LienHeEntity;
import com.nguyenhongdang.repository.ILienHeRepository;
import com.nguyenhongdang.service.web.ILienHeService;

@Service("serviceOfWeb")
public class LienHeService implements ILienHeService {
	@Autowired
	private ILienHeRepository repository;

	@Override
	@Transactional
	public LienHeEntity add(LienHeEntity entity) {
		entity.setTrangThai(TrangThaiLienHeConstant.ChuaPhanHoi);
		return repository.save(entity);
	}

}
