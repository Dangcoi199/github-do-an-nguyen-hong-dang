package com.nguyenhongdang.service.web.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenhongdang.constant.SystemConstant;
import com.nguyenhongdang.conveter.ProductEntityTransferDTO;
import com.nguyenhongdang.dto.ProductTransferDTO;
import com.nguyenhongdang.entity.KhoangGiaEntity;
import com.nguyenhongdang.entity.OrderDetailEntity;
import com.nguyenhongdang.entity.SanPhamEntity;
import com.nguyenhongdang.entity.UserEntity;
import com.nguyenhongdang.repository.IKhoangGiaRepository;
import com.nguyenhongdang.repository.ISanPhamRepository;
import com.nguyenhongdang.repository.UserRepository;
import com.nguyenhongdang.service.web.ISanPhamService;

@SuppressWarnings("deprecation")
@Service("sanPhamServiceOfWeb")
public class SanPhamService implements ISanPhamService {
	@Autowired
	EntityManager entityManager;
	@Autowired
	private ProductEntityTransferDTO productEntityTransferDTO;

	@Autowired
	private IKhoangGiaRepository khoangGiaRepo;
	
	@Autowired
	private ISanPhamRepository repository;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public SanPhamEntity getOne(long id) {
		return repository.getOne(id);
	}
	
	@Override
	public List<ProductTransferDTO> findAll(int offset, int limit) {
		String hql = "FROM SanPhamEntity E WHERE E.status = 1 ORDER BY E.showDetail DESC";
		Session session = entityManager.unwrap(Session.class);
		TypedQuery<SanPhamEntity> query = session.createQuery(hql, SanPhamEntity.class);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return productEntityTransferDTO.transferDTO(query.getResultList());
	}

	@Override
	public Integer getTotalItem() {
		String hql = "FROM SanPhamEntity E WHERE E.status = 1";
		Session session = entityManager.unwrap(Session.class);
		TypedQuery<SanPhamEntity> query = session.createQuery(hql, SanPhamEntity.class);
		return query.getResultList().size();
	}

	@Override
	public Integer getTotalItemByGender(String gender, String brandCode, String loaiDayCode, String khoangGiaCode,
			String loaiDHCode) {
		StringBuilder hql = new StringBuilder("from SanPhamEntity E where E.gender = :gender and E.status= 1");
		if (brandCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.brand.code = :brandCode");
		}
		if (loaiDayCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.loaiDay.code = :loaiDayCode");
		}
		if (khoangGiaCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.price >= :mocDau and E.price <= :mocCuoi");
		}
		if (loaiDHCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.productType.code = :loaiDHCode");
		}
		Session session = entityManager.unwrap(Session.class);
		Query<SanPhamEntity> query = session.createQuery(hql.toString(), SanPhamEntity.class);
		query.setParameter("gender", gender);
		if (brandCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("brandCode", brandCode);
		}
		if (loaiDayCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("loaiDayCode", loaiDayCode);
		}
		if (khoangGiaCode.equals(SystemConstant.ALL) == false) {
			KhoangGiaEntity khoangGia = khoangGiaRepo.findByCode(khoangGiaCode);
			query.setParameter("mocDau", khoangGia.getMocDau());
			query.setParameter("mocCuoi", khoangGia.getMocCuoi());
		}
		if (loaiDHCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("loaiDHCode", loaiDHCode);
		}
		List<SanPhamEntity> index = query.getResultList();
		return index.size();
	}

	@Override
	public List<ProductTransferDTO> findAllByGender(String gender, int offset, int limit, String brandCode,
			String loaiDayCode, String khoangGiaCode, String loaiDHCode) {
		StringBuilder hql = new StringBuilder("from SanPhamEntity E where E.gender = :gender and E.status= 1");
		if (brandCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.brand.code = :brandCode");
		}
		if (loaiDayCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.loaiDay.code = :loaiDayCode");
		}
		if (khoangGiaCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.price >= :mocDau and E.price <= :mocCuoi");
		}
		if (loaiDHCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.productType.code = :loaiDHCode");
		}
		hql.append(" ORDER BY E.id DESC");
		Session session = entityManager.unwrap(Session.class);
		Query<SanPhamEntity> query = session.createQuery(hql.toString(), SanPhamEntity.class);
		query.setParameter("gender", gender);
		if (brandCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("brandCode", brandCode);
		}
		if (loaiDayCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("loaiDayCode", loaiDayCode);
		}
		if (khoangGiaCode.equals(SystemConstant.ALL) == false) {
			KhoangGiaEntity khoangGia = khoangGiaRepo.findByCode(khoangGiaCode);
			query.setParameter("mocDau", khoangGia.getMocDau());
			query.setParameter("mocCuoi", khoangGia.getMocCuoi());
		}
		if (loaiDHCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("loaiDHCode", loaiDHCode);
		}
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		List<SanPhamEntity> products = query.getResultList();
		return productEntityTransferDTO.transferDTO(products);
	}

