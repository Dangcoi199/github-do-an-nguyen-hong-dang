package com.nguyenhongdang.service.web.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenhongdang.constant.TinTucConstant;
import com.nguyenhongdang.entity.TinTucEntity;
import com.nguyenhongdang.repository.ITinTucRepository;
import com.nguyenhongdang.service.web.ITinTucService;

@Service("tinTucServiceOfWeb")
public class TinTucService implements ITinTucService {

	@Autowired
	EntityManager entityManager;
	@Autowired
	private ITinTucRepository repository;


	@Override
	public TinTucEntity getByCode(String code) {
		TinTucEntity entity = repository.findByCode(code);
		entity.setShowDetail(entity.getShowDetail() + 1);
		entity = repository.save(entity);
		return entity;
	}

	@Override
	public List<String> getDanhMuc() {
		List<String> danhMucs = new ArrayList<>();
		danhMucs.add(TinTucConstant.tinTuc);
		danhMucs.add(TinTucConstant.reviewSP);
		danhMucs.add(TinTucConstant.chungNhan_ThuongHieu);
		return danhMucs;
	}

	@Override
	public List<TinTucEntity> getDocNhieu() {
		String hql = "FROM TinTucEntity E ORDER BY E.showDetail DESC";
		Session session = entityManager.unwrap(Session.class);
		TypedQuery<TinTucEntity> query = session.createQuery(hql, TinTucEntity.class);
		query.setMaxResults(5);
		return query.getResultList();
	}

	@Override
	public List<TinTucEntity> getMoiNhat() {
		String hql = "FROM TinTucEntity E ORDER BY E.id DESC";
		Session session = entityManager.unwrap(Session.class);
		TypedQuery<TinTucEntity> query = session.createQuery(hql, TinTucEntity.class);
		query.setMaxResults(5);
		return query.getResultList();
	}

	@Override
	public List<TinTucEntity> findAll(int offset, int limit) {
		String hql = "FROM TinTucEntity E ORDER BY E.id DESC";
		Session session = entityManager.unwrap(Session.class);
		TypedQuery<TinTucEntity> query = session.createQuery(hql, TinTucEntity.class);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.getResultList();
	}

	@Override
	public List<TinTucEntity> getLienQuan(String code) {
		TinTucEntity entity = repository.findByCode(code);
		String hql = "FROM TinTucEntity E WHERE E.code <> :code AND E.danhMuc =:danhMuc ORDER BY E.id DESC";
		Session session = entityManager.unwrap(Session.class);
		TypedQuery<TinTucEntity> query = session.createQuery(hql, TinTucEntity.class);
		query.setParameter("code", code);
		query.setParameter("danhMuc", entity.getDanhMuc());
		query.setMaxResults(3);
		return query.getResultList();
	}

	@Override
	public List<TinTucEntity> getDocNhieu(String code) {
		String hql = "FROM TinTucEntity E WHERE E.code <> :code  ORDER BY E.showDetail DESC";
		Session session = entityManager.unwrap(Session.class);
		TypedQuery<TinTucEntity> query = session.createQuery(hql, TinTucEntity.class);
		query.setParameter("code", code);
		query.setMaxResults(5);
		return query.getResultList();
	}

	@Override
	public List<TinTucEntity> getMoiNhat(String code) {
		String hql = "FROM TinTucEntity E WHERE E.code <> :code  ORDER BY E.id DESC";
		Session session = entityManager.unwrap(Session.class);
		TypedQuery<TinTucEntity> query = session.createQuery(hql, TinTucEntity.class);
		query.setParameter("code", code);
		query.setMaxResults(5);
		return query.getResultList();
	}

	@Override
	public List<TinTucEntity> findAllByDanhMuc(int offset, int limit, String danhMuc) {
		String hql = "FROM TinTucEntity E WHERE E.danhMuc = :danhMuc  ORDER BY E.id DESC";
		Session session = entityManager.unwrap(Session.class);
		TypedQuery<TinTucEntity> query = session.createQuery(hql, TinTucEntity.class);
		query.setParameter("danhMuc", danhMuc);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.getResultList();
	}

	@Override
	public Integer getItemByDanhMuc(String danhMuc) {
		return repository.findByDanhMuc(danhMuc).size();
	}

	@Override
	public List<TinTucEntity> getDocNhieuByDM(String danhMuc) {
		String hql = "FROM TinTucEntity E WHERE E.danhMuc = :danhMuc ORDER BY E.showDetail DESC";
		Session session = entityManager.unwrap(Session.class);
		TypedQuery<TinTucEntity> query = session.createQuery(hql, TinTucEntity.class);
		query.setParameter("danhMuc", danhMuc);
		query.setMaxResults(5);
		return query.getResultList();
	}

	@Override
	public List<TinTucEntity> getMoiNhatByDM(String danhMuc) {
		String hql = "FROM TinTucEntity E WHERE E.danhMuc = :danhMuc ORDER BY E.id DESC";
		Session session = entityManager.unwrap(Session.class);
		TypedQuery<TinTucEntity> query = session.createQuery(hql, TinTucEntity.class);
		query.setParameter("danhMuc", danhMuc);
		query.setMaxResults(5);
		return query.getResultList();
	}

	@Override
	public List<TinTucEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public List<TinTucEntity> getThreeTinGioiThieu() {
		String hql = "FROM TinTucEntity E WHERE E.danhMuc = :danhMuc ORDER BY E.id DESC";
		Session session = entityManager.unwrap(Session.class);
		TypedQuery<TinTucEntity> query = session.createQuery(hql, TinTucEntity.class);
		query.setParameter("danhMuc", TinTucConstant.gioiThieu);
		query.setMaxResults(3);
		return query.getResultList();
	}
	
	@Override
	public List<TinTucEntity> getThreeTinTuc() {
		String hql = "FROM TinTucEntity E WHERE E.danhMuc <> :danhMuc ORDER BY E.showDetail DESC";
		Session session = entityManager.unwrap(Session.class);
		TypedQuery<TinTucEntity> query = session.createQuery(hql, TinTucEntity.class);
		query.setParameter("danhMuc", TinTucConstant.gioiThieu);
		query.setMaxResults(3);
		return query.getResultList();
	}
}
