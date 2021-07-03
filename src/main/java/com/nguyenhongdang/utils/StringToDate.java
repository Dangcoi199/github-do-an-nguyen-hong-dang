package com.nguyenhongdang.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class StringToDate {
	public Date stringtoDate(String dateString) {
		Date date = new Date();
		SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd");
		try {
			 date = formatter.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
}