	@Override
	public Integer getTotalItemSearch(String search) {
		return repository.getTotalItemBySearch(search);
	}

	@Override
	public List<ProductTransferDTO> findByProductSearch(String search, int offset, int limit) {
		List<SanPhamEntity> entities = repository.findBySearch(search, offset, limit);
		return productEntityTransferDTO.transferDTO(entities);
	}

	@Override
	public List<ProductTransferDTO> findByBrand(String code, String gender, int offset, int limit, String loaiDayCode,
			String khoangGiaCode, String loaiDHCode) {
		StringBuilder hql = new StringBuilder(
				"from SanPhamEntity E where E.gender = :gender and E.brand.code = :code and E.status= 1");
		if (loaiDayCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.loaiDay.code = :loaiDayCode");
		}
		if (khoangGiaCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.price >= :mocDau and E.price <= :mocCuoi");
		}
		if (loaiDHCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.productType.code = :loaiDHCode");
		}
		hql.append(" ORDER BY E.id DESC");
		Session session = entityManager.unwrap(Session.class);
		Query<SanPhamEntity> query = session.createQuery(hql.toString(), SanPhamEntity.class);
		query.setParameter("code", code);
		query.setParameter("gender", gender);
		if (loaiDayCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("loaiDayCode", loaiDayCode);
		}
		if (khoangGiaCode.equals(SystemConstant.ALL) == false) {
			KhoangGiaEntity khoangGia = khoangGiaRepo.findByCode(khoangGiaCode);
			query.setParameter("mocDau", khoangGia.getMocDau());
			query.setParameter("mocCuoi", khoangGia.getMocCuoi());
		}
		if (loaiDHCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("loaiDHCode", loaiDHCode);
		}
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		List<SanPhamEntity> products = query.getResultList();
		return productEntityTransferDTO.transferDTO(products);
	}

	@Override
	public Integer getTotalItemByBrand(String code, String gender, String loaiDayCode, String khoangGiaCode,
			String loaiDHCode) {
		StringBuilder hql = new StringBuilder(
				"from SanPhamEntity E where E.gender = :gender and E.brand.code = :code and E.status= 1");
		if (loaiDayCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.loaiDay.code = :loaiDayCode");
		}
		if (khoangGiaCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.price >= :mocDau and E.price <= :mocCuoi");
		}
		if (loaiDHCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.productType.code = :loaiDHCode");
		}
		Session session = entityManager.unwrap(Session.class);
		Query<SanPhamEntity> query = session.createQuery(hql.toString(), SanPhamEntity.class);
		query.setParameter("gender", gender);
		query.setParameter("code", code);
		if (loaiDayCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("loaiDayCode", loaiDayCode);
		}
		if (khoangGiaCode.equals(SystemConstant.ALL) == false) {
			KhoangGiaEntity khoangGia = khoangGiaRepo.findByCode(khoangGiaCode);
			query.setParameter("mocDau", khoangGia.getMocDau());
			query.setParameter("mocCuoi", khoangGia.getMocCuoi());
		}
		if (loaiDHCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("loaiDHCode", loaiDHCode);
		}
		List<SanPhamEntity> index = query.getResultList();
		return index.size();
	}

