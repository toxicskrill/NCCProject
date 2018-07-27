package com.example.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class UserDTOProjectDetail {
	@Id
	@Column(name="id")
	private int id;
	@Column(name="username")
	private String username;
	public UserDTOProjectDetail() {
		
	}
	public UserDTOProjectDetail(int id, String username) {
		this.id = id;
		this.username = username;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
