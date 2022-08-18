package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.poly.bean.Category;
import com.poly.bean.Product;

public interface CategoryDAO  extends JpaRepository<Category, String>{
		
	
}
