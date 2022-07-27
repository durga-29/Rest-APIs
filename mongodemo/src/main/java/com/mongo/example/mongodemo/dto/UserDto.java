package com.mongo.example.mongodemo.dto;

import com.mongo.example.mongodemo.models.apimodel.User;

public class UserDto {

	private User user;
	private String email;   
	private String address;   
	private String img;
	
	public UserDto() {
		super();
	}

	public UserDto(User user, String email, String address, String img) {
		super();
		this.user = user;
		this.email = email;
		this.address = address;
		this.img = img;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "UserDto [user=" + user + ", email=" + email + ", address=" + address + ", img=" + img + "]";
	}
	
}