package com.nguyenhongdang.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenhongdang.entity.SliderEntity;
import com.nguyenhongdang.service.admin.ISliderService;

@Controller
public class SliderController {
	@Autowired
	private ISliderService service;

	@GetMapping(value = "/listSlider")
	public String showList(Model model) {
		List<SliderEntity> sliders = service.findAll();
		model.addAttribute("sliders", sliders);
		return "admin/slider/showlist";
	}

	@GetMapping(value = "/addSlider")
	public String add() {
		return "admin/slider/add";
	}

	@GetMapping("/updateSlider")
	public String edit(@RequestParam(value = "id") long id, Model model) {
		SliderEntity slider = service.getOne(id);
		model.addAttribute("slider", slider);
		return "admin/slider/edit";
	}
}
