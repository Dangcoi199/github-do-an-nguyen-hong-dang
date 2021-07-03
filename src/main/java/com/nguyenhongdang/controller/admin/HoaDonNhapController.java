package com.nguyenhongdang.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenhongdang.entity.HoaDonNhapEntity;
import com.nguyenhongdang.entity.SanPhamEntity;
import com.nguyenhongdang.entity.SupplierEntity;
import com.nguyenhongdang.service.admin.IHoaDonNhapService;
import com.nguyenhongdang.service.admin.ISanPhamService;
import com.nguyenhongdang.service.admin.ISupplierService;

@Controller
public class HoaDonNhapController {
	@Autowired
	private IHoaDonNhapService hdnService;

	
	@Autowired 
	private ISanPhamService productService;
	 
	@Autowired
	private ISupplierService supplierService;
	
	@GetMapping(value = "/admin_listHDN")
	public String showList(Model model) {
		List<HoaDonNhapEntity> hoadonnhaps = hdnService.findAll();
		model.addAttribute("hoadonnhaps", hoadonnhaps);
		return "admin/hoadonnhap/showlist";
	}

	@GetMapping(value = "/admin_addHDN")
	public String add(Model model) {
		List<SupplierEntity> suppliers = supplierService.findAll();
		model.addAttribute("suppliers", suppliers);
		return "admin/hoadonnhap/add";
	}

	@GetMapping("/admin_detailHDN")
	public String edit(@RequestParam(value = "id") long id, Model model) {
		HoaDonNhapEntity hoadonnhap = hdnService.getOne(id);
		model.addAttribute("hoadonnhap", hoadonnhap);
		return "admin/hoadonnhap/detail";
	}
	@GetMapping("/admin_addCTHDN")
	public String addChiTiet(@RequestParam(value = "id") long id, Model model) {
		List<SanPhamEntity> sanPhams = productService.findAll();
		model.addAttribute("sanPhams", sanPhams);
		model.addAttribute("hoaDonNhapId",id);
		return "admin/hoadonnhap/add_chi_tiet";
	}
}
