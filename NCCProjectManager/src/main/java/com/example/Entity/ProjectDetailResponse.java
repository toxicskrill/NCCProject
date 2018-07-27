package com.example.Entity;

import java.util.List;
import com.example.Entity.UserDTOProjectDetail;
public class ProjectDetailResponse {
	private Project project;
	private List<UserDTOProjectDetail> list_user;
	int number;
	public ProjectDetailResponse() {
		
	}
	public ProjectDetailResponse(Project project, List<UserDTOProjectDetail> list_user, int number) {
		super();
		this.project = project;
		this.list_user = list_user;
		this.number = number;
	}



	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}



	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public List<UserDTOProjectDetail> getList_user() {
		return list_user;
	}
	public void setList_user(List<UserDTOProjectDetail> list_user) {
		this.list_user = list_user;
	}
	
	
	
}
