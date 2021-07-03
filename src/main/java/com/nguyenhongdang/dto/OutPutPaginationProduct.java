package com.nguyenhongdang.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class OutPutPaginationProduct {
	private Integer totalPage;
	private Integer page;
	private List<ProductTransferDTO> listResult = new ArrayList<>();

}