	@Override
	public Integer getTotalItemByPrice(String code, String gender, String loaiDayCode, String brandCode,
			String loaiDHCode) {
		KhoangGiaEntity khoangGia = khoangGiaRepo.findByCode(code);
		StringBuilder hql = new StringBuilder(
				"from SanPhamEntity E where E.price <= :mocCuoi and E.price >= :mocDau and E.gender= :gender and E.status= 1");
		if (loaiDayCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.loaiDay.code = :loaiDayCode");
		}
		if (brandCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.brand.code= :brandCode");
		}
		if (loaiDHCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.productType.code = :loaiDHCode");
		}
		Session session = entityManager.unwrap(Session.class);
		Query<SanPhamEntity> query = session.createQuery(hql.toString(), SanPhamEntity.class);
		if (loaiDayCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("loaiDayCode", loaiDayCode);
		}
		if (brandCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("brandCode", brandCode);
		}
		if (loaiDHCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("loaiDHCode", loaiDHCode);
		}
		query.setParameter("mocCuoi", khoangGia.getMocCuoi());
		query.setParameter("mocDau", khoangGia.getMocDau());
		query.setParameter("gender", gender);
		List<SanPhamEntity> index = query.getResultList();
		return index.size();
	}

	@Override
	public List<ProductTransferDTO> findAllByPrice(String code, String gender, int offset, int limit,
			String loaiDayCode, String brandCode, String loaiDHCode) {
		KhoangGiaEntity khoangGia = khoangGiaRepo.findByCode(code);
		StringBuilder hql = new StringBuilder(
				"from SanPhamEntity E where E.price <= :mocCuoi and E.price >= :mocDau and E.gender= :gender and E.status= 1");
		if (loaiDayCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.loaiDay.code = :loaiDayCode");
		}
		if (brandCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.brand.code= :brandCode");
		}
		if (loaiDHCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.productType.code = :loaiDHCode");
		}
		Session session = entityManager.unwrap(Session.class);
		Query<SanPhamEntity> query = session.createQuery(hql.toString(), SanPhamEntity.class);
		if (loaiDayCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("loaiDayCode", loaiDayCode);
		}
		if (brandCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("brandCode", brandCode);
		}
		if (loaiDHCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("loaiDHCode", loaiDHCode);
		}
		query.setParameter("mocCuoi", khoangGia.getMocCuoi());
		query.setParameter("mocDau", khoangGia.getMocDau());
		query.setParameter("gender", gender);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		List<SanPhamEntity> products = query.getResultList();
		return productEntityTransferDTO.transferDTO(products);
	}

	@Override
	public Integer getTotalItemByProductType(String code, String gender, String loaiDayCode, String khoangGiaCode,
			String brandCode) {
		StringBuilder hql = new StringBuilder(
				"from SanPhamEntity E where E.gender = :gender and E.productType.code = :code and E.status= 1");
		if (loaiDayCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.loaiDay.code = :loaiDayCode");
		}
		if (khoangGiaCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.price >= :mocDau and E.price <= :mocCuoi");
		}
		if (brandCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.brand.code = :brandCode");
		}
		Session session = entityManager.unwrap(Session.class);
		Query<SanPhamEntity> query = session.createQuery(hql.toString(), SanPhamEntity.class);
		query.setParameter("gender", gender);
		query.setParameter("code", code);
		if (loaiDayCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("loaiDayCode", loaiDayCode);
		}
		if (khoangGiaCode.equals(SystemConstant.ALL) == false) {
			KhoangGiaEntity khoangGia = khoangGiaRepo.findByCode(khoangGiaCode);
			query.setParameter("mocDau", khoangGia.getMocDau());
			query.setParameter("mocCuoi", khoangGia.getMocCuoi());
		}
		if (brandCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("brandCode", brandCode);
		}
		List<SanPhamEntity> index = query.getResultList();
		return index.size();
	}

	@Override
	public List<ProductTransferDTO> findByProductType(String code, String gender, int offset, int limit,
			String loaiDayCode, String khoangGiaCode, String brandCode) {
		StringBuilder hql = new StringBuilder(
				"from SanPhamEntity E where E.gender = :gender and E.productType.code = :code and E.status= 1");
		if (loaiDayCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.loaiDay.code = :loaiDayCode");
		}
		if (khoangGiaCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.price >= :mocDau and E.price <= :mocCuoi");
		}
		if (brandCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.brand.code = :brandCode");
		}
		hql.append(" ORDER BY E.id DESC");
		Session session = entityManager.unwrap(Session.class);
		Query<SanPhamEntity> query = session.createQuery(hql.toString(), SanPhamEntity.class);
		query.setParameter("gender", gender);
		query.setParameter("code", code);
		if (loaiDayCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("loaiDayCode", loaiDayCode);
		}
		if (khoangGiaCode.equals(SystemConstant.ALL) == false) {
			KhoangGiaEntity khoangGia = khoangGiaRepo.findByCode(khoangGiaCode);
			query.setParameter("mocDau", khoangGia.getMocDau());
			query.setParameter("mocCuoi", khoangGia.getMocCuoi());
		}
		if (brandCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("brandCode", brandCode);
		}
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		List<SanPhamEntity> products = query.getResultList();
		return productEntityTransferDTO.transferDTO(products);
	}

