package com.nguyenhongdang.service.web.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nguyenhongdang.entity.DanhGiaEntity;
import com.nguyenhongdang.entity.SanPhamEntity;
import com.nguyenhongdang.repository.IDanhGiaRepository;
import com.nguyenhongdang.repository.ISanPhamRepository;
import com.nguyenhongdang.service.web.IDanhGiaService;

@Service
public class DanhGiaService implements IDanhGiaService {
	@Autowired
	private IDanhGiaRepository danhGiaRepo;
	@Autowired
	EntityManager entityManager;
	@Autowired
	private ISanPhamRepository sanPhamRepo;

	@Override
	@Transactional
	public void save(int soSao, long product_id) {
		SanPhamEntity product = sanPhamRepo.getOne(product_id);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		String hql="FROM DanhGiaEntity E WHERE E.product=:product AND E.createdBy=:username";
		Session session = entityManager.unwrap(Session.class);
		TypedQuery<DanhGiaEntity> query = session.createQuery(hql, DanhGiaEntity.class);
		query.setParameter("product", product);
		query.setParameter("username", username);
		List<DanhGiaEntity> danhGias  = query.getResultList();
		if(danhGias.size() != 0) {
			danhGiaRepo.deleteAll(danhGias);
		}
		DanhGiaEntity newEntity = new DanhGiaEntity();
		newEntity.setProduct(product);
		newEntity.setSoSao(soSao);
		entityManager.persist(newEntity);		
	}

}
