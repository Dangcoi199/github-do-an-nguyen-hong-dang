package com.nguyenhongdang.api.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenhongdang.entity.KhoangGiaEntity;
import com.nguyenhongdang.service.admin.IKhoangGiaService;


@RestController
public class KhoangGiaAPI {
	@Autowired
	private IKhoangGiaService khoangGiaService;
	@PostMapping("/khoangGias")
	public KhoangGiaEntity save(@Valid @RequestBody KhoangGiaEntity entity) {
		return khoangGiaService.save(entity);
	}
	@DeleteMapping("/khoangGias")
	public void deleteCate(@RequestBody long[] ids) {
		khoangGiaService.delete(ids);
	}
	@PutMapping("/khoangGias")
	public KhoangGiaEntity update(@RequestBody KhoangGiaEntity entity) {
		return khoangGiaService.update(entity);
	}
}