	@Override
	public Integer getTotalItemByLoaiDay(String code, String gender, String loaiDHCode, String khoangGiaCode,
			String brandCode) {
		StringBuilder hql = new StringBuilder(
				"from SanPhamEntity E where E.gender = :gender and E.loaiDay.code = :code and E.status= 1");
		if (loaiDHCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.productType.code = :loaiDHCode");
		}
		if (khoangGiaCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.price >= :mocDau and E.price <= :mocCuoi");
		}
		if (brandCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.brand.code = :brandCode");
		}
		Session session = entityManager.unwrap(Session.class);
		Query<SanPhamEntity> query = session.createQuery(hql.toString(), SanPhamEntity.class);
		query.setParameter("gender", gender);
		query.setParameter("code", code);
		if (loaiDHCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("loaiDHCode", loaiDHCode);
		}
		if (khoangGiaCode.equals(SystemConstant.ALL) == false) {
			KhoangGiaEntity khoangGia = khoangGiaRepo.findByCode(khoangGiaCode);
			query.setParameter("mocDau", khoangGia.getMocDau());
			query.setParameter("mocCuoi", khoangGia.getMocCuoi());
		}
		if (brandCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("brandCode", brandCode);
		}
		List<SanPhamEntity> index = query.getResultList();
		return index.size();
	}

	@Override
	public List<ProductTransferDTO> findByLoaiDay(String code, String gender, int offset, int limit, String loaiDHCode,
			String khoangGiaCode, String brandCode) {
		StringBuilder hql = new StringBuilder(
				"from SanPhamEntity E where E.gender = :gender and E.loaiDay.code = :code and E.status= 1");
		if (loaiDHCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.productType.code = :loaiDHCode");
		}
		if (khoangGiaCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.price >= :mocDau and E.price <= :mocCuoi");
		}
		if (brandCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.brand.code = :brandCode");
		}
		hql.append(" ORDER BY E.id DESC");
		Session session = entityManager.unwrap(Session.class);
		Query<SanPhamEntity> query = session.createQuery(hql.toString(), SanPhamEntity.class);
		query.setParameter("gender", gender);
		query.setParameter("code", code);
		if (loaiDHCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("loaiDHCode", loaiDHCode);
		}
		if (khoangGiaCode.equals(SystemConstant.ALL) == false) {
			KhoangGiaEntity khoangGia = khoangGiaRepo.findByCode(khoangGiaCode);
			query.setParameter("mocDau", khoangGia.getMocDau());
			query.setParameter("mocCuoi", khoangGia.getMocCuoi());
		}
		if (brandCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("brandCode", brandCode);
		}
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		List<SanPhamEntity> products = query.getResultList();
		return productEntityTransferDTO.transferDTO(products);
	}

	@Override
	public ProductTransferDTO getProductDetail(String code) {
		SanPhamEntity entity = repository.findByCode(code);
		entity.setShowDetail(entity.getShowDetail() + 1);
		entity = repository.save(entity);
		return productEntityTransferDTO.transferDTO(entity);
	}

	@Override
	public List<ProductTransferDTO> getSanPhamLienQuan(String code) {
		SanPhamEntity entity = repository.findByCode(code);
		String hql = "from SanPhamEntity E where E.gender = :gender and E.brand = :brand and E.loaiDay =:loaiDay and E.productType =:productType "
				+ "and E.kieuDongHo =:kieuDongHo and E.id <> :id and E.status= 1";
		Session session = entityManager.unwrap(Session.class);
		TypedQuery<SanPhamEntity> query = session.createQuery(hql, SanPhamEntity.class);
		query.setParameter("gender", entity.getGender());
		query.setParameter("brand", entity.getBrand());
		query.setParameter("loaiDay", entity.getLoaiDay());
		query.setParameter("productType", entity.getProductType());
		query.setParameter("kieuDongHo", entity.getKieuDongHo());
		query.setParameter("id", entity.getId());
		query.setMaxResults(4);
		List<SanPhamEntity> products = query.getResultList();
		return productEntityTransferDTO.transferDTO(products);
	}

