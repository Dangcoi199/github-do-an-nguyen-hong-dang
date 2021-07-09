package com.nguyenhongdang.api.web;

import javax.persistence.EntityManager;

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
	public TokenEntity insertToken(@RequestBody TokenEntity entity) {
		return service.save(entity);
	}

}
