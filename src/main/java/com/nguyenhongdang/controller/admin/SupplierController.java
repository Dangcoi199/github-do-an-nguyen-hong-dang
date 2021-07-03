package com.nguyenhongdang.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenhongdang.entity.SupplierEntity;
import com.nguyenhongdang.service.admin.ISupplierService;

@Controller
public class SupplierController {
	@Autowired
	private ISupplierService service;

	@GetMapping(value = "/admin_listSupplier")
	public String showList(Model model) {
		List<SupplierEntity> suppliers = service.findAll();
		model.addAttribute("suppliers", suppliers);
		return "admin/nhacungcap/showlist";
	}

	@GetMapping(value = "/admin_addSupplier")
	public String add() {
		return "admin/nhacungcap/add";
	}

	@GetMapping("/admin_updateSupplier")
	public String edit(@RequestParam(value = "id") long id, Model model) {
		SupplierEntity supplier = service.getOne(id);
		model.addAttribute("supplier", supplier);
		return "admin/nhacungcap/edit";
	}
}
