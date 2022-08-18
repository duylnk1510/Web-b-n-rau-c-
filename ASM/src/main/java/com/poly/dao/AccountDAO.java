package com.poly.dao;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.bean.Account;

public interface AccountDAO extends JpaRepository<Account, String> {
	Optional<Account> findById(String id);
	
	
}
