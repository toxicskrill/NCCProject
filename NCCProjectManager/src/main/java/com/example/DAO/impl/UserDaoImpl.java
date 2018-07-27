package com.example.DAO.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.DAO.*;
import com.example.Entity.*;
import com.example.Model.UserFullInfoDTO;
import com.example.Model.UserLogTimeSheetProjectWithoutIdDTO;


@Repository
@Transactional(rollbackFor = Exception.class)
public class UserDaoImpl implements UserDao{
	@Autowired
	private SessionFactory sessionFactory;

	public void save(final User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(user);
//		session.close();
	}
	public void update(final User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(user);
//		session.close();
	}
	public User findById(final int id) {
		Session session = this.sessionFactory.getCurrentSession();
//		session.close();
		return session.get(User.class, id);
		
	}
	public void delete(final User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.remove(user);
//		session.close();
	}
	public List<User> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
//		session.close();
		return session.createQuery("FROM User", User.class).getResultList();
	}

	@Override
	public List<User> searhUser(String field, String name, String intValue) {
		int value = Integer.parseInt(intValue);
		Session session = this.sessionFactory.getCurrentSession();
		String qry = "select * from user where "+ field +" like '%"+ name +"%' limit "+(value*10-10)+","+(value*10);
		SQLQuery query = session.createSQLQuery(qry).addEntity(User.class);
		List<User> list = query.list();
//		session.close();
		return list;
	}
	
	public List<UserFullInfoDTO> getUserDataPaging(int from, int offset) {
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<UserFullInfoDTO> resultList = session.createNativeQuery("CALL getUserDataPaging( ?1, ?2)")
											.setResultTransformer(new AliasToBeanResultTransformer(UserFullInfoDTO.class))
											.setParameter(1, from*offset - offset)
											.setParameter(2, offset)
											.getResultList();
//		session.close();						
		return resultList;
	}
	public BigInteger getCountUser() {
		Session session = this.sessionFactory.getCurrentSession();
//		session.close();
		return (BigInteger) session.createNativeQuery("SELECT COUNT(id) FROM user").getSingleResult();
	}
	/*@Override
	public Set<Project> getProjectOfUser(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(User.class, id).getProjects();
	}*/
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<UserFullInfoDTO> filterUser(String field, String value, int index_of_page, int pageSize) {
		Session a=sessionFactory.getCurrentSession();
		List<UserFullInfoDTO> b = a.createNativeQuery("CALL filter_user(?1, ?2 ,?3,?4)")
				.setParameter(1, field)
				.setParameter(2, value)
				.setParameter(3, index_of_page)
				.setParameter(4, pageSize)
				.setResultTransformer(new AliasToBeanResultTransformer(UserFullInfoDTO.class))
				.getResultList();
//		a.close();
		return b;
	}
	@Override
	public BigInteger countFilterUser(String field, String value) {
		Session a=sessionFactory.getCurrentSession();
		BigInteger b= (BigInteger)sessionFactory.getCurrentSession().createNativeQuery("CALL user_filter_count_all(?1, ?2 )")
				.setParameter(1, field)
				.setParameter(2, value)
				.getSingleResult();
//		a.close();
		return b;
	}
}