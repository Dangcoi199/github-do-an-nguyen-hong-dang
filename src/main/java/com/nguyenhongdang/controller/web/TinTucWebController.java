package com.nguyenhongdang.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenhongdang.entity.TinTucEntity;
import com.nguyenhongdang.service.web.ITinTucService;

@Controller
public class TinTucWebController {
	@Autowired
	private ITinTucService tinTucService;
	
	/* Chi tiết bài viết */
	@GetMapping("/tin_tuc")
	public String tin_tuc(Model model,@RequestParam(name = "code") String code) {
		TinTucEntity tinTuc = tinTucService.getByCode(code);
		model.addAttribute("tinTuc", tinTuc);
		model.addAttribute("lienQuans", tinTucService.getLienQuan(code));
		model.addAttribute("moiNhats", tinTucService.getMoiNhat(code));
		model.addAttribute("docNhieus", tinTucService.getDocNhieu(code));
		return "tin_tuc";
	}
	/* Chi tiết bài viết */
	@GetMapping("/danh-muc-tin")
	public String danh_muc_tin(Model model,@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "6") int limit) {
		model.addAttribute("page", page);
		model.addAttribute("totalPage", (int) Math.ceil((double) tinTucService.findAll().size()/ limit));
		int offset = page * limit - limit;
		model.addAttribute("tinTucs", tinTucService.findAll(offset,limit));
		model.addAttribute("moiNhats", tinTucService.getMoiNhat());
		model.addAttribute("docNhieus", tinTucService.getDocNhieu());
		return "danh_muc_tin";
	}
	@GetMapping("/tin-tuc-by-danh_muc")
	public String review_sanPham(Model model,@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "6") int limit,@RequestParam String danhMuc) {
		model.addAttribute("page", page);
		model.addAttribute("totalPage", (int) Math.ceil((double) tinTucService.getItemByDanhMuc(danhMuc)/ limit));
		int offset = page * limit - limit;
		model.addAttribute("tinTucs", tinTucService.findAllByDanhMuc(offset, limit, danhMuc));
		model.addAttribute("moiNhats", tinTucService.getMoiNhat());
		model.addAttribute("docNhieus", tinTucService.getDocNhieu());
		model.addAttribute("danhMuc", danhMuc);
		return "tin_by_danh_muc";
	}
}
