package com.poly.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.bean.Order;
import com.poly.bean.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long> {
	
	@Query("SELECT od FROM OrderDetail od WHERE od.order.id = ?1 ")
	List<OrderDetail> findOrderDetailById(Long id);
	
	
	@Query("SELECT od.product.id FROM OrderDetail od WHERE od.order.id = ?1 ")
	Set<Integer> findOrderDetailByProdId(Long id);
	
	@Query(value = "update OrderDetails set Quantity = ?1 where ProductId = ?2 and OrderId = ?3", nativeQuery = true)
	void updateOrderDetail (int qty, int prodId, long oderId);
	
	@Query(value = "delete from OrderDetails where ProductId = ?1 and OrderId = ?2", nativeQuery = true)
	void deleteOrderDetail (int prodId, long oderId);
	
	@Query("SELECT od.product.id, od.quantity FROM OrderDetail od WHERE od.order.id = ?1 ")
	List<Object[]> getIdProdAndQty(Long id);
	
	@Query("SELECT od FROM OrderDetail od WHERE od.order.id = ?1")
	List<OrderDetail> listOrderDetailByOrderId(Long id);
}
