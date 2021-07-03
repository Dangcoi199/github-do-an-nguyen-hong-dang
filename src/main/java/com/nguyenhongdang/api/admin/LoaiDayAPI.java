package com.nguyenhongdang.api.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenhongdang.entity.LoaiDayEntity;
import com.nguyenhongdang.service.admin.ILoaiDayService;

@RestController
public class LoaiDayAPI {
	@Autowired
	private ILoaiDayService loaiDayService;

	@PostMapping("/loaiDays")
	public LoaiDayEntity save(@Valid @RequestBody LoaiDayEntity kieuDH) {
		return loaiDayService.save(kieuDH);
	}

	@DeleteMapping("/loaiDays")
	public void deleteCate(@RequestBody long[] ids) {
		loaiDayService.delete(ids);
	}

	@PutMapping("/loaiDays")
	public LoaiDayEntity update(@RequestBody LoaiDayEntity cate) {
		return loaiDayService.update(cate);
	}
}
