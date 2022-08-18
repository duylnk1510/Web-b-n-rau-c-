package com.poly.service;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ParamService {
	
	@Autowired
	HttpServletRequest req;

	public String getString(String name, String defaultValue){
		if (name == null || name.isEmpty() ) {
			return defaultValue;
		}
		return name;
	}
	
	public int getInt(String name, int defaultValue) {
		if (name == null || name.isEmpty() ) {
			return defaultValue;
		}
		return Integer.parseInt(name);
	}
	
	public double getDouble(String name, double defaultValue) {
		if (name == null || name.isEmpty() ) {
			return defaultValue;
		}
		return Double.parseDouble(name);
	}

	public boolean getBoolean(String name, boolean defaultValue) {
		if (name == null || name.isEmpty() ) {
			return defaultValue;
		}
		return Boolean.parseBoolean(name);
	}

	
	public Date getDate(String name, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        
		if (name == null || name.isEmpty() ) {
			return null;
		}
		Date dateFormat = null;
		try {
			dateFormat = formatter.parse(name);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Sai định dạng");
		}
		return dateFormat;
	}
	
	public File save(MultipartFile file, String path) {
		if (file == null ) {
			return null;
		}
		System.out.println("getContextPath: "+req.getRealPath(path));
		File dir = new File(req.getRealPath(path+ "/images/"));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File newFile = new File(dir, file.getOriginalFilename());
		System.out.println(newFile);
		try {
			System.out.println("File: " + file);
			file.transferTo(newFile);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
		return newFile;
	}

	
}
