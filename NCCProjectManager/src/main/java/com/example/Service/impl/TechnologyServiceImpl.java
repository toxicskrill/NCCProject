package com.example.Service.impl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DAO.TechnologyDAO;
import com.example.DAO.impl.TechnologyDAOIpml;
import com.example.Entity.TechnologyInfo;
import com.example.Service.TechnologyService;

@Service
public class TechnologyServiceImpl implements TechnologyService{
 
	@Autowired
	private TechnologyDAO TechnologyDAOImpl;
	
    @Override
	public void save(TechnologyInfo project) {
		// TODO Auto-generated method stub0
		TechnologyDAOImpl.save(project);
	}

    @Override
	public void update(TechnologyInfo project) {
		// TODO Auto-generated method stub
		TechnologyDAOImpl.update(project);
	}

    @Override
	public List<TechnologyInfo> findAll() {
		// TODO Auto-generated method stub
		return TechnologyDAOImpl.findAll();
	}
	

	@Override
	public List<TechnologyInfo> findTechnology(String id) {
		// TODO Auto-generated method stub
        return TechnologyDAOImpl.findTechnology(id);
        
	}

}
