package com.example.DAO.impl;

import java.io.Serializable;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.DAO.TechnologyDAO;
import com.example.Entity.Project;
import com.example.Entity.TechnologyInfo;
import com.mysql.jdbc.PreparedStatement;

@Repository(value="TechnologyDAO")
@Transactional(rollbackFor = Exception.class)
public class TechnologyDAOIpml implements TechnologyDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(TechnologyInfo project) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(project);
	//	sessionFactory.close();
	}

	@Override
	public void update(TechnologyInfo project) {
		Session session = sessionFactory.getCurrentSession();
		session.update(project);
	//	session.close();
	}
	
	@Override
	public List<TechnologyInfo> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("FROM TechnologyInfo", TechnologyInfo.class).getResultList();
	}
	
	@Override
	public List<TechnologyInfo> findTechnology(String id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query<TechnologyInfo> query = session.createQuery(" FROM TechnologyInfo t WHERE t.techId= :techId").setParameter("techId", id);
		List<TechnologyInfo> result = query.list();
		return result;
	}

}
