package com.nguyenhongdang.api.admin;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAPI {
	@GetMapping("/test")
	public List<String> getFile() {
		return Arrays.asList(new File("src\\main\\resources\\static\\uploads\\sanpham").listFiles()).stream()
				.map(f -> f.getName()).collect(Collectors.toList());
	}
}
