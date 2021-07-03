package com.nguyenhongdang.api.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenhongdang.dto.ProductTypeDTO;
import com.nguyenhongdang.service.admin.IProductTypeService;

@RestController
public class ProductTypeAPI {
	@Autowired
	private IProductTypeService productTypeService;
	
	@PostMapping("/productTypes")
	public ProductTypeDTO addProductType(@Valid @RequestBody ProductTypeDTO dto) {
		return productTypeService.save(dto);
	}
	
	@PutMapping("/productTypes")
	public ProductTypeDTO updateProductType(@RequestBody ProductTypeDTO dto) {
		return productTypeService.update(dto);
	}
	@DeleteMapping("/productTypes")
	public void deleteProductType(@RequestBody long[] ids) {
		productTypeService.delete(ids);
	}
}
