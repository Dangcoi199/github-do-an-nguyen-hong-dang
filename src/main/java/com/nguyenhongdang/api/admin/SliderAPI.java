package com.nguyenhongdang.api.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenhongdang.dto.SliderDTO;
import com.nguyenhongdang.entity.SliderEntity;
import com.nguyenhongdang.service.admin.ISliderService;

@RestController
public class SliderAPI {
	@Autowired
	private ISliderService service;
	@PostMapping("/sliders")
	public SliderEntity add(@Valid @RequestBody SliderDTO dto , HttpServletRequest request) throws IOException {
		return service.save(dto,request);
	}

	@PutMapping("/sliders")
	public SliderEntity update(@RequestBody SliderDTO dto,HttpServletRequest request) throws IOException {
		return service.update(dto,request);
	}

	@DeleteMapping("/sliders")
	public void delete(@RequestBody long[] ids) {
		service.delete(ids);
	}
}
