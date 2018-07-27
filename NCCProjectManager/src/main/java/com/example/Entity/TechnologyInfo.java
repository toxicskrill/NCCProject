package com.example.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the technology_info database table.
 * 
 */
@Entity
@Table(name="technology_info")
@NamedQuery(name="TechnologyInfo.findAll", query="SELECT t FROM TechnologyInfo t")
public class TechnologyInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tech_id")
	private String techId;

	private String technology;

	public TechnologyInfo() {
	}

	public String getTechId() {
		return this.techId;
	}

	public void setTechId(String techId) {
		this.techId = techId;
	}

	public String getTechnology() {
		return this.technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

}