package com.nguyenhongdang.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenhongdang.entity.BaoHanhEntity;
import com.nguyenhongdang.service.admin.IBaoHanhService;
@Controller
public class BaoHanHController {
	@Autowired
	private IBaoHanhService service;

	@GetMapping(value = "/listBH")
	public String show(Model model) {
		List<BaoHanhEntity> baohanhs = service.findAll();
		model.addAttribute("baohanhs", baohanhs);
		return "admin/baohanh/showlist";
	}

	@GetMapping(value = "/addBH")
	public String add() {
		return "admin/baohanh/add";
	}

	@GetMapping("/updateBH")
	public String edit(@RequestParam(value = "id") long id, Model model) {
		BaoHanhEntity baoHanh = service.getOne(id);
		model.addAttribute("baoHanh", baoHanh);
		return "admin/baohanh/edit";
	}
}
