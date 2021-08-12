package com.nguyenhongdang.service.admin.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.nguyenhongdang.constant.StatusConstant;
import com.nguyenhongdang.dto.ThongKeHoaDonBanDTO;
import com.nguyenhongdang.entity.HoaDonBanEntity;
import com.nguyenhongdang.entity.OrderDetailEntity;
import com.nguyenhongdang.entity.OrderInfoEntity;
import com.nguyenhongdang.entity.SanPhamEntity;
import com.nguyenhongdang.repository.IHoaDonBanRepository;
import com.nguyenhongdang.repository.ISanPhamRepository;
import com.nguyenhongdang.repository.OrderRepository;
import com.nguyenhongdang.service.admin.IHoaDonBanService;
import com.nguyenhongdang.utils.SendMailUtil;

@Service
public class HoaDonBanService implements IHoaDonBanService {
	@Autowired
	private Environment env;
	@Autowired
	EntityManager entityManager;
	@Autowired
	private OrderRepository repository;
	@Autowired
	private ISanPhamRepository spRepo;
	@Autowired
	private SendMailUtil sendMail;
	@Autowired
	private IHoaDonBanRepository hdbRepo;

	@Override
	@Transactional
	public HoaDonBanEntity add(long id) {
		OrderInfoEntity orderInfo = entityManager.find(OrderInfoEntity.class, id);
		orderInfo.setTrangThai(StatusConstant.ThanhCong);
		entityManager.merge(orderInfo);
		HoaDonBanEntity entity = new HoaDonBanEntity();
		entity.setOrderInfo(entityManager.find(OrderInfoEntity.class, id));
		entity = hdbRepo.save(entity);
		// Send mail
		String content = "<a href='"+env.getProperty("app.mailsender.redirect.uri.success") + entity.getId()
				+ "' style='color:red;' >Chi tiết đơn hàng</a>"
				+ "<img src='https://www.english-learning.net/wp-content/uploads/2020/04/thank_you_2.jpg' style='width: 20%; height: auto;'>";
		sendMail.SendMail(orderInfo.getEmail(), content, "XÁC NHẬN ĐƠN HÀNG THÀNH CÔNG");
		return entity;
	}

	@Override
	@Transactional
	public void cancel(long id) {
		OrderInfoEntity entity = repository.getOne(id);
		entity.setTrangThai(StatusConstant.Huy);
		entity = repository.save(entity);
		for (OrderDetailEntity orderDetail : entity.getOrderDetails()) {
			SanPhamEntity product = spRepo.getOne(orderDetail.getProductId());
			product.setQuantity(product.getQuantity() + orderDetail.getSoLuong());
			spRepo.save(product);
		}
		// Xóa order detail
		String hql = "DELETE FROM OrderDetailEntity E WHERE E.orderInfo =:orderInfo";
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery(hql);
		query.setParameter("orderInfo", entity);
		query.executeUpdate();
		// Send mail
		String content = "<a href='"+env.getProperty("app.mailsender.redirect.uri.cancel")+ "' style='color:red;'>LUXURY WATCHES</a> "
				+ "<img src='https://www.english-learning.net/wp-content/uploads/2020/04/thank_you_2.jpg' style='width: 20%; height: auto;'>";
		sendMail.SendMail(entity.getEmail(), content, "ĐƠN HÀNG ĐÃ BỊ HỦY");

	}

	@Override
	public List<HoaDonBanEntity> getAll() {
		Session session = entityManager.unwrap(Session.class);
		String hql = "FROM HoaDonBanEntity E ORDER BY E.id DESC";
		TypedQuery<HoaDonBanEntity> query = session.createQuery(hql, HoaDonBanEntity.class);
		List<HoaDonBanEntity> hoaDonBans = query.getResultList();
		return hoaDonBans;
	}

	@Override
	public HoaDonBanEntity getOne(long id) {
		HoaDonBanEntity entity = entityManager.find(HoaDonBanEntity.class, id);
		return entity;
	}

	@Override
	public List<HoaDonBanEntity> getByUserName(String username) {
		String hql ="FROM HoaDonBanEntity E WHERE E.orderInfo.username =:username";
		Session session = entityManager.unwrap(Session.class);
		TypedQuery<HoaDonBanEntity> query = session.createQuery(hql,HoaDonBanEntity.class);
		query.setParameter("username", username);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ThongKeHoaDonBanDTO findByCreateDate(String date) {
		String sql= "SELECT * FROM shopdongho.hoadonban as dg where dg.created_date like :date";
		Session session =  entityManager.unwrap(Session.class);
		Query query = session.createNativeQuery(sql, HoaDonBanEntity.class);
		if(date.equals("all")) {
			query.setParameter("date","%");
		}else {
			query.setParameter("date", date+"%");
		}
		long tongTien=0;
		ThongKeHoaDonBanDTO dto = new ThongKeHoaDonBanDTO();		
		List<HoaDonBanEntity> hoaDonBansBanEntities = query.getResultList();
		dto.setHoaDonBans(hoaDonBansBanEntities);
		for(HoaDonBanEntity entity : hoaDonBansBanEntities) {
			tongTien = (long) (tongTien + entity.getOrderInfo().getTongTien());
		}
		dto.setTongTien(tongTien);
		return dto;
	}

}
