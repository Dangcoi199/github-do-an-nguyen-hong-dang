package com.nguyenhongdang.api.web;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenhongdang.entity.TokenEntity;
import com.nguyenhongdang.service.web.ITokenService;

@RestController
public class TokenAPI {
	@Autowired
	private ITokenService service;
	@Autowired
	EntityManager entityManager;
	
	@PostMapping("/save-token")
	@Transactional
	public TokenEntity insertToken(@RequestBody TokenEntity entity) {
		return service.save(entity);
	}
	
	/*
	 * @PostMapping("/save-token")
	 * 
	 * @Transactional public void insertToken(@RequestParam(name = "token") String
	 * token , @RequestParam(name = "product_id") long product_id) { String hql=
	 * "from TokenEntity E where E.token = :token and E.productId =:product_id";
	 * Session session = entityManager.unwrap(Session.class);
	 * TypedQuery<TokenEntity> query = session.createQuery(hql, TokenEntity.class);
	 * query.setParameter("token", token);
	 * query.setParameter("product_id",product_id); if(query.getResultList().size()
	 * == 0) { TokenEntity newToken = new TokenEntity(); newToken.setToken(token);
	 * newToken.setProductId(product_id); entityManager.persist(newToken); } }
	 */
}
