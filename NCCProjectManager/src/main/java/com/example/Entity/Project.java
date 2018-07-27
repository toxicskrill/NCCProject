package com.example.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the project database table.
 * 
 */
@Entity
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
@Table(name="project")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	/*@ManyToMany(mappedBy = "projects")*/

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int project_id;

	private String customer;

	private String description;

	@Column(name = "PM")
	private String pm;
	
	private String projectName;

	private String status;
	@Column(name = "tech_id")
	private int technology;
	
	private String notes;
	

	public Project() {
	}

	public int getproject_id() {
		return this.project_id;
	}

	public void setproject_id(int project_id) {
		this.project_id = project_id;
	}

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPm() {
		return this.pm;
	}

	public void setPm(String pm) {
		this.pm = pm;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getTechnology() {
		return this.technology;
	}

	public void setTechnology(int technology) {
		this.technology = technology;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}