package com.nguyenhongdang.constant;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DoChiuNuocConstant {
	public static final String chiuNuoc3 = "3 ATM";
	 
	public static final String chiuNuoc5 = "5 ATM";
	
	public static final String chiuNuoc8 = "8 ATM";
   
    public List<String> getListDoChiuNuoc(){
    	List<String> doChiuNuocs = new ArrayList<>();
    	doChiuNuocs.add(chiuNuoc3);
    	doChiuNuocs.add(chiuNuoc5);
    	doChiuNuocs.add(chiuNuoc8);
    	return doChiuNuocs;
    }
}
