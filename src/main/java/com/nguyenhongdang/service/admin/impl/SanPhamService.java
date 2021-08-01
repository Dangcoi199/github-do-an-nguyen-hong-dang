package com.nguyenhongdang.service.admin.impl;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenhongdang.constant.GenderConstant;
import com.nguyenhongdang.conveter.SanPhamConveter;
import com.nguyenhongdang.dto.DiscountDTO;
import com.nguyenhongdang.dto.SanPhamDTO;
import com.nguyenhongdang.dto.UpdateDiscountDTO;
import com.nguyenhongdang.entity.BaoHanhEntity;
import com.nguyenhongdang.entity.BrandEntity;
import com.nguyenhongdang.entity.ChatLieuMatEntity;
import com.nguyenhongdang.entity.ChatLieuVoEntity;
import com.nguyenhongdang.entity.ImagesEntity;
import com.nguyenhongdang.entity.KieuDongHoEntity;
import com.nguyenhongdang.entity.LoaiDayEntity;
import com.nguyenhongdang.entity.ProductSaleEntity;
import com.nguyenhongdang.entity.ProductTypeEntity;
import com.nguyenhongdang.entity.SaleEntity;
import com.nguyenhongdang.entity.SanPhamEntity;
import com.nguyenhongdang.repository.IBaoHanhRepository;
import com.nguyenhongdang.repository.IBrandRepository;
import com.nguyenhongdang.repository.IChatLieuMatRepository;
import com.nguyenhongdang.repository.IChatLieuVoRepository;
import com.nguyenhongdang.repository.IKieuDongHoRepository;
import com.nguyenhongdang.repository.ILoaiDayRepository;
import com.nguyenhongdang.repository.IProductSaleRepository;
import com.nguyenhongdang.repository.ISaleReposotory;
import com.nguyenhongdang.repository.ISanPhamRepository;
import com.nguyenhongdang.repository.ImagesRepository;
import com.nguyenhongdang.repository.ProductTypeRepository;
import com.nguyenhongdang.service.admin.ISanPhamService;
import com.nguyenhongdang.utils.StringToDate;
import com.nguyenhongdang.utils.UploadFile;
import com.nguyenhongdang.utils.VNCharacterUtils;

@Service("sanPhamServiceOfAdmin")
public class SanPhamService implements ISanPhamService {

	@Autowired
	private ISanPhamRepository repository;

	@Autowired
	private IBrandRepository brandRepo;

	@Autowired
	private IChatLieuMatRepository chatLieuMatRepo;

	@Autowired
	private IChatLieuVoRepository chatLieuVoRepo;


	@Autowired
	private IKieuDongHoRepository kieuDHRepo;

	@Autowired
	private ILoaiDayRepository loaiDayRepo;

	@Autowired
	private ProductTypeRepository productTypeRepo;

	@Autowired
	private VNCharacterUtils Vncharacter;

	@Autowired
	private UploadFile uploads;

	@Autowired
	private SanPhamConveter sanPhamConveter;

	@Autowired
	EntityManager entityManager;

	@Autowired
	private ImagesRepository imagesRepo;

	@Autowired
	private StringToDate stringToDate;

	@Autowired
	private ISaleReposotory saleRepo;

	@Autowired
	private IBaoHanhRepository baoHanhRepo;
	
	@Autowired
	private IProductSaleRepository saleProductRepo;

	@Override
	public SanPhamEntity getOne(long id) {
		return repository.getOne(id);
	}

	@Override
	public List<SanPhamEntity> findAll() {
		List<SanPhamEntity> entities = repository.findAll();
		return entities;
	}

