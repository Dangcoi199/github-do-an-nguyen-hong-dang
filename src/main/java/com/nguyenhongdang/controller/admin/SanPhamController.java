package com.nguyenhongdang.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenhongdang.constant.DoChiuNuocConstant;
import com.nguyenhongdang.constant.DuongKinhMatConstant;
import com.nguyenhongdang.conveter.ProductEntityTransferDTO;
import com.nguyenhongdang.conveter.ProductToDiscount;
import com.nguyenhongdang.dto.ProductTransferDTO;
import com.nguyenhongdang.dto.UpdateDiscountDTO;
import com.nguyenhongdang.entity.BaoHanhEntity;
import com.nguyenhongdang.entity.BrandEntity;
import com.nguyenhongdang.entity.ChatLieuMatEntity;
import com.nguyenhongdang.entity.ChatLieuVoEntity;
import com.nguyenhongdang.entity.KieuDongHoEntity;
import com.nguyenhongdang.entity.LoaiDayEntity;
import com.nguyenhongdang.entity.ProductTypeEntity;
import com.nguyenhongdang.entity.SaleEntity;
import com.nguyenhongdang.entity.SanPhamEntity;
import com.nguyenhongdang.service.admin.IBaoHanhService;
import com.nguyenhongdang.service.admin.IBrandService;
import com.nguyenhongdang.service.admin.IChatLieuMatService;
import com.nguyenhongdang.service.admin.IChatLieuVoService;
import com.nguyenhongdang.service.admin.IKieuDongHoService;
import com.nguyenhongdang.service.admin.ILoaiDayService;
import com.nguyenhongdang.service.admin.IProductTypeService;
import com.nguyenhongdang.service.admin.ISaleService;
import com.nguyenhongdang.service.admin.ISanPhamService;

@Controller
public class SanPhamController {
	@Autowired
	private ISanPhamService sanPhamService;
	@Autowired
	private IBrandService brandService;
	
	@Autowired
	private IChatLieuMatService chatLieuMatService;
	
	@Autowired
	private IChatLieuVoService chatLieuVoService;
		
	@Autowired
	private IKieuDongHoService kieuDHService;
	
	@Autowired
	private ILoaiDayService loaiDayService;
	
	@Autowired
	private IProductTypeService productTypeService;
	
	@Autowired
	private ISaleService saleService;
	
	@Autowired 
	private IBaoHanhService baoHanhService;
	
	@Autowired
	private DoChiuNuocConstant doChiuNuocConstant;
	
	@Autowired
	private DuongKinhMatConstant duongKinhMatConstant;
	
	@Autowired
	private ProductEntityTransferDTO productToDTO;
	
	@Autowired
	private ProductToDiscount toDiscountDTO;
	
	
	
	@GetMapping(value = "/admin_listSanPham")
	public String showList(Model model) {
		List<SanPhamEntity> productEntities = sanPhamService.findAll();
		List<ProductTransferDTO> products = productToDTO.transferDTO(productEntities);
		model.addAttribute("products", products);
		List<SaleEntity> saleEvents = saleService.findAll();
		model.addAttribute("saleEvents", saleEvents);
		return "admin/sanpham/showlist";
	}
	@GetMapping(value = "/admin_addSanPham")
	public String add(Model model) {
		List<BrandEntity> brands = brandService.findAll();
		List<ChatLieuMatEntity> chatLieuMats = chatLieuMatService.findAll();
		List<ChatLieuVoEntity> chatlieuVos =chatLieuVoService.findAll();
		List<String> doChiuNuocs = doChiuNuocConstant.getListDoChiuNuoc();
		List<String> duongKinhs = duongKinhMatConstant.getListDuongKinh();
		List<KieuDongHoEntity> kieuDHs = kieuDHService.findAll();
		List<LoaiDayEntity> loaiDays = loaiDayService.findAll();
		List<ProductTypeEntity> productTypes = productTypeService.findAll();
		List<BaoHanhEntity> baoHanhs = baoHanhService.findAll();
		model.addAttribute("brands", brands);
		model.addAttribute("chatLieuMats", chatLieuMats);
		model.addAttribute("chatlieuVos", chatlieuVos);
		model.addAttribute("doChiuNuocs", doChiuNuocs);
		model.addAttribute("duongKinhs", duongKinhs);
		model.addAttribute("kieuDHs", kieuDHs);
		model.addAttribute("loaiDays", loaiDays);
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("baoHanhs", baoHanhs);
		return "admin/sanpham/add";
	}
	@GetMapping(value = "/admin_updateSanPham")
	public String update(@RequestParam(value = "id") long id, Model model) {
		SanPhamEntity product = sanPhamService.getOne(id);
		List<BrandEntity> brands = brandService.findAll();
		List<ChatLieuMatEntity> chatLieuMats = chatLieuMatService.findAll();
		List<ChatLieuVoEntity> chatlieuVos =chatLieuVoService.findAll();
		List<String> doChiuNuocs = doChiuNuocConstant.getListDoChiuNuoc();
		List<String> duongKinhs = duongKinhMatConstant.getListDuongKinh();
		List<KieuDongHoEntity> kieuDHs = kieuDHService.findAll();
		List<LoaiDayEntity> loaiDays = loaiDayService.findAll();
		List<ProductTypeEntity> productTypes = productTypeService.findAll();
		List<BaoHanhEntity> baoHanhs = baoHanhService.findAll();
		model.addAttribute("product", product);
		model.addAttribute("brands", brands);
		model.addAttribute("chatLieuMats", chatLieuMats);
		model.addAttribute("chatlieuVos", chatlieuVos);
		model.addAttribute("doChiuNuocs", doChiuNuocs);
		model.addAttribute("duongKinhs", duongKinhs);
		model.addAttribute("kieuDHs", kieuDHs);
		model.addAttribute("loaiDays", loaiDays);
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("baoHanhs", baoHanhs);
		return "admin/sanpham/edit";
	}
	@GetMapping(value = "/admin_updateProductSale")
	public String addSaleProduct(@RequestParam(value = "id") long id, Model model) {
		SanPhamEntity productEntity = sanPhamService.getOne(id);
		UpdateDiscountDTO product = toDiscountDTO.toDiscountDto(productEntity);
		List<SaleEntity> saleEvents = saleService.findAll();
		model.addAttribute("product", product);
		model.addAttribute("saleEvents", saleEvents);
		return "admin/sanpham/addSale";
	}
}
