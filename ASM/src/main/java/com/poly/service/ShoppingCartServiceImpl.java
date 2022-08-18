package com.poly.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.jasper.tagplugins.jstl.core.If;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.soap.SoapFaultException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.poly.bean.Account;
import com.poly.bean.Item;
import com.poly.bean.Order;
import com.poly.bean.OrderDetail;
import com.poly.bean.Product;
import com.poly.dao.OrderDAO;
import com.poly.dao.OrderDetailDAO;
import com.poly.dao.ProductDAO;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	CookieService cService;

	@Autowired
	SessionService sessionService;

	@Autowired
	OrderDAO oDao;

	@Autowired
	ProductDAO pDao;

	@Autowired
	OrderDetailDAO odDao;

	static Map<Integer, Item> map = new HashMap<>();

	@Override
	public Item add(Integer id, Item itemP) {

		Item item = map.get(id);
		if (item == null) {
			item = itemP;
			System.out.println(item.getName() + " " + item.getPrice());
			item.setQty(itemP.getQty());
			// System.out.println(item.getImg() + " iiiiiiiiiiiiimmmmmmmmmmmmggggggggg");
			map.put(id, item);
		} else {
			item.setQty(item.getQty() + itemP.getQty());
		}
		saveToCookie();

		return item;
	}

	public void saveToCookie() {// lưu dữ liệu từ map vào cookie
		StringBuilder strBuilder = new StringBuilder();
		if (map.size() == 0) {
			System.out.println("map = 0");
			cService.add("proT", "", 720);
		}
		for (Map.Entry<Integer, Item> entry : map.entrySet()) {
			Integer key = entry.getKey();
			Item val = entry.getValue();
			// System.out.println("Id: " + key + " " + val.toString() + " saveToCookie");

			strBuilder.append(key + "/" + val.getQty() + "/");
			cService.add("proT", strBuilder.toString(), 720);
		}

	}

	public void fillDataToMap() {// đưa dữ liệu từ cookie lên map
		String prodStr = cService.getValue("proT");
	
			if (prodStr != null && prodStr != "") {// khi cookie có data sẽ lưu cắt và đẩy lại lên map
				String[] splitProdStr = prodStr.split("/");
				
					for (int i = 0; i < splitProdStr.length; i += 2) {
						// System.out.println("-" + splitProdStr[i] + splitProdStr[i + 1]);
						Item item = new Item();
						Product prod = pDao.getById(Integer.valueOf(splitProdStr[i]));
						// System.out.println("prod: " + prod.getName() + " " + prod.getPrice());
						item.setName(prod.getName());
						item.setPrice(prod.getPrice());
						item.setId(Integer.valueOf(splitProdStr[i]));
						item.setQty(Integer.valueOf(splitProdStr[i + 1]));
						item.setImg(prod.getImage());
						map.put(item.getId(), item);

						saveDataToDB(prod, item.getQty());
					}
			}
		

		// khi đã đăng nhập và cooki rỗng
		// load dữ liệu sp đó từ order paid = 0 nếu order đó có data
		// ngược lại k làm j
		// (dùng để đẩy dữ liệu từ DB lên khi đã đăng nhập vào)
		Account userAccount = sessionService.get("user");
		if (userAccount != null) {
			Order order = oDao.checkPaid(userAccount.getUsername(), false);
			if (order != null) {
				List<Object[]> list = odDao.getIdProdAndQty(order.getId());
				if (map.size() == 0 && list != null) {
					System.out.println("Đọc data từ DB lên map khi map rỗng");
					for (Object[] arrObj : list) {
						for (int i = 0; i < arrObj.length; i += 2) {
							int idP = Integer.parseInt(String.valueOf(arrObj[i]));
							int qty = Integer.parseInt(String.valueOf(arrObj[i + 1]));
							// System.out.println("id Product: " + idP + " qty: " + qty);
							Product prod = pDao.getOne(idP);
							Item item = new Item();
							item.setId(idP);
							item.setName(prod.getName());
							item.setPrice(prod.getPrice());
							item.setImg(prod.getImage());
							item.setQty(qty);
							map.put(idP, item);
						}
					}
				}

			}

			saveToCookie();
		}

//		for (Map.Entry<Integer, Item> entry : map.entrySet()) {
//			Integer key = entry.getKey();
//			Item val = entry.getValue();
//			System.out.println("Id: " + key + " " + val.toString() + " fillDataToMap");
//		}
	}

	public void saveDataToDB(Product product, int qty) {
		System.out.println("saveDataToDB");
		// lưu data từ cookie khi user đăng xuất và thêm vài sp r đăng nhập lại
		// hàm chỉ chạy 1 lần và trong lúc data được fill lên khi có cooki k rỗng
		Account userAccount = sessionService.get("user");
		System.out.println("userAccount " + userAccount);
		if (userAccount != null) {
			Order order = oDao.checkPaid(userAccount.getUsername(), false);
			if (order != null) {
				MapDBFromCookie(order, product, qty);
			}

		}
	}

	// lưu dữ liệu từ cookie xuống db khi đăng nhập
	public void MapDBFromCookie(Order order, Product prod, Integer qty) {

		Set<Integer> listProdId = odDao.findOrderDetailByProdId(order.getId());

		if (map.values().size() != 0 && listProdId != null) {
			for (Map.Entry<Integer, Item> entry : map.entrySet()) {
				Integer idP = entry.getKey();
				if (listProdId.contains(idP)) {// so id prod trong DB id vs id prod trong map
					System.out.println("update sp trong OrderDetail " + idP);
					// update sp tại id đó vs số lượng
					System.out
							.println("Số lượng " + map.get(idP).getQty() + " idP " + idP + " idOrder" + order.getId());
					try {
						odDao.updateOrderDetail(map.get(idP).getQty(), idP, order.getId());
					} catch (Exception e) {

					}

				} else {

					// chỉ trong trường hợp thêm vào từng sp
					System.out.println("Thêm sp vào OrderDetail " + idP);
					// thêm sp vào OrderDetail
					OrderDetail orderDetail = new OrderDetail();
					Product product = new Product();
					product.setId(idP);
					orderDetail.setOrder(order);
					orderDetail.setProduct(product);
					orderDetail.setPrice(prod.getPrice());
					orderDetail.setQuantity(qty);
					odDao.save(orderDetail);
				}

			}

			for (Integer idP : listProdId) {
				boolean checkKey = false;
				for (Map.Entry<Integer, Item> entry : map.entrySet()) {
					if (entry.getKey() == idP) {
						checkKey = true;
					}
				}
				if (checkKey == false) {
					System.out.println("k có trong db " + idP);
					try {
						odDao.deleteOrderDetail(idP, order.getId());
					} catch (Exception e) {

					}

				}
			}

		}

	}

	@Override
	public void remove(Integer id) {
		map.remove(id);
		saveToCookie();
		Account userAccount = sessionService.get("user");

		if (userAccount != null) {
			try {
				Order order = oDao.checkPaid(userAccount.getUsername(), false);
				System.out.println("remove id " + id + " order Paid " + order.getId());
				odDao.deleteOrderDetail(id, order.getId());
			} catch (Exception e) {
			}
		}

	}

	@Override
	public Item update(Integer id, int qty) {
		map.get(id).setQty(qty);
		saveToCookie();

		Account userAccount = sessionService.get("user");
		if (userAccount != null) {
			Order order = oDao.checkPaid(userAccount.getUsername(), false);
			if (map.values().size() != 0) {

				System.out.println("update sp trong OrderDetail " + id);
				// update sp tại id đó vs số lượng
				// System.out.println("Số lượng " + map.get(id).getQty() + " idP " + id + "
				// idOrder" + order.getId());
				try {
					odDao.updateOrderDetail(map.get(id).getQty(), id, order.getId());
				} catch (Exception e) {

				}
			}
		}

		return null;
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public Collection<Item> getItems() {
		Collection<Item> collection = map.values();
		if (collection.size() != 0) {
			return collection;
		}
		return null;
	}

	@Override
	public int getCount() {
		if (getItems() != null) {
			int sum = 0;
			for (Item item : getItems()) {
				sum += item.getQty();
			}
			return sum;
		}
		return 0;
	}

	@Override
	public double getAmount() {
		if (getItems() != null) {
			double sum = 0.0;
			for (Item item : getItems()) {
				sum += item.getQty() * item.getPrice();
			}
			return sum;
		}
		return 0;
	}

}
