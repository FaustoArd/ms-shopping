package com.lord.securityservice.model;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Role implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="authority")
	private String authority;
	
	public Role() {
		
	}
	
	public Role(String authority) {
		super();
		this.authority = authority;
	}
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setAuthority(String authority) {
		 this.authority= authority;
	}

}
