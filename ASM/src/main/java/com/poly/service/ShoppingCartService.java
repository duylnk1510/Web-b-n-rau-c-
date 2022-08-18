package com.poly.service;

import java.util.Collection;

import com.poly.bean.Item;

public interface ShoppingCartService  {
	Item add(Integer id, Item item);
	
	void remove(Integer id);
	
	Item update(Integer id, int qty);
	
	void clear();

	Collection<Item> getItems();
	
	int getCount();
	
	double getAmount();


}
