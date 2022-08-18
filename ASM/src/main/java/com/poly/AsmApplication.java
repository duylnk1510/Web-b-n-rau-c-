package com.poly;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.poly.bean.Category;
import com.poly.bean.Order;
import com.poly.bean.OrderDetail;
import com.poly.dao.CategoryDAO;
import com.poly.dao.OrderDAO;
import com.poly.dao.OrderDetailDAO;

@SpringBootApplication
public class AsmApplication {

	@Autowired
	CategoryDAO cDao;
	
	@Autowired
	OrderDAO oDao;
	
	@Autowired
	OrderDetailDAO odDao;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(AsmApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		
////		String sqlSelect = "Select * from Orders";
////		//select(Order.class, sqlSelect);
////		
////		List<Object[]> list = odDao.getIdProdAndQty(6L);
////		
////		for (Object[]  arrObj: list) {
////			Object[] obj = arrObj;
////			for (int i = 0; i < obj.length; i+=2) {
////				int idP = Integer.parseInt(String.valueOf(obj[i]));
////				int qty = Integer.parseInt(String.valueOf(obj[i+1]));
////				System.out.println("id Product: "+idP + " qty: " + qty);
////			}
////		
////		}
//	}
	
	public <T> void select (Class<T> T, String sql  ) {
		String sqlSelect = sql;
		List<T> list = jdbcTemplate.query(sqlSelect,
											BeanPropertyRowMapper.newInstance(T));
		for (T type : list) {
			System.out.println(type.toString());
		}
		
	}
	
	

}
