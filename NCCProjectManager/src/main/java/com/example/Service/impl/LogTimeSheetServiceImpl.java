package com.example.Service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.function.LongToIntFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DAO.LogTimeSheetDao;
import com.example.DAO.impl.LogTimeSheetDaoImpl;
import com.example.Entity.LogTimeSheet;
import com.example.Model.ProjectLogTimeSheetDTO;
import com.example.Model.UserLogTimeSheetProjectWithoutIdDTO;
import com.example.Model.LogTimeSheetDTO;
import com.example.Model.LogTimeSheetUserWithProjectNameDTO;
import com.example.Service.LogTimeSheetService;

@Service
public class LogTimeSheetServiceImpl implements LogTimeSheetService{
	
	Boolean status;

	@Autowired
	private LogTimeSheetDao logTimeSheetDao;
	
	@Override
	public List<LogTimeSheet> findAll() {
		
		return logTimeSheetDao.findAll();
	}
	
	@Override
	public List<LogTimeSheetUserWithProjectNameDTO> findLogTimeSheetByUserId(int user_id) {
		return logTimeSheetDao.findByUserId(user_id);
	}
	@Override
	public List<LogTimeSheet> findLogTimeSheetByUserIdAndProjectId(int userId, int projectId) {
		return logTimeSheetDao.findByUserIdAndProjectId(userId, projectId);
	}
	@Override
	public LogTimeSheet findLogTimeSheetById(int id) {
		return logTimeSheetDao.findById(id);
	}
	@Override
	public LogTimeSheetDTO createLogTimeSheet(LogTimeSheet logTimeSheet) throws Exception {
		 return new LogTimeSheetDTO(logTimeSheetDao.create(logTimeSheet), logTimeSheet);
	}
	@Override
	public String updatelogTimeSheet(LogTimeSheet logTimeSheet) {
		return logTimeSheetDao.updateLogTimeSheet(logTimeSheet);
	}
	@Override
	public String deleteLogTimeSheet(int id) {
		return logTimeSheetDao.deleteLogTimeSheet(id);
	}

	@Override
	public ProjectLogTimeSheetDTO findLogTimeSheetByProjectIdWithListUser(int project_id) {
		
		return logTimeSheetDao.findLogTimeSheetByProjectIdWithListUser(project_id);
	}

	@Override
	public List<UserLogTimeSheetProjectWithoutIdDTO> findDataPaging(Integer from, Integer offset) {
		return logTimeSheetDao.findDataPaging(from, offset);
	}

	@Override
	public BigInteger getCountLogTimeSheet() {
		return logTimeSheetDao.getCountLogTimeSheet();
	}

	@Override
	public BigInteger test(String regx) {
		
		return logTimeSheetDao.test(regx);
	}

	@Override
	public List<UserLogTimeSheetProjectWithoutIdDTO> findDataPagingFilter(String field,String value,int index_of_page,int pageSize) {
		return logTimeSheetDao.findDataPagingFilter(field, value, index_of_page,pageSize);
	}

	@Override
	public BigInteger countDataPagingFilter(String field, String value) {
		return logTimeSheetDao.countDataPagingFilter(field, value);
	}

	@Override
	public BigDecimal countHoursLogtimesheet() {
		return logTimeSheetDao.countHoursLogtimesheet();
	}
}
