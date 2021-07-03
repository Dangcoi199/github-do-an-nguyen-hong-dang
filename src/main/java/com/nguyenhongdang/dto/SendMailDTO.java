package com.nguyenhongdang.dto;

import lombok.Data;

@Data
public class SendMailDTO {
	private String title;
	private String content;
	private String toEmail;
	private long id;
}
