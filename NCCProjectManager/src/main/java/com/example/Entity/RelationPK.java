package com.example.Entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the relation database table.
 * 
 */

@Embeddable
@Table(name="relation")
public class RelationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_id", insertable=false, updatable=false)
	public int userId;

	@Column(name="project_id", insertable=false, updatable=false)
	public int projectId;

	public RelationPK() {
	}
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getProjectId() {
		return this.projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RelationPK)) {
			return false;
		}
		RelationPK castOther = (RelationPK)other;
		return 
			(this.userId == castOther.userId)
			&& (this.projectId == castOther.projectId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.projectId;
		
		return hash;
	}
}