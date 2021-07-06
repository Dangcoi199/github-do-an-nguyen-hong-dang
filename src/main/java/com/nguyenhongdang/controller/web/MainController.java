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
import com.nguyenhongdang.entity.RoleEntity;
import com.nguyenhongdang.entity.UserEntity;
import com.nguyenhongdang.repository.RoleRepository;
import com.nguyenhongdang.repository.UserRepository;
import com.nguyenhongdang.service.admin.IBrandService;
import com.nguyenhongdang.service.admin.IHoaDonBanService;
import com.nguyenhongdang.service.admin.IRoleService;
import com.nguyenhongdang.service.admin.IUserService;
import com.nguyenhongdang.service.web.IDanhGiaService;

@Controller
public class MainController {
	
	@Autowired
	private IBrandService brandService;
	@Autowired
	private IHoaDonBanService hbdService;
	@Autowired
	private IDanhGiaService danhGiaService;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
	@GetMapping("/register")
	public String register() {
		return "register";
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
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername("hoangthuy99");
		userEntity.setFullname("Hoàng Thị Thúy");
		userEntity.setYearOfBirth("1999");
		userEntity.setEmail("hoangthuy99@gmail.com");
		userEntity.setPassword("$2a$10$4gzEXtD1yJKp3cxekslVZ.ISeO6Cq73BxbeT7lX5gR8H8SKegM9xu");
		userEntity.setGender("Nam");
		userEntity.setStatus(1);
		List<RoleEntity> roles = roleRepo.findByCode("ROLE_ADMIN");
		userEntity.setRoles(roles);
		userRepo.save(userEntity);
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
