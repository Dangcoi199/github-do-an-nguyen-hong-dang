package com.nguyenhongdang.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nguyenhongdang.conveter.ProductEntityTransferDTO;
import com.nguyenhongdang.dto.ProductTransferDTO;
import com.nguyenhongdang.entity.SaleEntity;
import com.nguyenhongdang.entity.SanPhamEntity;
import com.nguyenhongdang.service.admin.ISaleService;
import com.nguyenhongdang.service.admin.ISanPhamService;

@Controller
public class DiscountController {
	@Autowired
	private ISaleService saleService;
	
	@Autowired
	private ISanPhamService sanPhamService;
	
	@Autowired
	private ProductEntityTransferDTO productToDTO;
	
	@GetMapping(value = "/admin_addDiscount")
	public String addDiscount(Model model) {
		List<SaleEntity> saleEvents = saleService.findAll();
		model.addAttribute("saleEvents", saleEvents);
		List<SanPhamEntity> productEntities = sanPhamService.findAll();
		List<ProductTransferDTO> products = productToDTO.getListProductNotSale(productEntities);
		model.addAttribute("products",products);
		return "admin/discount/add";
	}
}
