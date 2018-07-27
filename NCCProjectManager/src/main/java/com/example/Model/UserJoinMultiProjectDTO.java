package com.example.Model;

import com.example.Entity.UserDTOProjectDetail;

public class UserJoinMultiProjectDTO {
	private UserDTOProjectDetail user;
	private int number;
	public UserJoinMultiProjectDTO(UserDTOProjectDetail user, int number) {
		this.user = user;
		this.number = number;
	}
	public UserDTOProjectDetail getUser() {
		return user;
	}
	public void setUser(UserDTOProjectDetail user) {
		this.user = user;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
}
