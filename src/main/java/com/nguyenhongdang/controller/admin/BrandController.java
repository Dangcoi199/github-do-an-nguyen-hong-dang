package com.nguyenhongdang.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenhongdang.entity.BrandEntity;
import com.nguyenhongdang.service.admin.IBrandService;


@Controller
public class BrandController {
	@Autowired
	private IBrandService brandService;

	@GetMapping(value = "/admin_listBrand")
	public String showList(Model model) {
		List<BrandEntity> brands = brandService.findAll();
		model.addAttribute("brands", brands);
		return "admin/brand/showlist";
	}

	@GetMapping(value = "/admin_addBrand")
	public String add() {
		return "admin/brand/add";
	}

	@GetMapping("/admin_updateBrand")
	public String edit(@RequestParam(value = "id") long id, Model model) {
		BrandEntity brand = brandService.getOne(id);
		model.addAttribute("brand", brand);
		return "admin/brand/edit";
	}
}
