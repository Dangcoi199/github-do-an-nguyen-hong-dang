package com.nguyenhongdang.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenhongdang.entity.LienHeEntity;
import com.nguyenhongdang.service.web.ILienHeService;

@RestController("lienHeApiOfWeb")
public class LienHeAPI {
	@Autowired 
	private ILienHeService service;
	
	@PostMapping("/add-lien-he")
	public LienHeEntity add(@RequestBody LienHeEntity entity) {
		return service.add(entity);
	}
}
