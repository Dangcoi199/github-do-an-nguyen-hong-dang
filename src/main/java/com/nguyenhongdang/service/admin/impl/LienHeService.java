package com.nguyenhongdang.service.admin.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenhongdang.constant.TrangThaiLienHeConstant;
import com.nguyenhongdang.dto.SendMailDTO;
import com.nguyenhongdang.entity.LienHeEntity;
import com.nguyenhongdang.repository.ILienHeRepository;
import com.nguyenhongdang.service.admin.ILienHeService;
import com.nguyenhongdang.utils.SendMailUtil;

@Service("serviceOfAdmin")
public class LienHeService implements ILienHeService {
	@Autowired
	private ILienHeRepository repository;
	@Autowired
	private SendMailUtil sendMail;
	@Autowired
	EntityManager entityManager;

	@Override
	public List<LienHeEntity> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for (long id : ids) {
			repository.deleteById(id);
		}
	}

	@Override
	public LienHeEntity mailing(SendMailDTO dto) {
		LienHeEntity entity = repository.getOne(dto.getId());
		try {
			sendMail.SendMail(dto.getToEmail(), dto.getContent(), dto.getTitle());
			entity.setTrangThai(TrangThaiLienHeConstant.ThanhCong);
		} catch (Exception e) {
			entity.setTrangThai(TrangThaiLienHeConstant.Huy);
		}
		return repository.save(entity);

	}

	@Override
	public LienHeEntity getOne(long id) {
		// TODO Auto-generated method stub
		return repository.getOne(id);
	}

	@Override
	public List<LienHeEntity> findAllByStatus() {
		Session session = entityManager.unwrap(Session.class);
		String hql="FROM LienHeEntity E WHERE E.trangThai =:trangThai";
		TypedQuery<LienHeEntity> query = session.createQuery(hql,LienHeEntity.class);
		query.setParameter("trangThai", TrangThaiLienHeConstant.ChuaPhanHoi);
		return query.getResultList();
	}
}