	@Override
	public Integer getTotalItemByProductSpecial(String loaiDayCode, String khoangGiaCode, String brandCode,
			String gender) {
		StringBuilder hql = new StringBuilder(
				"from SanPhamEntity E where E.productType.typeName =:typeName and E.status= 1");
		if (gender.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.gender = :gender");
		}
		if (loaiDayCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.loaiDay.code = :loaiDayCode");
		}
		if (khoangGiaCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.price >= :mocDau and E.price <= :mocCuoi");
		}
		if (brandCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.brand.code = :brandCode");
		}
		Session session = entityManager.unwrap(Session.class);
		Query<SanPhamEntity> query = session.createQuery(hql.toString(), SanPhamEntity.class);
		query.setParameter("typeName", "Phiên bản đặc biệt");
		if (loaiDayCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("loaiDayCode", loaiDayCode);
		}
		if (khoangGiaCode.equals(SystemConstant.ALL) == false) {
			KhoangGiaEntity khoangGia = khoangGiaRepo.findByCode(khoangGiaCode);
			query.setParameter("mocDau", khoangGia.getMocDau());
			query.setParameter("mocCuoi", khoangGia.getMocCuoi());
		}
		if (brandCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("brandCode", brandCode);
		}
		if (gender.equals(SystemConstant.ALL) == false) {
			query.setParameter("gender", gender);
		}
		List<SanPhamEntity> index = query.getResultList();
		return index.size();
	}

	@Override
	public List<ProductTransferDTO> findByProductSpecial(int offset, int limit, String loaiDayCode,
			String khoangGiaCode, String brandCode, String gender) {
		StringBuilder hql = new StringBuilder(
				"from SanPhamEntity E where E.productType.typeName =:typeName and E.status= 1");
		if (gender.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.gender = :gender");
		}
		if (loaiDayCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.loaiDay.code = :loaiDayCode");
		}
		if (khoangGiaCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.price >= :mocDau and E.price <= :mocCuoi");
		}
		if (brandCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.brand.code = :brandCode");
		}
		Session session = entityManager.unwrap(Session.class);
		Query<SanPhamEntity> query = session.createQuery(hql.toString(), SanPhamEntity.class);
		query.setParameter("typeName", "Phiên bản đặc biệt");
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		if (loaiDayCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("loaiDayCode", loaiDayCode);
		}
		if (khoangGiaCode.equals(SystemConstant.ALL) == false) {
			KhoangGiaEntity khoangGia = khoangGiaRepo.findByCode(khoangGiaCode);
			query.setParameter("mocDau", khoangGia.getMocDau());
			query.setParameter("mocCuoi", khoangGia.getMocCuoi());
		}
		if (brandCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("brandCode", brandCode);
		}
		if (gender.equals(SystemConstant.ALL) == false) {
			query.setParameter("gender", gender);
		}
		return productEntityTransferDTO.transferDTO(query.getResultList());
	}

	@Override
	public List<ProductTransferDTO> getProductLatest(int offset, int limit, String loaiDayCode, String khoangGiaCode,
			String brandCode, String gender, String loaiDHCode) {
		StringBuilder hql = new StringBuilder("from SanPhamEntity E where E.status= 1 ");
		if (gender.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.gender = :gender");
		}
		if (loaiDayCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.loaiDay.code = :loaiDayCode");
		}
		if (khoangGiaCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.price >= :mocDau and E.price <= :mocCuoi");
		}
		if (brandCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.brand.code = :brandCode");
		}
		if (loaiDHCode.equals(SystemConstant.ALL) == false) {
			hql.append(" and E.productType.code = :loaiDHCode");
		}
		hql.append(" ORDER BY E.id DESC");
		Session session = entityManager.unwrap(Session.class);
		Query<SanPhamEntity> query = session.createQuery(hql.toString(), SanPhamEntity.class);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		if (loaiDayCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("loaiDayCode", loaiDayCode);
		}
		if (khoangGiaCode.equals(SystemConstant.ALL) == false) {
			KhoangGiaEntity khoangGia = khoangGiaRepo.findByCode(khoangGiaCode);
			query.setParameter("mocDau", khoangGia.getMocDau());
			query.setParameter("mocCuoi", khoangGia.getMocCuoi());
		}
		if (brandCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("brandCode", brandCode);
		}
		if (gender.equals(SystemConstant.ALL) == false) {
			query.setParameter("gender", gender);
		}
		if (loaiDHCode.equals(SystemConstant.ALL) == false) {
			query.setParameter("loaiDHCode", loaiDHCode);
		}
		return productEntityTransferDTO.transferDTO(query.getResultList());
	}

