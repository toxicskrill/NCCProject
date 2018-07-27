package com.example.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Entity.TechnologyInfo;

@Service
public interface TechnologyService {
	public void save(final TechnologyInfo project);

	public void update(final TechnologyInfo project);
	
	public List<TechnologyInfo> findAll();
	
	public List<TechnologyInfo> findTechnology(String id);

}