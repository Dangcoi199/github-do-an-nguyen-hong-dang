package com.nguyenhongdang.constant;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DuongKinhMatConstant {
	   public static final String dk21 = "21 mm";
		 
	    public static final String dk26 = "26 mm";
	 
	    public static final String dk34 = "34 mm";
	    
	    public static final String dk36 = "36 mm";
	    
	    public static final String dk38 = "38 mm";
		 
	    public static final String dk40 = "40 mm";
	 
	    public static final String dk41 = "41 mm";
	    
	    public static final String dk42 = "42 mm";
	    
	    public List<String> getListDuongKinh(){
	    	List<String> duongKinhs = new ArrayList<>();
	    	duongKinhs.add(dk21);
	    	duongKinhs.add(dk26);
	    	duongKinhs.add(dk34);
	    	duongKinhs.add(dk36);
	    	duongKinhs.add(dk38);
	    	duongKinhs.add(dk40);
	    	duongKinhs.add(dk41);
	    	duongKinhs.add(dk42);
	    	return duongKinhs;
	    }
}
