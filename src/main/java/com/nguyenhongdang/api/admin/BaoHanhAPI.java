package com.nguyenhongdang.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenhongdang.entity.BaoHanhEntity;
import com.nguyenhongdang.service.admin.IBaoHanhService;

@RestController
public class BaoHanhAPI {
	@Autowired
	private IBaoHanhService service;
	@PostMapping("/baoHanhs")
	public BaoHanhEntity save(@RequestBody BaoHanhEntity baoHanh) {
		return service.save(baoHanh);
	}
	@DeleteMapping("/baoHanhs")
	public void deleteCate(@RequestBody long[] ids) {
		service.delete(ids);
	}
	@PutMapping("/baoHanhs")
	public BaoHanhEntity update(@RequestBody BaoHanhEntity cate) {
		return service.update(cate);
	}
}
