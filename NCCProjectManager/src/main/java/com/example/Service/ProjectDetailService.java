package com.example.Service;

import java.util.List;

import com.example.Entity.UserDTOProjectDetail;


public interface ProjectDetailService {
	public String getProjectNumber();
	public String getLogTimeSheetNumber();
	public String getUserNumber();
	public List<UserDTOProjectDetail> findPM();
	public String getNumberOfHourInProject(int project_id);
}
