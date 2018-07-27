package com.example.DAO;

import java.util.List;

import com.example.Entity.Project;

public interface ProjectDao {
	public void save(final Project project);
	
	public void update(final Project project);
	
	public Project findById(final int id);
	
	public void delete(final Project project);
	
	public List<Project> findAll();

	public List<Project> getProjectByIndex(int intValue, String status);

	public List<Project> getProjectByStatus(String status);

	public List<Project> findProjectOfUser(int userId);
	
	public List<Project> searhProjectByName(String field, String name, String intValue);

	
	public int getNumberProjectByName(String field, String name, String intValue);

}
