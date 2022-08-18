package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.bean.Order;

public interface OrderDAO extends JpaRepository<Order, Long> {
	
	@Query("SELECT o FROM Order o WHERE o.account.username = ?1 AND o.paid = ?2")
	Order checkPaid(String username, boolean paid);
	 
	@Query(value = "SELECT o FROM Order o WHERE o.account.fullname LIKE ?1", nativeQuery = false)
	List<Order> findOrderByName(String username);

}
