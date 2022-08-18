package com.poly.admin_controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.poly.bean.Category;
import com.poly.bean.Order;
import com.poly.bean.Product;
import com.poly.dao.CategoryDAO;
import com.poly.dao.OrderDAO;
import com.poly.dao.ProductDAO;

@Controller
public class AdminController {

	@Autowired
	CategoryDAO cDao;

	@Autowired
	ProductDAO pDao;

	@Autowired
	OrderDAO oDao;

	@RequestMapping("/admin/index")
	public String indexAdmin(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("LinkPage", "admin_product.jsp");
		return "/admin/admin_index";
	}

	@RequestMapping("/admin/categories")
	public String crudCategorie(Model model) {
		model.addAttribute("LinkPage", "admin_categories.jsp");
		return "/admin/admin_index";
	}

	@RequestMapping("/admin/listProd")
	public String listProd(Model model, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = null;
		if (p.orElse(0) == 0) {
			pageable = PageRequest.of(0, 4);
		} else {
			pageable = PageRequest.of((p.orElse(0)) - 1, 4);
		}

		Page<Product> page = pDao.findAll(pageable);
		List<Integer> numP = new ArrayList<>();
//		for (int i = 1; i < page.getTotalPages()-1; i++) {
//			numP.add(i);
//		}
//		model.addAttribute("numP", numP);
		model.addAttribute("page", page);
		model.addAttribute("LinkPage", "admin_list_product.jsp");
		return "/admin/admin_index";
	}

	@RequestMapping("/admin/editProd/{id}")
	public String editProudct(@PathVariable("id") Integer idP, Model model) {
		Product product = pDao.getOne(idP);
		model.addAttribute("product", product);
		model.addAttribute("img", product.getImage());
		model.addAttribute("idPro", idP);
		model.addAttribute("LinkPage", "admin_product.jsp");
		return "/admin/admin_index";
	}

	@RequestMapping("/admin/order")
	public String viewListOrder(Model model, HttpServletRequest req) {
		String search = req.getParameter("search");
		if (search != null) {
			List<Order> oList = oDao.findOrderByName("%" + search + "%");// ok
			model.addAttribute("oList", oList);
		} else {
			model.addAttribute("oList", oDao.findAll());
		}

		model.addAttribute("LinkPage", "admin_order.jsp");
		return "/admin/admin_index";
	}

	@ModelAttribute("Categories")
	public List<Category> getCategory() {
		return cDao.findAll();
	}
}
