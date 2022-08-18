package com.poly.controller;

import java.util.Date;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.bean.Account;
import com.poly.bean.Order;
import com.poly.dao.CategoryDAO;
import com.poly.dao.OrderDAO;
import com.poly.service.CookieService;
import com.poly.service.SessionService;
import com.poly.service.ShoppingCartService;
import com.poly.service.ShoppingCartServiceImpl;

@Controller
public class CartController {
	
	@Autowired
	ShoppingCartService spService;
	
	@Autowired
	ShoppingCartServiceImpl spImpl;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	CookieService cookieService;
	
	@Autowired
	CategoryDAO cDao;
	
	@Autowired
	OrderDAO oDao;
	
	@GetMapping("/cart/delete/{id}")
	public String deleteItem(@PathVariable("id") Integer idItem) {
		System.out.println("đã vào xóa");
		spService.remove(idItem);
		
		return "redirect:/home/cart";
	}
	
	@PostMapping("/cart/update/{id}")
	public String updateItem(@PathVariable("id") Integer idItem, @RequestParam("qty") Integer qty) {
		System.out.println("đã vào update");
		spService.update(idItem, qty);
		return "redirect:/home/cart";
	}
	
	@GetMapping("/cart/checkout")
	public String checkout(Model model) {
		System.out.println("Thanh toán xong");
		model.addAttribute("listCategories", cDao.findAll());
		model.addAttribute("sumQty", spService.getCount());// đếm tổng số lượng sp đã mua
		Account account = sessionService.get("user");
		fillInfoUser(model, account);
		
		model.addAttribute("LinkPage", "pay.jsp");
		return "index";
	}
	
	@RequestMapping("/cart/pay")
	public String pay(@RequestParam("address") String address, Model model) {
		Account account = sessionService.get("user");
		Order order = oDao.checkPaid(account.getUsername(), false);
		order.setAddress(address);
		order.setCreateDate(new Date());
		order.setPaid(true);
		oDao.save(order);
		spService.clear();
		cookieService.remove("proT");
		model.addAttribute("sumQty", 0);// đếm tổng số lượng sp đã mua
		model.addAttribute("listCategories", cDao.findAll());
		model.addAttribute("LinkPage", "pay_success.jsp");
		return "index";
	}
	
	public void fillInfoUser(Model model, Account account) {
		model.addAttribute("user", account);
		model.addAttribute("cart", spService);
		model.addAttribute("amount", spService.getAmount());
		
	}
	
}
