package com.nguyenhongdang.dto;

import lombok.Data;

@Data
public class TinTucDTO extends AbstractDTO<TinTucDTO>{
	 private String title;
	 private String image;
	 private String bytes;
	 private String shortDescription;
	 private String content;
	 private String danhMuc;
}
