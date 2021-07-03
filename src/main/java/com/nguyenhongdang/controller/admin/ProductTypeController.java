package com.nguyenhongdang.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenhongdang.entity.ProductTypeEntity;
import com.nguyenhongdang.service.admin.IProductTypeService;

@Controller
public class ProductTypeController {
	@Autowired
	private IProductTypeService productTypeService;
	
	@GetMapping("/admin_listProductType")
	public String showProductTypes(Model model) {
		List<ProductTypeEntity> productTypes = productTypeService.findAll();
		model.addAttribute("productTypes", productTypes);
		return "admin/producttype/showProductType";
	}
	@GetMapping("/admin_addProductType")
	public String addProductType() {
		return "admin/producttype/addProductType";
	}
	@GetMapping("/admin_updateProductType")
	public String editProductType(@RequestParam(value = "id") long id , Model model) {
		ProductTypeEntity productType = productTypeService.getOne(id);
		model.addAttribute("productType", productType);
		return "admin/producttype/editProductType";
	}
}
