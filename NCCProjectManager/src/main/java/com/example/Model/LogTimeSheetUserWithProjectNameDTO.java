package com.example.Model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class LogTimeSheetUserWithProjectNameDTO {
	
	
	private int id;
	
	private int project_id;
	
	private String projectName;
	
	private String role;
	
	private String type;
	
	private int hours;

	private int user_id;
	
	private String username;
	
	private Timestamp date;
	
	private String description;
	
	

	public LogTimeSheetUserWithProjectNameDTO() {
		super();
	}

	public LogTimeSheetUserWithProjectNameDTO(int id, int project_id, String role, String type, int hours, int user_id,
			String projectName, Timestamp date, String description, String username) {
		super();
		this.id = id;
		this.project_id = project_id;
		this.role = role;
		this.type = type;
		this.hours = hours;
		this.user_id = user_id;
		this.projectName = projectName;
		this.date = date;
		this.description = description;
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
