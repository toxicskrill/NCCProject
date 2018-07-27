package com.example.DAO;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Entity.Relation;
import com.example.Entity.RelationPK;

@Service
public interface relationDao {
	

	public void saveRelation(final RelationPK relation);
	public List<Relation> findall();
	public List<Integer> findmultiprojectuser();
	public List<Relation> findByUserId(int user_id);
	public List<Relation> findByProjectId(int project_id);
}
