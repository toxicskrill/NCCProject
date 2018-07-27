package com.example.Entity;

import java.io.Serializable;




public class ProjectDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	private String token;
	private int project_id;
	public ProjectDetail() {
		
	}
	public ProjectDetail(String token, int project_id) {
		super();
		this.token = token;
		this.project_id = project_id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	
}
