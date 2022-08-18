package com.poly.admin_controller;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poly.bean.Product;
import com.poly.dao.CategoryDAO;
import com.poly.dao.ProductDAO;

@Controller
public class Admin_ProductController {
	
	@Autowired
	ProductDAO pDao;
	
	@Autowired
	CategoryDAO cDao;
	
	@Autowired
	HttpServletRequest req;
	
	@RequestMapping("/admin/addProduct")
	public String addProudct(Product product, @RequestParam("upload") MultipartFile fileImg) {
		product.setAvailable(true);
		product.setCreateDate(new Date());
		save(fileImg);
		pDao.save(product);
		return "redirect:/admin/index";
	}
	
	public File save(MultipartFile file) {
		if (file == null ) {
			return null;
		}
		File dir = new File(req.getRealPath("/img/img_p/"));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File newFile = new File(dir, file.getOriginalFilename());
		try {
			System.out.println("File: " + file);
			file.transferTo(newFile);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return newFile;
	}
	
	///admin/resetProd
	@RequestMapping("/admin/resetProd")
	public String resetProduct() {
		return "redirect:/admin/index";
	}
	
	@RequestMapping("/admin/deleteProd/{id}")
	public String deleteProudct(@PathVariable("id") Integer idP, Model model) {
		Product p = pDao.getOne(idP);
 		pDao.delete(p);
		return "redirect:/admin/listProd";
	}
	
	@RequestMapping("/admin/updatePro/{id}")
	public String updateProudct(@PathVariable("id") Integer idP, Product product) {
		product.setId(idP);
		product.setAvailable(true);
 		pDao.save(product);
		return "redirect:/admin/listProd";
	}
	
}
