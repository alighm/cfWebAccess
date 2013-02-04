package com.vmware.cf.auth.cloudcontroller;

import java.util.Collection;

import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User {
	private static final long serialVersionUID = 1L;
	private String email;
	private String name;

	public CustomUserDetails(String id,String name, String email,Collection authorities) {
		super(name, "unused", true,true,true,true,authorities);
		this.email = email;
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}