package com.example.DAO.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.DAO.relationDao;
import com.example.Entity.Relation;
import com.example.Entity.RelationPK;


@Repository
@Transactional(rollbackFor = Exception.class)
public class relationDaoImpl implements relationDao {
	@Autowired
	private SessionFactory sessionFactory;
	
//	@PersistenceContext
//    private EntityManager em;
	@Override
	public void saveRelation(final RelationPK relation) {
		// TODO Auto-generated method stub
		Relation a = new Relation();
		a.setId(relation);
		Session session = this.sessionFactory.getCurrentSession();
		session.save(a);
//		session.close();
	}
	@Override
	public List<Relation> findall(){
		EntityManager em = sessionFactory.createEntityManager();
		@SuppressWarnings("unchecked")
		Query<Relation> query=(Query<Relation>) em.createQuery("select r from Relation r");
		return query.list();
	}
	@Override
	public List<Integer> findmultiprojectuser(){
		EntityManager em = sessionFactory.createEntityManager();
		@SuppressWarnings("unchecked")
		Query<Integer> query=(Query<Integer>) em.createQuery("select r.id.userId from Relation r group by r.id.userId  ");
		return query.getResultList();
	}
	@SuppressWarnings( "unchecked" )
	@Override
	public List<Relation> findByUserId(int user_id) {
		EntityManager em = sessionFactory.createEntityManager();
		Query<Relation> query = (Query<Relation>) em.createQuery("select r from Relation r where r.id.userId = :user_id").setParameter("user_id", user_id);
		List<Relation> result = query.list();
		return result;
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Relation> findByProjectId(int project_id) {
		// TODO Auto-generated method stub
		EntityManager em = sessionFactory.createEntityManager();
		Query<Relation> query = (Query<Relation>) em.createQuery("select r from RelationPK r where r.id.projectId = :project_id").setParameter("projec_id", project_id);
		List<Relation> result = query.list();
		return result;
	}
	


	public void removeUser(int project_id, int getuser_id) {
		Session session = this.sessionFactory.getCurrentSession();
		String sql = "DELETE Relation where user_id = :getuser_id AND project_id = :project_id";
		Query query = session.createQuery(sql);
		query.setParameter("getuser_id", getuser_id);
		query.setParameter("project_id", project_id);
		query.executeUpdate();
//		session.close();
	}
}
