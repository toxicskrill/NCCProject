package com.example.Model;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserLogTimeSheetProjectWithoutIdDTO implements Serializable{
	
	private int id;
	private String projectName;
	private String username;
	private String role;
	private String type;
	private int hours;
	private String description;
	private Timestamp date;
	
	
	
	
	public UserLogTimeSheetProjectWithoutIdDTO() {
	}
	public UserLogTimeSheetProjectWithoutIdDTO(int id, String projectName, String username, String role, String type,
			int hours, String description, Timestamp date) {
		super();
		this.id = id;
		this.projectName = projectName;
		this.username = username;
		this.role = role;
		this.type = type;
		this.hours = hours;
		this.description = description;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	

}
