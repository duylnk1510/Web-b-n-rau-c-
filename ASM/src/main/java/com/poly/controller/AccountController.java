package com.poly.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.bean.Account;
import com.poly.dao.AccountDAO;
import com.poly.service.CookieService;
import com.poly.service.SessionService;

@Controller
public class AccountController {
	@Autowired
	AccountDAO dao;

	@Autowired
	HttpServletRequest req;

	@Autowired
	SessionService session;

	@Autowired
	CookieService cookieService;

	@GetMapping("/account/login")
	public String login(Model model) {
		System.out.println("login");
		//session.remove("security-uri");
		// checkSaveAccount(model);
		return "/account/login";
	}

	@GetMapping("/account/regis")
	public String regis() {
		
		return "/account/regis";
	}

	@GetMapping("/account/logout")
	public String logout() {
		session.remove("security-uri");
		session.remove("user");
		return "redirect:/home/index";
	}

	@PostMapping("/account/login")
	public String login(Model model, @RequestParam("username") String username,
			@RequestParam("password") String password) {
		try {
			Boolean rm = Boolean.parseBoolean(req.getParameter("remember"));

			Account user = dao.getOne(username);
			System.out.println(user.toString());

			if (!user.getPassword().equals(password)) {
				model.addAttribute("message", "Invalid password");
			} else {
				// saveAccount(rm, user);
				session.set("user", user);
				String uri = session.get("security-uri");
				System.out.println(uri + " uri");
				if (user.getAdmin()) {//admin
					return "redirect:/admin/index";
				} 
				if (uri != null) {
					System.out.println("uri");
					return "redirect:" + uri;
				}
				return "redirect:/home/index";
				
//				if (!user.getAdmin()) {//user
//					model.addAttribute("message", "Login succeed");
//					return "redirect:/home/cart";
//				} else {//admin
//					return "redirect:/admin/index";
//				}
				
			}
		} catch (Exception e) {
			model.addAttribute("message", "Invalid username");
		}
		return "/account/login";
	}

//	public void saveAccount(boolean rm, Account user) {
//		if (rm == true) {
//			cookieService.add("username", user.getUsername(), 720);
//			cookieService.add("password", user.getPassword(), 720);
//		}
//	}

//	public void checkSaveAccount(Model model) {
//		String username = cookieService.getValue("username");
//
//		if (username != null) {
//			model.addAttribute("ch", "checked");// cho check vào remember khi tk đã có trong cookie
//		}
//	}

}
