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

import com.nguyenhongdang.dto.BrandDTO;
import com.nguyenhongdang.entity.BrandEntity;
import com.nguyenhongdang.service.admin.IBrandService;

@RestController
public class BrandAPI {
	@Autowired
	private IBrandService brandService;
	@PostMapping("/brands")
	public BrandEntity add(@Valid @RequestBody BrandDTO dto , HttpServletRequest request) throws IOException {
		return brandService.save(dto,request);
	}

	@PutMapping("/brands")
	public BrandEntity update(@RequestBody BrandDTO dto,HttpServletRequest request) throws IOException {
		return brandService.update(dto,request);
	}

	@DeleteMapping("/brands")
	public void delete(@RequestBody long[] ids) {
		brandService.delete(ids);
	}
}
