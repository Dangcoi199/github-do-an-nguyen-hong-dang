package com.nguyenhongdang.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenhongdang.constant.SystemConstant;
import com.nguyenhongdang.constant.TinTucConstant;
import com.nguyenhongdang.dto.OutPutPaginationProduct;
import com.nguyenhongdang.dto.ProductTransferDTO;
import com.nguyenhongdang.entity.SanPhamEntity;
import com.nguyenhongdang.entity.SliderEntity;
import com.nguyenhongdang.service.admin.IBrandService;
import com.nguyenhongdang.service.admin.IKhoangGiaService;
import com.nguyenhongdang.service.admin.ILoaiDayService;
import com.nguyenhongdang.service.admin.IProductTypeService;
import com.nguyenhongdang.service.admin.ISliderService;
import com.nguyenhongdang.service.web.ISanPhamService;
import com.nguyenhongdang.service.web.ITinTucService;

@Controller
public class ProductController {
	@Autowired
	private ISliderService sliderService;
	@Autowired
	private ISanPhamService sanPhamService;
	@Autowired
	private ILoaiDayService loaiDayService;
	@Autowired
	private IProductTypeService productTypeService;
	@Autowired
	private IBrandService brandService;
	@Autowired
	private IKhoangGiaService khoangGiaService;
	@Autowired
	private ITinTucService tinTucService;

