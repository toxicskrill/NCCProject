package com.example.Model;

import java.util.List;


public class ProjectLogTimeSheetDTO {
	private int project_id;
	private String projectName;
	private List <LogTimeSheetUsername> listLogTime;
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
	public List<LogTimeSheetUsername> getListLogTime() {
		return listLogTime;
	}
	public void setListLogTime(List<LogTimeSheetUsername> listLogTime) {
		this.listLogTime = listLogTime;
	}
	public ProjectLogTimeSheetDTO(int project_id, String projectName, List<LogTimeSheetUsername> listLogTime) {
		this.project_id = project_id;
		this.projectName = projectName;
		this.listLogTime = listLogTime;
	}
	
	
	
}
