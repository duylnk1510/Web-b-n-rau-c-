package com.poly.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.bean.Account;
import com.poly.bean.Category;
import com.poly.bean.Order;
import com.poly.bean.OrderDetail;
import com.poly.bean.Product;
import com.poly.dao.AccountDAO;
import com.poly.dao.CategoryDAO;
import com.poly.dao.OrderDAO;
import com.poly.dao.OrderDetailDAO;
import com.poly.dao.ProductDAO;
import com.poly.service.CookieService;
import com.poly.service.ShoppingCartService;
import com.poly.service.ShoppingCartServiceImpl;

@Controller
public class IndexController {
	@Autowired
	HttpServletRequest req;
	
	
	@Autowired
	CategoryDAO cDao;
	
	@Autowired
	ProductDAO pDao;
	
	@Autowired
	OrderDAO oDao;
	
	@Autowired
	AccountDAO aDao;
	
	@Autowired
	OrderDetailDAO odDao;
	
	@Autowired
	ShoppingCartServiceImpl spServiceImpl;
	
	@Autowired
	ShoppingCartService spService;
	
	@Autowired 
	CookieService cookieService;
	

	@GetMapping("/home/index")
	public String index(Model model, HttpSession session) {
		model.addAttribute("listCategories", cDao.findAll());
		model.addAttribute("loadCategories", cDao.findAll());
		model.addAttribute("display", "yes");
		Cookie cookie = cookieService.get("proT");
		if(cookie == null) {
			cookieService.add("proT", "", 720);
		}
		
		Account user =(Account) session.getAttribute("user");
		if (user != null) {
			Order order = oDao.checkPaid(user.getUsername(), false);
			if (order == null) {
				Account account = aDao.getOne(user.getUsername());
				Order createOrder = new Order();
				createOrder.setAccount(account);
				createOrder.setAddress("Trung Mĩ Tây");
				createOrder.setCreateDate(new Date());
				createOrder.setPaid(false);
				oDao.save(createOrder);
				System.out.println("tạo hóa đơn");
			}
		}
		spServiceImpl.fillDataToMap();
		
		topProd(model);	
		model.addAttribute("sumQty", spService.getCount());// đếm tổng số lượng sp đã mua
		
		model.addAttribute("LinkPage", "home.jsp");
		return "index";
	}
	
	
	
	@GetMapping("/home/cart")
	public String cart(Model model) {
		model.addAttribute("listCategories", cDao.findAll());
		Cookie cookie = cookieService.get("proT");
		if (cookie != null && cookie.getValue() != null) {
			model.addAttribute("cart", spService);
		}
		spServiceImpl.fillDataToMap();
		model.addAttribute("amount", spService.getAmount());
		model.addAttribute("sumQty", spService.getCount());// đếm tổng số lượng sp đã mua
		
		model.addAttribute("LinkPage", "cart.jsp");
		return "index";
	}
	
	@GetMapping("/home/{id}")
	public String loadFollowId(Model model, @PathVariable("id") String id) {
		model.addAttribute("display", "yes");
		model.addAttribute("listCategories", cDao.findAll());

		List<Category>  list = new ArrayList<>();
		list.add(cDao.getOne(id));
		model.addAttribute("sumQty", spService.getCount());// đếm tổng số lượng sp đã mua
		model.addAttribute("loadCategories", list);
		model.addAttribute("LinkPage", "home.jsp");
		return "index";
	}
	
	@GetMapping("/home/search")
	public String search(Model model, @RequestParam("searchProd") String name) {
		model.addAttribute("listCategories", cDao.findAll());
		model.addAttribute("display", "yes");
		List<Product> listTemp = pDao.findByNameContaining(name);
		model.addAttribute("sumQty", spService.getCount());// đếm tổng số lượng sp đã mua
		model.addAttribute("loadProducts", listTemp);
		model.addAttribute("LinkPage", "home.jsp");
		return "index";
	}
	
	public void topProd(Model model) {
		List<Integer> idP = pDao.topProduct();
		
		List<Product> listP = new ArrayList<>();
		for (Integer id : idP) {
			Product p = pDao.getOne(id);
			listP.add(p);
		}
		
		model.addAttribute("topP", listP);
	}
}
