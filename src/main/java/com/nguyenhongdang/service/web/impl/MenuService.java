package com.nguyenhongdang.service.web.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenhongdang.dto.MenuDTO;
import com.nguyenhongdang.repository.IBrandRepository;
import com.nguyenhongdang.repository.IKhoangGiaRepository;
import com.nguyenhongdang.repository.ILoaiDayRepository;
import com.nguyenhongdang.repository.ProductTypeRepository;
import com.nguyenhongdang.service.web.IMenuService;

@Service
public class MenuService implements IMenuService{

	@Autowired
	private IBrandRepository brandRepo;
	@Autowired
	private IKhoangGiaRepository khoangGiaRepo;
	@Autowired
	private ILoaiDayRepository loaiDayRepo;
	@Autowired
	private ProductTypeRepository productTypeRepo;
	@Override
	public MenuDTO loadMenu() {
		MenuDTO dto = new MenuDTO();
		dto.setBrands(brandRepo.findAll());
		dto.setKhoangGias(khoangGiaRepo.findAll());
		dto.setLoaiDays(loaiDayRepo.findAll());
		dto.setLoaiSps(productTypeRepo.findAll());
		return dto;
	}

}
