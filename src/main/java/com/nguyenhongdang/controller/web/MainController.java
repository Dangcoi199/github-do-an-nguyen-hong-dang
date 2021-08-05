package com.nguyenhongdang.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenhongdang.entity.HoaDonBanEntity;
import com.nguyenhongdang.service.admin.IBrandService;
import com.nguyenhongdang.service.admin.IHoaDonBanService;
import com.nguyenhongdang.service.web.IDanhGiaService;

@Controller
public class MainController {
	
	@Autowired
	private IBrandService brandService;
	@Autowired
	private IHoaDonBanService hbdService;
	@Autowired
	private IDanhGiaService danhGiaService;
	
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	@GetMapping("/403")
	public String page403() {
		return "page403";
	}
	/* Lịch sử thương hiệu */
	@GetMapping("/historyBrand")
	public String historyBrand(Model model,@RequestParam(name = "brandCode") String brandCode) {
		model.addAttribute("brand", brandService.findByCode(brandCode));
		return "historyBrand";
	}
	
	/* Xem chi tiết hóa đơn từ email */
	@GetMapping("/order_detail_email")
	public String OrderDetailEmail(Model model,@RequestParam long id) {		
		HoaDonBanEntity hoaDonBan  = hbdService.getOne(id);
		model.addAttribute("hoaDonBan", hoaDonBan);
		return "order_detail_email";		
	}
	//Liên hệ
	@GetMapping("/lien-he")
	public String contact() {
		return "contact";
	}
	
	
	//Lịch sử mua hàng
	@GetMapping("/lich-su-mua-hang")
	public String lich_su(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		List<HoaDonBanEntity> hoaDonBans = hbdService.getByUserName(username);
		model.addAttribute("hoaDonBans", hoaDonBans);
		model.addAttribute("sizeItem", hoaDonBans.size());
		return "lich_su_mua_hang";
	}
	//Rating
	@GetMapping("/save_rating")
	public String rating(@RequestParam(name = "star") int star,@RequestParam(name = "product_id") long product_id) {
		danhGiaService.save(star, product_id);
		return "redirect:/product_detailById?id="+product_id;
	}

}