	@Override
	@Transactional
	public SanPhamEntity save(SanPhamDTO dto, HttpServletRequest request) {

		SanPhamEntity entity = sanPhamConveter.toEntity(dto);
		if (dto.getGender() == 1) {
			entity.setGender(GenderConstant.MALE);
		} else if (dto.getGender() == 0) {
			entity.setGender(GenderConstant.FEMALE);
		} else {
			entity.setGender(GenderConstant.Dong_Ho_Doi);
		}
		entity.setQuantity(0);
		entity.setShowDetail(0);
		entity.setCode(Vncharacter.removeAccent(dto.getName()));
		BrandEntity brand = brandRepo.findByCode(dto.getBrandCode());
		entity.setBrand(brand);
		ChatLieuMatEntity chatLieuMat = chatLieuMatRepo.findByCode(dto.getChatLieuMatCode());
		entity.setChatLieuMat(chatLieuMat);
		ChatLieuVoEntity chatLieuVo = chatLieuVoRepo.findByCode(dto.getChatLieuVoCode());
		entity.setChatLieuVo(chatLieuVo);
		KieuDongHoEntity kieuDH = kieuDHRepo.findByCode(dto.getKieuDongHoCode());
		entity.setKieuDongHo(kieuDH);
		LoaiDayEntity loaiDay = loaiDayRepo.findByCode(dto.getLoaiDayCode());
		entity.setLoaiDay(loaiDay);
		ProductTypeEntity productType = productTypeRepo.findByCode(dto.getProductTypeCode());
		entity.setProductType(productType);
		BaoHanhEntity baoHanh = baoHanhRepo.findByCode(dto.getBaoHanhCode());
		entity.setBaohanh(baoHanh);
		try {
			uploads.saveFile(dto.getBytes(), request, "sanpham", dto.getImage());
			uploads.saveMultipleFiles(dto.getFileByteDetails(), request, "chitietanh", dto.getFileNameDetails());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		entityManager.persist(entity);
		entity = repository.findByName(entity.getName());
		for (int i = 0; i < dto.getFileByteDetails().length; i++) {
			ImagesEntity imagesEntity = new ImagesEntity();
			imagesEntity.setImageName(dto.getFileNameDetails()[i]);
			imagesEntity.setProduct(entity);
			entityManager.persist(imagesEntity);
		}
		return entity;
	}

	@Override
	@Transactional
	public SanPhamEntity update(SanPhamDTO dto, HttpServletRequest request) {
		SanPhamEntity oldEntity = repository.getOne(dto.getId());
		oldEntity.setName(dto.getName());
		oldEntity.setCode(Vncharacter.removeAccent(dto.getName()));
		oldEntity.setPrice(dto.getPrice());
		oldEntity.setContent(dto.getContent());
		if (dto.getGender() == 1) {
			oldEntity.setGender(GenderConstant.MALE);
		} else if (dto.getGender() == 0) {
			oldEntity.setGender(GenderConstant.FEMALE);
		} else {
			oldEntity.setGender(GenderConstant.Dong_Ho_Doi);
		}
		if (dto.getImage() != null) {
			oldEntity.setImage(dto.getImage());
			try {
				uploads.saveFile(dto.getBytes(), request, "sanpham", dto.getImage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		BrandEntity brand = brandRepo.findByCode(dto.getBrandCode());
		oldEntity.setBrand(brand);
		ChatLieuMatEntity chatLieuMat = chatLieuMatRepo.findByCode(dto.getChatLieuMatCode());
		oldEntity.setChatLieuMat(chatLieuMat);
		ChatLieuVoEntity chatLieuVo = chatLieuVoRepo.findByCode(dto.getChatLieuVoCode());
		oldEntity.setChatLieuVo(chatLieuVo);
		KieuDongHoEntity kieuDH = kieuDHRepo.findByCode(dto.getKieuDongHoCode());
		oldEntity.setKieuDongHo(kieuDH);
		LoaiDayEntity loaiDay = loaiDayRepo.findByCode(dto.getLoaiDayCode());
		oldEntity.setLoaiDay(loaiDay);
		ProductTypeEntity productType = productTypeRepo.findByCode(dto.getProductTypeCode());
		oldEntity.setProductType(productType);
		BaoHanhEntity baoHanh = baoHanhRepo.findByCode(dto.getBaoHanhCode());
		oldEntity.setBaohanh(baoHanh);
		entityManager.persist(oldEntity);
		SanPhamEntity entity = repository.getOne(dto.getId());
		if (dto.getFileByteDetails() != null) {
			uploads.saveMultipleFiles(dto.getFileByteDetails(), request, "chitietanh", dto.getFileNameDetails());
			List<ImagesEntity> images = imagesRepo.findByProduct(entity);
			for (ImagesEntity image : images) {
				entityManager.remove(image);
			}
			for (int i = 0; i < dto.getFileByteDetails().length; i++) {
				ImagesEntity imagesEntity = new ImagesEntity();
				imagesEntity.setImageName(dto.getFileNameDetails()[i]);
				imagesEntity.setProduct(entity);
				entityManager.persist(imagesEntity);
			}
		}
		return entity;
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for (long id : ids) {
			repository.deleteById(id);
		}

	}

	@Override
	public SanPhamEntity findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	@Transactional
	public SanPhamEntity updateStatus(long id) {
		SanPhamEntity entity = repository.getOne(id);
		if(entity.getStatus() == 1) {
			entity.setStatus(0);
		}
		else {
			entity.setStatus(1);			
		}
		return repository.save(entity);
	}

	@Override
	@Transactional
	public DiscountDTO saveDiscount(DiscountDTO dto) {
		for(long id : dto.getIds()) {
			ProductSaleEntity productSale = new ProductSaleEntity();
			productSale.setBegin(stringToDate.stringtoDate(dto.getBegin()));
			productSale.setEnd(stringToDate.stringtoDate(dto.getEnd()));
			SaleEntity saleEntity = saleRepo.findByCode(dto.getSaleCode());
			productSale.setSale(saleEntity);
			SanPhamEntity product = repository.getOne(id);
			productSale.setProduct(product);
			entityManager.persist(productSale);
		}
		return dto;
	}

	@Override
	@Transactional
	public UpdateDiscountDTO updateDiscount(UpdateDiscountDTO dto) {
		ProductSaleEntity entity = entityManager.find(ProductSaleEntity.class,dto.getSaleProductId());
		SaleEntity sale = saleRepo.findByCode(dto.getSaleCode());
		entity.setSale(sale);
		entity.setBegin(stringToDate.stringtoDate(dto.getBegin()));
		entity.setEnd(stringToDate.stringtoDate(dto.getEnd()));
		entityManager.merge(entity);
		return dto;
	}

	@Override
	@Transactional
	public void deleteDiscount(long saleProductId) {
		saleProductRepo.deleteById(saleProductId);
	}
}
