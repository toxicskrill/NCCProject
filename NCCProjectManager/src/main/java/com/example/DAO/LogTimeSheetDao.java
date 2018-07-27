package com.example.DAO;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.hibernate.HibernateException;

import com.example.Entity.LogTimeSheet;
import com.example.Model.LogTimeSheetUserWithProjectNameDTO;
import com.example.Model.ProjectLogTimeSheetDTO;
import com.example.Model.UserLogTimeSheetProjectWithoutIdDTO;


public interface LogTimeSheetDao{

	public List<LogTimeSheet> findAll();
	public ProjectLogTimeSheetDTO findLogTimeSheetByProjectIdWithListUser(int project_id);
	@SuppressWarnings("unchecked")
	public List<LogTimeSheetUserWithProjectNameDTO> findByUserId(int user_id) ;

	public boolean create(LogTimeSheet logTimeSheet) throws Exception;

	public LogTimeSheet findById(int id) ;

	public List<LogTimeSheet> findByUserIdAndProjectId(int userId, int projectId) ;

	public String updateLogTimeSheet(LogTimeSheet logTimeSheet) ;

	public String deleteLogTimeSheet(int id) ;
	public List<UserLogTimeSheetProjectWithoutIdDTO> findDataPaging(Integer from, Integer offset);
	public BigInteger getCountLogTimeSheet();
	public BigInteger test(String regx);

	public List<UserLogTimeSheetProjectWithoutIdDTO> findDataPagingFilter(String field,String value,int index_of_page,int pageSize);
	public BigInteger countDataPagingFilter(String field,String value);
	
	public BigDecimal countHoursLogtimesheet();

}
