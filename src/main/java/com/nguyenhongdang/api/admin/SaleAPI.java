package com.nguyenhongdang.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenhongdang.entity.SaleEntity;
import com.nguyenhongdang.service.admin.ISaleService;

@RestController
public class SaleAPI {
	@Autowired
	private ISaleService service;

	@PostMapping("/sales")
	public SaleEntity add(@RequestBody SaleEntity entity) {
		return service.save(entity);
	}

	@PutMapping("/sales")
	public SaleEntity update(@RequestBody SaleEntity entity) {
		return service.update(entity);
	}

	@DeleteMapping("/sales")
	public void delete(@RequestBody long[] ids) {
		service.delete(ids);
	}
}
