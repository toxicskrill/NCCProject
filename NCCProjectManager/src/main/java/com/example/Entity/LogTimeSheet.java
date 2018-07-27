package com.example.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity(name="log_time_sheet")
@Table(name="log_time_sheet")
@NamedQuery(name="LogTimeSheet.findAll", query="SELECT lo FROM log_time_sheet lo")
public class LogTimeSheet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name ="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int project_id;
	
	private String role;
	
	private String type;
	
	private int hours;

	private int user_id;
	
	private Timestamp date;
	
	private String description;
	
	public LogTimeSheet() {

	}


	
	
	public LogTimeSheet(int id, int project_id, String role, String type, int hours, int user_id, Timestamp date, String description) {
		this.id = id;
		this.project_id = project_id;
		this.role = role;
		this.type = type;
		this.hours = hours;
		this.user_id = user_id;
		this.date = date;
		this.description = description;
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




	@Override
	public String toString() {
		return "project_id:"+getProject_id()+",role:"+getRole()+",type:"+getType()+",hours:"+getHours()+"user_id"+getUser_id();
	}
	
}
