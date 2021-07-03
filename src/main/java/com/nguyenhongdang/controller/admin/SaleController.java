package com.nguyenhongdang.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenhongdang.entity.SaleEntity;
import com.nguyenhongdang.service.admin.ISaleService;

@Controller
public class SaleController {
	@Autowired
	private ISaleService service;

	@GetMapping(value = "/listSale")
	public String showCategory(Model model) {
		List<SaleEntity> sales = service.findAll();
		model.addAttribute("sales", sales);
		return "admin/sale/showlist";
	}

	@GetMapping(value = "/addSale")
	public String add() {
		return "admin/sale/add";
	}

	@GetMapping("/updateSale")
	public String edit(@RequestParam(value = "id") long id, Model model) {
		SaleEntity sale = service.getOne(id);
		model.addAttribute("sale", sale);
		return "admin/sale/edit";
	}
}
