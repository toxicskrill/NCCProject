package com.example.DAO.impl;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.DAO.LogTimeSheetDao;
import com.example.DAO.ProjectDao;
import com.example.Entity.LogTimeSheet;
import com.example.Entity.Project;
import com.example.Entity.User;
import com.example.Model.LogTimeSheetUserWithProjectNameDTO;
import com.example.Model.LogTimeSheetUsername;
import com.example.Model.ProjectLogTimeSheetDTO;
import com.example.Model.UserDTO;
import com.example.Model.UserLogTimeSheetProjectWithoutIdDTO;

@Repository(value="LogTimeSheetDao")
@Transactional(rollbackFor = Exception.class)
public class LogTimeSheetDaoImpl implements LogTimeSheetDao{

	public static final String RESULT_OK ="success";
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	ProjectDaoImpl pDao;
	@Autowired
	UserDaoImpl uDao;
	
	public Session getSession() {
		if(sessionFactory != null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<LogTimeSheet> findAll() {
		Session a = getSession();
		List<LogTimeSheet> result = a.createQuery("FROM log_time_sheet").getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LogTimeSheetUserWithProjectNameDTO> findByUserId(int user_id) {
		@SuppressWarnings( "unchecked")
		Session a = getSession();
		List<LogTimeSheetUserWithProjectNameDTO> listResult = a
				.createNativeQuery("CALL findLogTimeSheetByUserId(?1)")
				.setParameter(1, user_id).setResultTransformer(new AliasToBeanResultTransformer(LogTimeSheetUserWithProjectNameDTO.class)).getResultList();
		return listResult;
	}
	@Override
	public boolean create(LogTimeSheet logTimeSheet) throws Exception{
		try {
			Session a = getSession();
			a.save(logTimeSheet);

			return true;
		}catch (HibernateException e) {
			throw new Exception("Saving the log time sheet failed, a cay", e);
		}
		
	}
	@Override
	public LogTimeSheet findById(int id) {
		Session a = getSession();
		
		LogTimeSheet b =  (LogTimeSheet) a
				.createNativeQuery("SELECT id, project_id, role, type, hours, user_id FROM log_time_sheet   WHERE id = ?1")
				.setParameter(1, id).addEntity(LogTimeSheet.class).getSingleResult();

		return b;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<LogTimeSheet> findByUserIdAndProjectId(int userId, int projectId) {
		
		Session a = getSession();
		List<LogTimeSheet> listResult=   a
				.createNativeQuery("SELECT id, project_id, role, type, hours, user_id FROM log_time_sheet   WHERE user_id =?1 AND project_id = ?2")
				.setParameter(1, userId).setParameter(2, projectId).addEntity(LogTimeSheet.class).getResultList();
		if(listResult.isEmpty()) {
			
			return null;
		}

		return listResult;
	}
	@Override
	public String updateLogTimeSheet(LogTimeSheet logTimeSheet) {
			Session a=getSession();
			a.update("log_time_sheet",logTimeSheet);
			
			return RESULT_OK;
		
	}
	@Override
	public String deleteLogTimeSheet(int id) {
		Session a = getSession();
		 a.delete(findById(id));
		
		 return RESULT_OK;
	}
	

	@Override
	
	public ProjectLogTimeSheetDTO findLogTimeSheetByProjectIdWithListUser(int project_id) {
		
		List<LogTimeSheet> listLogTime=  findAll();
		List<Project> projects = pDao.findAll();
		List<User> users = uDao.findAll();
		Project projectResult = new Project();
		for(int i=0;i<projects.size();i++) {
			if(projects.get(i).getproject_id()==project_id) {
				projectResult = projects.get(i);
			}
		}
		
		List<LogTimeSheetUsername> listLogTimeTemp = new ArrayList<>();
		for(int j=0;j<listLogTime.size();j++) {
			if(listLogTime.get(j).getProject_id() == project_id ) {
				String username = "";
				for(User user : users) {
			        if(user.getId() ==  listLogTime.get(j).getUser_id()) {
			        	username = user.getUsername();
			        }
			    }
				//int id, int project_id, String role, String type, int hours, int user_id,String username
				listLogTimeTemp.add(new LogTimeSheetUsername(listLogTime.get(j).getId(),
						listLogTime.get(j).getProject_id(),
						listLogTime.get(j).getRole(),
						listLogTime.get(j).getType(),
						listLogTime.get(j).getHours(),
						listLogTime.get(j).getUser_id(),username
						,listLogTime.get(j).getDate(),listLogTime.get(j).getDescription()));
			}
		}
		ProjectLogTimeSheetDTO result = new ProjectLogTimeSheetDTO(project_id,projectResult.getProjectName(),listLogTimeTemp);
		return result;
	}

	@Override
	public List<UserLogTimeSheetProjectWithoutIdDTO> findDataPaging(Integer from, Integer offset) {
		Session a = getSession();
		@SuppressWarnings( "unchecked")
		List<UserLogTimeSheetProjectWithoutIdDTO> resultList = a.createNativeQuery("CALL getDataPagingLogTimeSheet(?1, ?2)")
											.setParameter(1, from*offset - offset)
											.setParameter(2, offset).setResultTransformer(new AliasToBeanResultTransformer(UserLogTimeSheetProjectWithoutIdDTO.class))
											.getResultList();
	
		return resultList;
	}

	@Override
	public BigInteger getCountLogTimeSheet() {
		Session a = getSession();
		BigInteger count = (BigInteger) a.createNativeQuery("SELECT COUNT(id) FROM log_time_sheet").getSingleResult();
		
		return count;
	}

	@Override
	public BigInteger test(String regx) {
		String text = "%" + regx + "%";
		System.out.println("MMMM: "+text);
		Session a = getSession();
		BigInteger kq = (BigInteger) a.createNativeQuery("SELECT count(id) FROM ncc.log_time_sheet where (:field) like ?2")
				.setParameter("field", "role")	
				.setParameter(2, text)
					.getSingleResult();
		
		return kq;
	}
	
	@Override
	public List<UserLogTimeSheetProjectWithoutIdDTO> findDataPagingFilter(String field,String value,int index_of_page,int pageSize) {
		Session a = getSession();
		@SuppressWarnings( "unchecked")
		List<UserLogTimeSheetProjectWithoutIdDTO> resultList = a.createNativeQuery("CALL filter(?1, ?2 ,?3,?4)")
											.setParameter(1, field)
											.setParameter(2, value)
											.setParameter(3, index_of_page)
											.setParameter(4, pageSize)
											.setResultTransformer(new AliasToBeanResultTransformer(UserLogTimeSheetProjectWithoutIdDTO.class))
											.getResultList();
	
		return resultList;
	}
	
	@Override
	public BigInteger countDataPagingFilter(String field,String value) {
		Session a = getSession();
		BigInteger countResultList = (BigInteger)a.createNativeQuery("CALL filter_count_all(?1, ?2 )")
											.setParameter(1, field)
											.setParameter(2, value).getSingleResult();
	
		return countResultList;
	}

	@Override
	public BigDecimal countHoursLogtimesheet() {
		Session a = getSession();
		BigDecimal count = (BigDecimal) a.createNativeQuery("SELECT sum(hours) FROM log_time_sheet").getSingleResult();
		
		return count;
	}
}
