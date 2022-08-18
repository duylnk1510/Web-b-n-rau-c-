package com.poly.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.bean.Account;
import com.poly.bean.Category;
import com.poly.bean.Item;
import com.poly.bean.Order;
import com.poly.bean.OrderDetail;
import com.poly.bean.Product;
import com.poly.dao.AccountDAO;
import com.poly.dao.CategoryDAO;
import com.poly.dao.OrderDAO;
import com.poly.dao.OrderDetailDAO;
import com.poly.dao.ProductDAO;
import com.poly.service.CookieService;
import com.poly.service.SessionService;
import com.poly.service.ShoppingCartService;
import com.poly.service.ShoppingCartServiceImpl;

@Controller
public class DetailController {
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
	CookieService cookieService;

	@Autowired
	SessionService sessionService;

	@Autowired
	ShoppingCartService spService;

	@Autowired
	ShoppingCartServiceImpl spServiceImpl;

	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer idItem) {
		model.addAttribute("listCategories", cDao.findAll());
		model.addAttribute("LinkPage", "detail.jsp");
		model.addAttribute("display", "no");

		Product product = pDao.getOne(idItem);

		Category category = cDao.getOne(product.getCategory().getId());
		List<Product> listProdSameKind = pDao.findByProdSameKind(category.getId(), idItem);
		model.addAttribute("listDatail", listProdSameKind);
		model.addAttribute("p", product);

		System.out.println(spService.getCount());
		model.addAttribute("sumQty", spService.getCount());// đếm tổng số lượng sp đã mua
		return "index";
	}

	@RequestMapping(value = "/detailcart", method = RequestMethod.GET)
	public String addCart(Model model, @RequestParam("qt") int quantity, @RequestParam("id") int idI) {
		model.addAttribute("listCategories", cDao.findAll());
		model.addAttribute("LinkPage", "detail.jsp");
		model.addAttribute("display", "no");
		Product product = pDao.getOne(idI);

		System.out.println(product.getName() + " " + product.getPrice() + " addCart");
		Category category = cDao.getOne(product.getCategory().getId());
		List<Product> listProdSameKind = pDao.findByProdSameKind(category.getId(), idI);
		model.addAttribute("listDatail", listProdSameKind);
		model.addAttribute("p", product);

		// lưu vào cooki
		loadProdCooki(product, quantity);
		model.addAttribute("sumQty", spService.getCount());// đếm tổng số lượng sp đã mua
		return "index";
	}

	//StringBuilder prodCooki = new StringBuilder();

	public void loadProdCooki(Product product, int qty) {// chuẩn bị dữ liệu để add vào cookie
		Cookie cookie = cookieService.get("proT");
		if (cookie == null) {
			cookieService.add("proT", "", 720);
		}

		Item item = new Item();
		item.setId(product.getId());
		item.setName(product.getName());
		item.setPrice(product.getPrice());
		item.setImg(product.getImage());
		item.setQty(qty);
		System.out.println(item.getName() + " " + item.getPrice() + " loadProdCooki");
		spService.add(product.getId(), item);

		Account user = sessionService.get("user");
		if (user != null) {
			//
			System.out.println("user: " + user);
			createOrderAndOrderDetail(user, product, qty);
		}
		

	}

	public void createOrderAndOrderDetail(Account user, Product product, int qty) {
		// khi đã đăng nhập
		Order order = oDao.checkPaid(user.getUsername(), false);////////////////// đang fix cứng
		if (order == null) {
			Account account = aDao.getOne(user.getUsername());
			Order createOrder = new Order();
			createOrder.setAccount(account);
			createOrder.setAddress("Trung Mĩ Tây");
			createOrder.setCreateDate(new Date());
			createOrder.setPaid(false);
			oDao.save(createOrder);
			System.out.println("tạo hóa đơn");
		
			List<OrderDetail> listOrderDetail = odDao.findOrderDetailById(order.getId());
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setPrice(product.getPrice());
			orderDetail.setQuantity(qty);
			orderDetail.setProduct(product);
			orderDetail.setOrder(order);
			if (listOrderDetail.size() == 0) {
				System.out.println("thêm OrderDetail");
				odDao.save(orderDetail);
			} else {
				spServiceImpl.MapDBFromCookie(order, product, qty);
			}
		}else {
			List<OrderDetail> listOrderDetail = odDao.findOrderDetailById(order.getId());
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setPrice(product.getPrice());
			orderDetail.setQuantity(qty);
			orderDetail.setProduct(product);
			orderDetail.setOrder(order);
			if (listOrderDetail.size() == 0) {
				System.out.println("thêm OrderDetail");
				odDao.save(orderDetail);
			} else {
				spServiceImpl.MapDBFromCookie(order, product, qty);
			}
		}
	}

}