	@GetMapping(value = { "/","/trang-chu" })
	public String index(Model model, @RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "8") int limit) {
		List<SliderEntity> sliders = sliderService.getSlider();
		model.addAttribute("sliders", sliders);
		OutPutPaginationProduct result = new OutPutPaginationProduct();
		result.setPage(page);
		int offset = page * limit - limit;
		Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getPrincipal() != "anonymousUser") {
			String userName = authentication.getName();
			result.setTotalPage((int) Math.ceil((double) sanPhamService.getTotalItemRecommend(userName)/ limit));
			List<ProductTransferDTO> sanPhamRecommends = sanPhamService.getRecommend(userName,offset,limit);			
			result.setListResult(sanPhamRecommends);	
		}else {
			result.setTotalPage((int) Math.ceil((double) sanPhamService.getTotalItem()/ limit));			
			List<ProductTransferDTO> products = sanPhamService.findAll(offset, limit);
			result.setListResult(products);		
		}		
		model.addAttribute("listProducts", result);
		model.addAttribute("tinTucs", tinTucService.getThreeTinTuc());
		model.addAttribute("gioiThieus", tinTucService.getThreeTinGioiThieu());
		return "index";
	}

	@GetMapping("/productByGender")
	public String productByGender(Model model, @RequestParam(name = "gender") String gender,
			@RequestParam(name = "loaiDHCode", defaultValue = SystemConstant.ALL) String loaiDHCode,
			@RequestParam(name = "khoangGiaCode", defaultValue = SystemConstant.ALL) String khoangGiaCode,
			@RequestParam(name = "loaiDayCode", defaultValue = SystemConstant.ALL) String loaiDayCode,
			@RequestParam(name = "brandCode", defaultValue = SystemConstant.ALL) String brandCode,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "6") int limit) {
		// Begin
		OutPutPaginationProduct result = new OutPutPaginationProduct();
		result.setTotalPage((int) Math.ceil(
				(double) sanPhamService.getTotalItemByGender(gender, brandCode, loaiDayCode, khoangGiaCode, loaiDHCode)
						/ limit));

		result.setPage(page);
		int offset = page * limit - limit;
		List<ProductTransferDTO> products = sanPhamService.findAllByGender(gender, offset, limit, brandCode,
				loaiDayCode, khoangGiaCode, loaiDHCode);
		result.setListResult(products);
		model.addAttribute("listProducts", result);
		model.addAttribute("gender", gender);
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("loaiDays", loaiDayService.findAll());
		model.addAttribute("khoangGias", khoangGiaService.findAll());
		model.addAttribute("loaiDHs", productTypeService.findAll());
		model.addAttribute("brandCode", brandCode);
		model.addAttribute("loaiDayCode", loaiDayCode);
		model.addAttribute("khoangGiaCode", khoangGiaCode);
		model.addAttribute("loaiDHCode", loaiDHCode);
		model.addAttribute("sizeResult",
				sanPhamService.getTotalItemByGender(gender, brandCode, loaiDayCode, khoangGiaCode, loaiDHCode));
		return "productByGender";
	}

	@GetMapping("/searchProduct")
	public String productSearch(Model model, @RequestParam(name = "search") String search,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "8") int limit) {
		// Begin
		OutPutPaginationProduct result = new OutPutPaginationProduct();
		result.setTotalPage((int) Math.ceil((double) sanPhamService.getTotalItemSearch(search) / limit));
		result.setPage(page);
		int offset = page * limit - limit;
		List<ProductTransferDTO> products = sanPhamService.findByProductSearch(search, offset, limit);
		result.setListResult(products);
		model.addAttribute("listProducts", result);
		model.addAttribute("search", search);
		model.addAttribute("sizeResult", sanPhamService.getTotalItemSearch(search));
		return "searchResult";
	}

	@GetMapping("/productByBrand")
	public String productByBrand(Model model, @RequestParam(name = "gender") String gender,
			@RequestParam(name = "code") String code,
			@RequestParam(name = "loaiDHCode", defaultValue = SystemConstant.ALL) String loaiDHCode,
			@RequestParam(name = "khoangGiaCode", defaultValue = SystemConstant.ALL) String khoangGiaCode,
			@RequestParam(name = "loaiDayCode", defaultValue = SystemConstant.ALL) String loaiDayCode,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "6") int limit) {
		// Begin
		OutPutPaginationProduct result = new OutPutPaginationProduct();
		result.setPage(page);
		result.setTotalPage((int) Math
				.ceil((double) sanPhamService.getTotalItemByBrand(code, gender, loaiDayCode, khoangGiaCode, loaiDHCode)
						/ limit));
		int offset = page * limit - limit;
		List<ProductTransferDTO> products = sanPhamService.findByBrand(code, gender, offset, limit, loaiDayCode,
				khoangGiaCode, loaiDHCode);
		result.setListResult(products);
		model.addAttribute("listProducts", result);
		model.addAttribute("brand", brandService.findByCode(code));
		model.addAttribute("loaiDays", loaiDayService.findAll());
		model.addAttribute("khoangGias", khoangGiaService.findAll());
		model.addAttribute("loaiDHs", productTypeService.findAll());
		model.addAttribute("loaiDayCode", loaiDayCode);
		model.addAttribute("khoangGiaCode", khoangGiaCode);
		model.addAttribute("loaiDHCode", loaiDHCode);
		model.addAttribute("code", code);
		model.addAttribute("gender", gender);
		return "productByBrand";
	}

	@GetMapping("/productByPrice")
	public String productByPrice(Model model, @RequestParam(name = "gender") String gender,
			@RequestParam(name = "code") String code,
			@RequestParam(name = "loaiDHCode", defaultValue = SystemConstant.ALL) String loaiDHCode,
			@RequestParam(name = "brandCode", defaultValue = SystemConstant.ALL) String brandCode,
			@RequestParam(name = "loaiDayCode", defaultValue = SystemConstant.ALL) String loaiDayCode,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "6") int limit) {
		OutPutPaginationProduct result = new OutPutPaginationProduct();
		result.setPage(page);
		result.setTotalPage((int) Math.ceil(
				(double) sanPhamService.getTotalItemByPrice(code, gender, loaiDayCode, brandCode, loaiDHCode) / limit));
		int offset = page * limit - limit;
		List<ProductTransferDTO> products = sanPhamService.findAllByPrice(code, gender, offset, limit, loaiDayCode,
				brandCode, loaiDHCode);
		result.setListResult(products);
		model.addAttribute("listProducts", result);
		model.addAttribute("gender", gender);
		model.addAttribute("loaiDays", loaiDayService.findAll());
		model.addAttribute("loaiDHs", productTypeService.findAll());
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("code", code);
		model.addAttribute("gender", gender);
		model.addAttribute("loaiDHCode", loaiDHCode);
		model.addAttribute("brandCode", brandCode);
		model.addAttribute("loaiDayCode", loaiDayCode);
		model.addAttribute("sizeResult",
				sanPhamService.getTotalItemByPrice(code, gender, loaiDayCode, brandCode, loaiDHCode));
		model.addAttribute("name", khoangGiaService.getNameByCode(code));
		return "productByPrice";
	}

	@GetMapping("/productByProductType")
	public String productByProductType(Model model, @RequestParam(name = "gender") String gender,
			@RequestParam(name = "code") String code,
			@RequestParam(name = "brandCode", defaultValue = SystemConstant.ALL) String brandCode,
			@RequestParam(name = "khoangGiaCode", defaultValue = SystemConstant.ALL) String khoangGiaCode,
			@RequestParam(name = "loaiDayCode", defaultValue = SystemConstant.ALL) String loaiDayCode,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "9") int limit) {
		// Begin
		OutPutPaginationProduct result = new OutPutPaginationProduct();
		result.setPage(page);
		result.setTotalPage((int) Math.ceil(
				(double) sanPhamService.getTotalItemByProductType(code, gender, loaiDayCode, khoangGiaCode, brandCode)
						/ limit));
		int offset = page * limit - limit;
		List<ProductTransferDTO> products = sanPhamService.findByProductType(code, gender, offset, limit, loaiDayCode,
				khoangGiaCode, brandCode);
		result.setListResult(products);
		model.addAttribute("listProducts", result);
		model.addAttribute("brand", brandService.findByCode(code));
		model.addAttribute("loaiDays", loaiDayService.findAll());
		model.addAttribute("khoangGias", khoangGiaService.findAll());
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("loaiDayCode", loaiDayCode);
		model.addAttribute("khoangGiaCode", khoangGiaCode);
		model.addAttribute("brandCode", brandCode);
		model.addAttribute("code", code);
		model.addAttribute("gender", gender);
		model.addAttribute("sizeResult",
				sanPhamService.getTotalItemByProductType(code, gender, loaiDayCode, khoangGiaCode, brandCode));
		model.addAttribute("name", productTypeService.getNameByCode(code));
		return "productByProductType";
	}

	// Sản phẩm theo lại dây
	@GetMapping("/productByLoaiDay")
	public String productByLoaiDay(Model model, @RequestParam(name = "gender") String gender,
			@RequestParam(name = "code") String code,
			@RequestParam(name = "brandCode", defaultValue = SystemConstant.ALL) String brandCode,
			@RequestParam(name = "khoangGiaCode", defaultValue = SystemConstant.ALL) String khoangGiaCode,
			@RequestParam(name = "loaiDHCode", defaultValue = SystemConstant.ALL) String loaiDHCode,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "6") int limit) {
		// Begin
		OutPutPaginationProduct result = new OutPutPaginationProduct();
		result.setPage(page);
		result.setTotalPage((int) Math
				.ceil((double) sanPhamService.getTotalItemByLoaiDay(code, gender, loaiDHCode, khoangGiaCode, brandCode)
						/ limit));
		int offset = page * limit - limit;
		List<ProductTransferDTO> products = sanPhamService.findByLoaiDay(code, gender, offset, limit, loaiDHCode,
				khoangGiaCode, brandCode);
		result.setListResult(products);
		model.addAttribute("listProducts", result);
		model.addAttribute("brand", brandService.findByCode(code));
		model.addAttribute("loaiDHs", productTypeService.findAll());
		model.addAttribute("khoangGias", khoangGiaService.findAll());
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("loaiDHCode", loaiDHCode);
		model.addAttribute("khoangGiaCode", khoangGiaCode);
		model.addAttribute("brandCode", brandCode);
		model.addAttribute("code", code);
		model.addAttribute("gender", gender);
		model.addAttribute("name", loaiDayService.getNameByCode(code));
		model.addAttribute("sizeResult",
				sanPhamService.getTotalItemByLoaiDay(code, gender, loaiDHCode, khoangGiaCode, brandCode));
		return "productByLoaiDay";
	}

	// Chi tiết sản phẩm//
	@GetMapping("/productDetail")
	public String productDetail(Model model, @RequestParam(name = "code") String code) {
		ProductTransferDTO product = sanPhamService.getProductDetail(code);
		List<ProductTransferDTO> sanPhamLienQuans = sanPhamService.getSanPhamLienQuan(code);
		model.addAttribute("product", product);
		if (sanPhamLienQuans.size() != 0) {
			model.addAttribute("sanPhamLienQuans", sanPhamLienQuans);
		}		
		return "productDetail";
	}

	// Chi tiết sản phẩm//
	@GetMapping("/product_detailById")
	public String productDetailById(Model model, @RequestParam long id) {
		SanPhamEntity entity = sanPhamService.getOne(id);
		ProductTransferDTO product = sanPhamService.getProductDetail(entity.getCode());
		List<ProductTransferDTO> sanPhamLienQuans = sanPhamService.getSanPhamLienQuan(entity.getCode());
		model.addAttribute("product", product);
		if (sanPhamLienQuans.size() != 0) {
			model.addAttribute("sanPhamLienQuans", sanPhamLienQuans);
		}
		return "productDetail";
	}
	//Phiên bản đặc biệt
	@GetMapping("/phien-ban-dac_biet")
	public String phien_ban_dac_biet(Model model,@RequestParam(name = "gender",defaultValue = SystemConstant.ALL) String gender,
			@RequestParam(name = "brandCode", defaultValue = SystemConstant.ALL) String brandCode,
			@RequestParam(name = "khoangGiaCode", defaultValue = SystemConstant.ALL) String khoangGiaCode,
			@RequestParam(name = "loaiDayCode", defaultValue = SystemConstant.ALL) String loaiDayCode,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "6") int limit) {
		// Begin
		OutPutPaginationProduct result = new OutPutPaginationProduct();
		result.setPage(page);
		result.setTotalPage((int) Math.ceil(
				(double) sanPhamService.getTotalItemByProductSpecial(loaiDayCode, khoangGiaCode, brandCode,gender)
						/ limit));
		int offset = page * limit - limit;
		List<ProductTransferDTO> products = sanPhamService.findByProductSpecial(offset, limit, loaiDayCode, khoangGiaCode, brandCode,gender);
		result.setListResult(products);
		model.addAttribute("listProducts", result);
		model.addAttribute("loaiDays", loaiDayService.findAll());
		model.addAttribute("khoangGias", khoangGiaService.findAll());
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("loaiDayCode", loaiDayCode);
		model.addAttribute("khoangGiaCode", khoangGiaCode);
		model.addAttribute("brandCode", brandCode);
		model.addAttribute("gender", gender);
		return "phien_ban_dac_biet";
	}
	//Phiên bản đặc biệt
		@GetMapping("/san-pham-moi")
		public String san_pham_moi(Model model,@RequestParam(name = "gender",defaultValue = SystemConstant.ALL) String gender,
				@RequestParam(name = "brandCode", defaultValue = SystemConstant.ALL) String brandCode,
				@RequestParam(name = "khoangGiaCode", defaultValue = SystemConstant.ALL) String khoangGiaCode,
				@RequestParam(name = "loaiDayCode", defaultValue = SystemConstant.ALL) String loaiDayCode,
				@RequestParam(name = "loaiDHCode", defaultValue = SystemConstant.ALL) String loaiDHCode,
				@RequestParam(name = "page", defaultValue = "1") int page,
				@RequestParam(name = "limit", defaultValue = "9") int limit) {
			// Begin
			OutPutPaginationProduct result = new OutPutPaginationProduct();
			result.setPage(page);
			result.setTotalPage((int) Math.ceil(
					(double) sanPhamService.getTotalItem()
							/ limit));
			int offset = page * limit - limit;
			List<ProductTransferDTO> products = sanPhamService.getProductLatest(offset, limit, loaiDayCode, khoangGiaCode, brandCode, gender,loaiDHCode);
			result.setListResult(products);
			model.addAttribute("listProducts", result);
			model.addAttribute("loaiDays", loaiDayService.findAll());
			model.addAttribute("khoangGias", khoangGiaService.findAll());
			model.addAttribute("brands", brandService.findAll());
			model.addAttribute("loaiDHs", productTypeService.findAll());
			model.addAttribute("loaiDayCode", loaiDayCode);
			model.addAttribute("khoangGiaCode", khoangGiaCode);
			model.addAttribute("brandCode", brandCode);
			model.addAttribute("gender", gender);
			model.addAttribute("loaiDHCode", loaiDHCode);
			return "san_pham_moi";
		}
		// Top sellers
		@GetMapping("/top-sellers")
		public String top_sellers(Model model,
				@RequestParam(name = "page", defaultValue = "1") int page,
				@RequestParam(name = "limit", defaultValue = "6") int limit) {
			OutPutPaginationProduct result = new OutPutPaginationProduct();
			result.setPage(page);
			result.setTotalPage((int) Math.ceil(
					(double) sanPhamService.getTotalItemTopSellers()
							/ limit));
			int offset = page * limit - limit;
			List<ProductTransferDTO> products = sanPhamService.getTopSellers(offset, limit);
			result.setListResult(products);
			model.addAttribute("listProducts", result);
			model.addAttribute("moiNhats", tinTucService.getMoiNhatByDM(TinTucConstant.reviewSP));
			model.addAttribute("docNhieus", tinTucService.getDocNhieuByDM(TinTucConstant.reviewSP));
			return "top_sellers";
		}
}
