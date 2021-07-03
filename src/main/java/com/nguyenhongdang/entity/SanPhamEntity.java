package com.nguyenhongdang.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class SanPhamEntity extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "code", nullable = false)
	private String code;

	@Column(name = "image")
	private String image;

	@Column(name = "gender")
	private String gender;

	@Column(name = "quantity", nullable = false)
	private int quantity;

	@Column(name = "price" )
	private long price;

	@Column(name = "show_detail", nullable = false)
	private long showDetail;

	@Column(name = "status", nullable = false)
	private Integer status = 1;
	
	@Column(name = "content", columnDefinition = "TEXT")
	private String content;
	
	@Column(name = "do_chiu_nuoc")
	private String  doChiuNuoc;
	
	@Column(name = "duong_kinh_mat")
	private String duongKinhMat;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id")
	private BrandEntity brand;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chat_lieu_mat_id")
	private ChatLieuMatEntity chatLieuMat;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chat_lieu_vo_id")
	private ChatLieuVoEntity chatLieuVo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kieu_dong_ho_id")
	private KieuDongHoEntity kieuDongHo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loai_day_id")
	private LoaiDayEntity loaiDay;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_type_id")
	private ProductTypeEntity productType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "baohanh_id")
	private BaoHanhEntity baohanh;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<CTHoaDonNhapEntity> chiTietHDNs = new ArrayList<>();

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ImagesEntity> imageDetails = new ArrayList<>();

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ProductSaleEntity> sales = new ArrayList<>();
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<DanhGiaEntity> danhGias = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getQuantity() {
		return quantity;
	}
	
	

	public BaoHanhEntity getBaohanh() {
		return baohanh;
	}

	public void setBaohanh(BaoHanhEntity baohanh) {
		this.baohanh = baohanh;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getShowDetail() {
		return showDetail;
	}

	public void setShowDetail(long showDetail) {
		this.showDetail = showDetail;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BrandEntity getBrand() {
		return brand;
	}

	public void setBrand(BrandEntity brand) {
		this.brand = brand;
	}

	public ChatLieuMatEntity getChatLieuMat() {
		return chatLieuMat;
	}

	public void setChatLieuMat(ChatLieuMatEntity chatLieuMat) {
		this.chatLieuMat = chatLieuMat;
	}

	public ChatLieuVoEntity getChatLieuVo() {
		return chatLieuVo;
	}

	public void setChatLieuVo(ChatLieuVoEntity chatLieuVo) {
		this.chatLieuVo = chatLieuVo;
	}

	public KieuDongHoEntity getKieuDongHo() {
		return kieuDongHo;
	}

	public void setKieuDongHo(KieuDongHoEntity kieuDongHo) {
		this.kieuDongHo = kieuDongHo;
	}

	public LoaiDayEntity getLoaiDay() {
		return loaiDay;
	}

	public void setLoaiDay(LoaiDayEntity loaiDay) {
		this.loaiDay = loaiDay;
	}

	public ProductTypeEntity getProductType() {
		return productType;
	}

	public void setProductType(ProductTypeEntity productType) {
		this.productType = productType;
	}


	public List<ImagesEntity> getImageDetails() {
		return imageDetails;
	}

	public void setImageDetails(List<ImagesEntity> imageDetails) {
		this.imageDetails = imageDetails;
	}

	public long getId() {
		return id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<ProductSaleEntity> getSales() {
		return sales;
	}

	public void setSales(List<ProductSaleEntity> sales) {
		this.sales = sales;
	}

	public List<CTHoaDonNhapEntity> getChiTietHDNs() {
		return chiTietHDNs;
	}

	public void setChiTietHDNs(List<CTHoaDonNhapEntity> chiTietHDNs) {
		this.chiTietHDNs = chiTietHDNs;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDoChiuNuoc() {
		return doChiuNuoc;
	}

	public void setDoChiuNuoc(String doChiuNuoc) {
		this.doChiuNuoc = doChiuNuoc;
	}

	public String getDuongKinhMat() {
		return duongKinhMat;
	}

	public void setDuongKinhMat(String duongKinhMat) {
		this.duongKinhMat = duongKinhMat;
	}

	public List<DanhGiaEntity> getDanhGias() {
		return danhGias;
	}

	public void setDanhGias(List<DanhGiaEntity> danhGias) {
		this.danhGias = danhGias;
	}
	
	
	
	

}
