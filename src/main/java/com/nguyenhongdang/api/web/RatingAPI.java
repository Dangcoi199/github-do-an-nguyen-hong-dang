package com.nguyenhongdang.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenhongdang.service.web.IDanhGiaService;

@RestController
public class RatingAPI {
	@Autowired
	private IDanhGiaService service;
	@PostMapping("/save_star")
	public void addRating(@RequestParam(name = "star") int star,@RequestParam(name = "product_id") long product_id) {
		service.save(star, product_id);
	}
}
