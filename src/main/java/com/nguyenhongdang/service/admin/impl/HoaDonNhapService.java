package com.nguyenhongdang.service.admin.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenhongdang.dto.ChiTietHDNDTO;
import com.nguyenhongdang.dto.HoaDonNhapDTO;
import com.nguyenhongdang.entity.CTHoaDonNhapEntity;
import com.nguyenhongdang.entity.HoaDonNhapEntity;
import com.nguyenhongdang.entity.SanPhamEntity;
import com.nguyenhongdang.entity.SupplierEntity;
import com.nguyenhongdang.entity.TokenEntity;
import com.nguyenhongdang.repository.IHoaDonNhapRepository;
import com.nguyenhongdang.repository.ISanPhamRepository;
import com.nguyenhongdang.repository.ISupplierRepository;
import com.nguyenhongdang.repository.ITokenRepository;
import com.nguyenhongdang.service.admin.IHoaDonNhapService;
import com.nguyenhongdang.utils.Notification;
import com.nguyenhongdang.utils.ReadFileExcelUtil;
import com.nguyenhongdang.utils.VNCharacterUtils;

@Service
public class HoaDonNhapService implements IHoaDonNhapService {
	@Autowired
	private IHoaDonNhapRepository repository;

	@Autowired
	private ISupplierRepository supplierRepo;

	@Autowired
	private ReadFileExcelUtil excelUtil;

	@Autowired
	private ISanPhamRepository productRepo;

	@Autowired
	EntityManager entityManager;

	@Autowired
	private VNCharacterUtils Vncharacter;

	@Autowired
	private Notification sendNotification;
	@Autowired
	private ITokenRepository tokenRepo;

	@Override
	public HoaDonNhapEntity getOne(long id) {
		return repository.getOne(id);
	}

	@Override
	public List<HoaDonNhapEntity> findAll() {
		List<HoaDonNhapEntity> entities = repository.findAll();
		return entities;
	}
	
	@Override
	@Transactional
	public HoaDonNhapEntity save(HoaDonNhapDTO dto) throws IOException {
		List<Long> productIds = new ArrayList<>();
		long tongTien = 0;
		SupplierEntity supplier = supplierRepo.findByCode(dto.getSupplierCode());
		HoaDonNhapEntity hoaDonNhapEntity = new HoaDonNhapEntity();
		hoaDonNhapEntity.setSupplier(supplier);
		hoaDonNhapEntity = repository.save(hoaDonNhapEntity);
		List<ChiTietHDNDTO> chiTietHDNs = excelUtil.readExcel(dto.getBytes(),dto.getExcelFileName());
		for(ChiTietHDNDTO chiTietHDNDTO : chiTietHDNs) {
			CTHoaDonNhapEntity chiTietHDN = new CTHoaDonNhapEntity();
			SanPhamEntity sanPhamEntity = productRepo.findByName(chiTietHDNDTO.getProductName());
			if(sanPhamEntity == null) {
				SanPhamEntity newProduct = new SanPhamEntity();
				newProduct.setName(chiTietHDNDTO.getProductName());
				newProduct.setCode(Vncharacter.removeAccent(chiTietHDNDTO.getProductName()));
				newProduct.setShowDetail(0);
				newProduct.setStatus(0);
				newProduct.setQuantity(chiTietHDNDTO.getSoLuong());
				newProduct = productRepo.save(newProduct);
				chiTietHDN.setProduct(newProduct);
			}else {
				chiTietHDN.setProduct(sanPhamEntity);
				sanPhamEntity.setQuantity(sanPhamEntity.getQuantity()+chiTietHDNDTO.getSoLuong());
				sanPhamEntity = productRepo.save(sanPhamEntity);
				productIds.add(sanPhamEntity.getId());
			}
			long thanhTien = chiTietHDNDTO.getDonGia() * chiTietHDNDTO.getSoLuong();
			chiTietHDN.setHoadonnhap(hoaDonNhapEntity);
			chiTietHDN.setSoLuong(chiTietHDNDTO.getSoLuong());
			chiTietHDN.setDonGia(chiTietHDNDTO.getDonGia());
			chiTietHDN.setThanhTien(thanhTien);
			entityManager.persist(chiTietHDN);
			tongTien = tongTien + thanhTien;
		}
		hoaDonNhapEntity.setTongTien(tongTien);
		hoaDonNhapEntity= repository.save(hoaDonNhapEntity);
		sendNotification.sendNoti(productIds);
		return hoaDonNhapEntity;
	}


	@Override
	@Transactional
	public HoaDonNhapEntity update(HoaDonNhapDTO dto) {
		HoaDonNhapEntity oldHDN = repository.getOne(dto.getId());
		SupplierEntity supplier = supplierRepo.findByCode(dto.getSupplierCode());
		oldHDN.setSupplier(supplier);
		return repository.save(oldHDN);
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for (long id : ids) {
			repository.deleteById(id);
		}

	}

	@Override
	@Transactional
	public HoaDonNhapEntity addChiTietHDN(ChiTietHDNDTO dto) {
		HoaDonNhapEntity hoaDonNhap = repository.getOne(dto.getHoaDonNhapId());
		SanPhamEntity sanPham = productRepo.findByName(dto.getProductName());
		CTHoaDonNhapEntity chiTiet_HDN = new CTHoaDonNhapEntity();
		chiTiet_HDN.setProduct(sanPham);
		chiTiet_HDN.setSoLuong(dto.getSoLuong());
		chiTiet_HDN.setDonGia(dto.getDonGia());
		chiTiet_HDN.setHoadonnhap(hoaDonNhap);
		long thanhTien= dto.getDonGia() * dto.getSoLuong();
		chiTiet_HDN.setThanhTien(thanhTien);
		entityManager.persist(chiTiet_HDN);
		//Thông báo đẩy
		List<TokenEntity> tokenEntity = tokenRepo.findByProductId(sanPham.getId());
		sendNotification.sendNotificationAdd(tokenEntity, sanPham.getCode());
		//Cập nhật số lượng
		sanPham.setQuantity(sanPham.getQuantity() + dto.getSoLuong());
		entityManager.merge(sanPham);
		//Cập nhật tổng tiền
		hoaDonNhap.setTongTien(hoaDonNhap.getTongTien()+thanhTien);
		hoaDonNhap = repository.save(hoaDonNhap);
		return hoaDonNhap;
	}
}
