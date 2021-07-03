package com.nguyenhongdang.service.web.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenhongdang.entity.TokenEntity;
import com.nguyenhongdang.repository.ITokenRepository;
import com.nguyenhongdang.service.web.ITokenService;

@Service
public class TokenService implements ITokenService {
	@Autowired
	private ITokenRepository repository;
	
	@Autowired
     EntityManager entityManager;
	
	@Override
	@Transactional
	public TokenEntity save(TokenEntity entity) {
		String hql= "from TokenEntity E where E.token = :token and E.productId =:product_id";
		Session session = entityManager.unwrap(Session.class);
		TypedQuery<TokenEntity> query = session.createQuery(hql, TokenEntity.class);
		query.setParameter("token", entity.getToken());
		query.setParameter("product_id", entity.getProductId());
		if(query.getResultList().size() == 0) {
			TokenEntity newToken = new TokenEntity();
			newToken.setToken(entity.getToken());
			newToken.setProductId(entity.getProductId());
			newToken = repository.save(newToken);
			return newToken;
		}
		return null;
	}

}
