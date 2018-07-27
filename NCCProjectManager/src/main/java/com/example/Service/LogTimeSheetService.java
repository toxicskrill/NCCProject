package com.example.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import com.example.Entity.LogTimeSheet;
import com.example.Model.ProjectLogTimeSheetDTO;
import com.example.Model.UserLogTimeSheetProjectWithoutIdDTO;
import com.example.Model.LogTimeSheetDTO;
import com.example.Model.LogTimeSheetUserWithProjectNameDTO;

public interface LogTimeSheetService {

	public List<LogTimeSheet> findAll();
	public List<LogTimeSheetUserWithProjectNameDTO> findLogTimeSheetByUserId(int user_id);
	public List<LogTimeSheet> findLogTimeSheetByUserIdAndProjectId(int userId, int projectId);
	public LogTimeSheet findLogTimeSheetById(int id);
	public LogTimeSheetDTO createLogTimeSheet(LogTimeSheet logTimeSheet) throws Exception;
	public String updatelogTimeSheet(LogTimeSheet logTimeSheet);
	public String deleteLogTimeSheet(int id);
	public ProjectLogTimeSheetDTO findLogTimeSheetByProjectIdWithListUser(int project_id);
	public List<UserLogTimeSheetProjectWithoutIdDTO> findDataPaging(Integer from, Integer offset);
	public BigInteger getCountLogTimeSheet();
	public BigInteger test(String regx);
	public List<UserLogTimeSheetProjectWithoutIdDTO> findDataPagingFilter(String field,String value,int index_of_page,int pageSize);
	public BigInteger countDataPagingFilter(String field,String value);
	public BigDecimal countHoursLogtimesheet();
}
