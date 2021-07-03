package com.nguyenhongdang.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class UploadFile {
	public void saveFile(String file, HttpServletRequest request, String category, String fileName) throws IOException {

		byte[] bytes = Base64.getDecoder().decode(file.getBytes());
		String dirFile = "src\\main\\resources\\static\\uploads\\" + category;
		File fileDir = new File(dirFile);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		if (bytes != null) {
			File serveFile= new File(dirFile+File.separator+fileName);
			try(BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serveFile))) {
				stream.write(bytes);
			} catch (IOException e) {
				 e.printStackTrace();
			}
		}
		System.out.println(dirFile);

	}
	public void saveMultipleFiles(String[] files , HttpServletRequest request, String category , String[] fileNames) {
		String dirFile = "src\\main\\resources\\static\\uploads\\" + category;
		File fileDir = new File(dirFile);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		for(int i= 0 ; i< files.length; i++) {
			byte[] bytes = Base64.getDecoder().decode(files[i].getBytes());
			if (bytes != null) {
				File serveFile= new File(dirFile+File.separator+fileNames[i]);
				try(BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serveFile))) {
					stream.write(bytes);
				} catch (IOException e) {
					 e.printStackTrace();
				}
			}
		}
	}

}