	@Override
	public List<ProductTransferDTO> getTopSellers(int offset, int limit) {
		String hql = "FROM  OrderDetailEntity E GROUP BY E.productId ORDER BY SUM(E.soLuong) DESC";
		Session session = entityManager.unwrap(Session.class);
		TypedQuery<OrderDetailEntity> query = session.createQuery(hql, OrderDetailEntity.class);
		List<OrderDetailEntity> orderDetails = query.getResultList();
		List<Long> productIds = new ArrayList<>();
		for (OrderDetailEntity orderDetail : orderDetails) {
			productIds.add(orderDetail.getProductId());
		}
		List<SanPhamEntity> sanPhams = new ArrayList<>();
		for (long productId : productIds) {
			sanPhams.add(repository.getOne(productId));
		}
		List<SanPhamEntity> results = new ArrayList<>();
		int maxItem = offset + limit;
		if (maxItem >= sanPhams.size()) {
			for (int i = offset; i < sanPhams.size(); i++) {
				results.add(sanPhams.get(i));
			}
		} else {
			for (int i = offset; i < maxItem; i++) {
				results.add(sanPhams.get(i));
			}
		}
		return productEntityTransferDTO.transferDTO(results);
	}

	@Override
	public Integer getTotalItemTopSellers() {
		String hql = "FROM  OrderDetailEntity E GROUP BY E.productId ORDER BY SUM(E.soLuong) DESC";
		Session session = entityManager.unwrap(Session.class);
		TypedQuery<OrderDetailEntity> query = session.createQuery(hql, OrderDetailEntity.class);
		List<OrderDetailEntity> orderDetails = query.getResultList();
		List<Long> productIds = new ArrayList<>();
		for (OrderDetailEntity orderDetail : orderDetails) {
			productIds.add(orderDetail.getProductId());
		}
		List<SanPhamEntity> sanPhams = new ArrayList<>();
		for (long productId : productIds) {
			SanPhamEntity sanPham = repository.getOne(productId);
			sanPhams.add(sanPham);
		}
		return sanPhams.size();
	}

	@Override
	public List<ProductTransferDTO> getRecommend(String userName,int offset, int limit) {
		UserEntity entity = userRepo.findByUsername(userName);
		String sql = "SELECT p.* FROM shopdongho.order_info as info join shopdongho.order_detail as de on info.id = de.order_info_id"
				+" join shopdongho.users as u on info.username = u.username "
				+" join shopdongho.product as p on p.id= de.product_id"
				+" where u.year_of_birth =:namSinh and u.gender=:gender and p.status=1 group by de.product_id order by sum(de.so_luong) desc";
		Session session = entityManager.unwrap(Session.class);
		javax.persistence.Query query = session.createNativeQuery(sql,SanPhamEntity.class);
		query.setParameter("namSinh", entity.getYearOfBirth());
		query.setParameter("gender", entity.getGender());
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		@SuppressWarnings("unchecked")
		List<SanPhamEntity> sanPhams = query.getResultList();
		return productEntityTransferDTO.transferDTO(sanPhams);
	}

	@Override
	public Integer getTotalItemRecommend(String userName) {
		UserEntity entity = userRepo.findByUsername(userName);
		String sql = "SELECT p.* FROM shopdongho.order_info as info join shopdongho.order_detail as de on info.id = de.order_info_id"
				+" join shopdongho.users as u on info.username = u.username "
				+" join shopdongho.product as p on p.id= de.product_id"
				+" where u.year_of_birth =:namSinh and u.gender=:gender and p.status=1 group by de.product_id order by sum(de.so_luong) desc";
		Session session = entityManager.unwrap(Session.class);
		javax.persistence.Query query = session.createNativeQuery(sql,SanPhamEntity.class);
		query.setParameter("namSinh", entity.getYearOfBirth());
		query.setParameter("gender", entity.getGender());
		@SuppressWarnings("unchecked")
		List<SanPhamEntity> sanPhams = query.getResultList();
		return sanPhams.size();
	}

}
