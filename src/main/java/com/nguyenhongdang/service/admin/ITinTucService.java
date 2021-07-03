package com.nguyenhongdang.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.nguyenhongdang.dto.TinTucDTO;
import com.nguyenhongdang.entity.TinTucEntity;

public interface ITinTucService {
	List<TinTucEntity> findAll();
	TinTucEntity save(TinTucDTO dto,HttpServletRequest request);
	TinTucEntity update(TinTucDTO dto,HttpServletRequest request);
	void delete(long[] ids);
	TinTucEntity getOne(long id);
	List<String> getDanhMuc();
	
}
