package com.example.Entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the relation database table.
 * 
 */
@Entity
@Table(name="relation")
public class Relation implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RelationPK id;

	public Relation() {
		id = new RelationPK();
	}

	public RelationPK getId() {
		return this.id;
	}

	public void setId(RelationPK id) {
		this.id = id;
	}

	public int getuser_id() {
		// TODO Auto-generated method stub
		return id.getUserId();
	}

	public int getProject_id() {
		
		return id.getProjectId();
	}
	

}