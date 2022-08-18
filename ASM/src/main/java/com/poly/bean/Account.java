package com.poly.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Accounts")
public class Account  implements Serializable{
	@Id
	private String username;
	
	private String password;
	private String fullname;
	private String email;
	private String photo;
	
	private Boolean activated;
	private Boolean admin;
	
	@OneToMany(mappedBy = "account")
	List<Order> orders;

	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", fullname=" + fullname + ", email="
				+ email + ", photo=" + photo + ", activated=" + activated + ", admin=" + admin + "]";
	}
	
	
}
