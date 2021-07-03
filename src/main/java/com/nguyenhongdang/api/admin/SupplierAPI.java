package com.nguyenhongdang.api.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenhongdang.entity.SupplierEntity;
import com.nguyenhongdang.service.admin.ISupplierService;


@RestController
public class SupplierAPI {
	@Autowired
	private ISupplierService service;

	@PostMapping("/suppliers")
	public SupplierEntity add(@Valid @RequestBody SupplierEntity entity)  {
		return service.save(entity);
	}

	@PutMapping("/suppliers")
	public SupplierEntity update(@RequestBody SupplierEntity entity)  {
		return service.update(entity);
	}

	@DeleteMapping("/suppliers")
	public void delete(@RequestBody long[] ids) {
		service.delete(ids);
	}
}
