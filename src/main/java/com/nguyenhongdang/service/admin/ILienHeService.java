package com.nguyenhongdang.service.admin;

import java.util.List;

import com.nguyenhongdang.dto.SendMailDTO;
import com.nguyenhongdang.entity.LienHeEntity;

public interface ILienHeService {
	List<LienHeEntity> findAll();
	void delete(long[] ids);
	LienHeEntity mailing(SendMailDTO dto);
	LienHeEntity getOne(long id);
	List<LienHeEntity> findAllByStatus();
}
