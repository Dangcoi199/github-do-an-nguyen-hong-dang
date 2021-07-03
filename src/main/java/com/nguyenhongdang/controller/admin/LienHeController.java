package com.nguyenhongdang.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenhongdang.entity.LienHeEntity;
import com.nguyenhongdang.service.admin.ILienHeService;

@Controller
public class LienHeController {
	@Autowired
	private ILienHeService service;

	@GetMapping(value = "/admin_listLienHe")
	public String show(Model model) {
		List<LienHeEntity> lienHes = service.findAll();
		model.addAttribute("lienHes", lienHes);
		return "admin/lienhe/showlist";
	}

	@GetMapping("/admin_chiTietLienHe")
	public String edit(@RequestParam(value = "id") long id, Model model) {
		LienHeEntity lienHe = service.getOne(id);
		model.addAttribute("lienHe", lienHe);
		return "admin/lienhe/mailing";
	}
	
	@GetMapping(value = "/admin_listLienHeStatus")
	public String showList(Model model) {
		List<LienHeEntity> lienHes = service.findAllByStatus();
		model.addAttribute("lienHes", lienHes);
		return "admin/lienhe/showlist";
	}
}
