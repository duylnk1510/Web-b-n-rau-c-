package com.poly.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.poly.bean.Account;
import com.poly.service.SessionService;


@Service
public class AuthInterceptor implements HandlerInterceptor{
	@Autowired
	SessionService session;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("đây này");
		String uri = request.getRequestURI();
		Account user = session.get("user");
		//System.out.println(uri + " uri-user " + user);
		String error = "";
		if(user == null) {
			System.out.println("null");
			error = "Please login!";
		} else if(!user.getAdmin() && uri.startsWith("/admin/")) {
			error = "Access denied!";
		}
		System.out.println(error + " error");
		if(error.length() > 0) {
			System.out.println("error");
			session.set("security-uri", uri);
//			if (user != null && user.getAdmin() == true) {
//				System.out.println(" bằng true");
//				session.set("security-uri", "admin/index");
//			}
//			else if(user != null && user.getAdmin() == true){
//				session.set("security-uri", "/admin/index");
//			}
			
			response.sendRedirect("/account/login?error=" + error);
			return false;
		}
		return true;
	}
	
	
}


