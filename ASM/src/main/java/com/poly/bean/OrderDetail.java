package com.poly.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Orderdetails")
public class OrderDetail implements Serializable{
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	Double price;
	Integer quantity;
	
	@ManyToOne @JoinColumn(name = "Productid")
	Product product;
	
	@ManyToOne @JoinColumn(name = "Orderid")
	Order order;

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", price=" + price + ", quantity=" + quantity + "]";
	}
	
	
}
