package com.nguyenhongdang.dto;

import lombok.Data;

@Data
public class StatusDTO extends AbstractDTO<StatusDTO>{
	private String statusName;
	private String shortDescription;
}
