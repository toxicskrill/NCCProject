package com.example.DAO.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.DAO.ProjectDao;
import com.example.Entity.Project;


@Repository(value="ProjectDao")
@Transactional(rollbackFor = Exception.class)
public class ProjectDaoImpl implements ProjectDao {

	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public void save(Project project) {
		Session session = this.sessionFactory.getCurrentSession();
		project.setStatus("running");
		session.save(project);
	//	sessionFactory.close();
	}

	@Override
	public void update(Project project) {
		Session session = sessionFactory.getCurrentSession();
		session.update(project);
//		session.close();
	}

	@Override
	public Project findById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
//		session.close();
		return session.get(Project.class, id);
		
	}

	@Override
	public void delete(Project project) {
		Session session = this.sessionFactory.getCurrentSession();
//		session.close();
		session.remove(project);

	}

	@Override
	public List<Project> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
//		session.close();
		return session.createQuery("FROM Project", Project.class).getResultList();
	}

	@Override
	public List<Project> findProjectOfUser(int userId) {
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Project> p=  session.createNativeQuery(""
				+ "SELECT project_id, projectName, customer, PM, tech_id, description, status, notes FROM project P WHERE P.project_id IN ("
				+ "SELECT R.project_id FROM relation R WHERE R.user_id = ?1)").setParameter(1, userId).addEntity(Project.class).getResultList();
		if(p.isEmpty()) {
		//	session.close();
			return null;
		}
//		session.close();
		return p;
	}

	public List<Project> getProjectByIndex(int intValue,String status) {
		Session session = this.sessionFactory.getCurrentSession();
		String qry;
		if(status.equals("running"))
			qry = "select * from project where status='"+status+"' limit "+(intValue*10-10)+","+10;
		else
			 qry = "select * from project limit "+(intValue*10-10)+","+10;
		SQLQuery query = session.createSQLQuery(qry).addEntity(Project.class);
		List<Project> list = query.list();
//		session.close();
		return list;
	}

	@Override
	public List<Project> getProjectByStatus( String status) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		String query = "select * from project p where p.status='"+status+"'";
		@SuppressWarnings("unchecked")
		List<Project> result = session.createNativeQuery(query).getResultList();
		return result;
	}
	
	@Override
	public List<Project> searhProjectByName(String field, String name, String intValue) {
		int value = Integer.parseInt(intValue);
		Session session = this.sessionFactory.getCurrentSession();
		String qry = "select * from project where "+ field+" like '%"+ name +"%' limit "+(value*10-10)+","+10;
		SQLQuery query = session.createSQLQuery(qry).addEntity(Project.class);
		List<Project> list = query.list();
		return list;
	}


	@Override
	public int getNumberProjectByName(String field, String name, String intValue) {
		int value = Integer.parseInt(intValue);
		Session session = this.sessionFactory.getCurrentSession();
		String qry = "select * from project where "+ field+" like '%"+ name +"%'";
		SQLQuery query = session.createSQLQuery(qry).addEntity(Project.class);
		List<Project> list = query.list();
		return list.size();
	}


}
