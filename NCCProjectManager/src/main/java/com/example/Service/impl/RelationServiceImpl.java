package com.example.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DAO.impl.relationDaoImpl;
import com.example.Entity.Relation;
import com.example.Entity.RelationPK;
import com.example.Model.RelationDTO;
import com.example.Service.RelationService;
@Service
public class RelationServiceImpl implements RelationService {
	
	@Autowired
	relationDaoImpl relationDaoImpl;
	
	@Override
	public void addUser(RelationDTO relationDTO)  {
		RelationPK r = new RelationPK();
		r.setProjectId(relationDTO.getProject_ID());
		r.setUserId(relationDTO.getUser_ID());
		relationDaoImpl.saveRelation(r);
		
	}
	public void removeUser(List<Relation> list) {
		for(Relation relation: list)
		{
			relationDaoImpl.removeUser(relation.getProject_id(), relation.getuser_id());	
		}
		
	}

	public void addUserProject(List<RelationDTO> list) {
		for(RelationDTO relation: list)
		{
			addUser(relation);
		}	
	}


}
