package com.example.DAO;

import java.util.List;

import com.example.Entity.TechnologyInfo;

public interface TechnologyDAO {
	public void save(final TechnologyInfo project);

	public void update(final TechnologyInfo project);
	
	public List<TechnologyInfo> findAll();
	
	public List<TechnologyInfo> findTechnology(String id);

}
