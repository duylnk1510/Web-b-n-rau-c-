package com.poly.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.bean.Product;
import com.poly.bean.Report;

public interface ProductDAO extends JpaRepository<Product, Integer>{

	@Query("SELECT o FROM Product o WHERE o.price BETWEEN ?1 AND ?2")
	List<Product> findByPrice(double minPrice, double maxPrice);
	
	@Query("SELECT o FROM Product o WHERE o.name LIKE ?1")
	Page<Product> findByKeywords(String keywords, Pageable pageable);

	@Query("SELECT new Report(o.category, sum(o.price), count(o)) "
			+ " FROM Product o "
			+ " GROUP BY o.category"
			+ " ORDER BY sum(o.price) DESC")
	List<Report> getInventoryByCategory();
	
	List<Product> findByPriceBetween(double minPrice, double maxPrice);

	Page<Product> findAllByNameLike(String keywords, Pageable pageable);
	
//
	
	List<Product> findByNameContaining(String name);
	
	@Query("SELECT p FROM Product p WHERE p.category.id = ?1 AND p.id <> ?2")
	List<Product> findByProdSameKind(String idC,Integer idP);
	
	@Query(value =  "select OrderId from OrderDetails\r\n"
			+ "group by OrderId\r\n"
			+ "having sum(Quantity) > 5", nativeQuery = true)
	List<Integer> topProduct();
	
}
