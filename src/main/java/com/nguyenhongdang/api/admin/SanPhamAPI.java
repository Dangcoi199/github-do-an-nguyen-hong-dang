package com.nguyenhongdang.api.admin;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenhongdang.dto.DiscountDTO;
import com.nguyenhongdang.dto.SanPhamDTO;
import com.nguyenhongdang.dto.UpdateDiscountDTO;
import com.nguyenhongdang.entity.SanPhamEntity;
import com.nguyenhongdang.service.admin.ISanPhamService;

@RestController
public class SanPhamAPI {
	@Autowired
	private ISanPhamService service;
	@PostMapping("/sanphams")
	public SanPhamEntity add(@Valid @RequestBody SanPhamDTO dto,HttpServletRequest request)  {
		return service.save(dto, request);
	}
	@PutMapping("/sanphams")
	public SanPhamEntity update(@RequestBody SanPhamDTO dto,HttpServletRequest request)  {
		return service.update(dto, request);
	}
	@DeleteMapping("/sanphams")
	public void delete(@RequestBody long[] ids) {
		service.delete(ids);
	}
	@PostMapping("/productSales")
	public UpdateDiscountDTO addProductSale(@Valid @RequestBody UpdateDiscountDTO dto)  {		
		return service.updateDiscount(dto);
	}
	@PutMapping("/updateProductStatus")
	public SanPhamEntity updateStatus(@RequestBody long id)  {
		return service.updateStatus(id);
	}
	@PostMapping("/addDiscount")
	public DiscountDTO addDiscount(@Valid @RequestBody DiscountDTO dto)  {
		return service.saveDiscount(dto);	
	}
	@DeleteMapping("/deleteDiscount")
	public void delete(@RequestBody long saleProductId) {
		service.deleteDiscount(saleProductId);
	}
	
}
