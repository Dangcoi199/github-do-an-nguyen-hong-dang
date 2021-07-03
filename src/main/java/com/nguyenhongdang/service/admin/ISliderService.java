package com.nguyenhongdang.service.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.nguyenhongdang.dto.SliderDTO;
import com.nguyenhongdang.entity.SliderEntity;

public interface ISliderService {
	SliderEntity getOne(long id);

	List<SliderEntity> findAll();

	SliderEntity save(SliderDTO dto,HttpServletRequest request)  throws IOException;

	SliderEntity update(SliderDTO dto,HttpServletRequest request)  throws IOException;

	void delete(long[] ids);
	
	List<SliderEntity> getSlider();
}
