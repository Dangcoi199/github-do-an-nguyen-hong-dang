package com.nguyenhongdang.dto;

import java.security.Timestamp;

import lombok.Data;
@Data
public class AbstractDTO<T> {
	private Long id;
	private Timestamp createdDate;
	private Timestamp modifiedDate;
	private String createdBy;
	private String modifiedBy;
	private long[] ids;
}
