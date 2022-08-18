package com.poly.admin_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.bean.OrderDetail;
import com.poly.dao.OrderDetailDAO;

@Controller
public class Admin_OrderController {
	
	@Autowired
	OrderDetailDAO odDao;

	@RequestMapping("/admin/orderDetail/{id}")
	public String showDetail(Model model, @PathVariable("id") Long idO) {
		List<OrderDetail> list = odDao.findOrderDetailById(idO);
		
		model.addAttribute("odList", list);
		model.addAttribute("LinkPage", "admin_orderDetail.jsp");
		return "/admin/admin_index";
	}
}
