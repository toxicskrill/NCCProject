package com.example.DAO;

import java.util.List;




import com.example.Entity.Project;
import com.example.Entity.User;
//import com.example.Entity.User;
import com.example.Entity.UserDTOProjectDetail;
import com.example.Model.UserJoinMultiProjectDTO;


public interface ProjectDetailDao  {
	public List<Project> getProjectDetail(int project_id);
	public List<UserDTOProjectDetail> findByProjectID(int project_id);
	public List<UserDTOProjectDetail> findPM(); 
	public int getNumberOfHourInProject(int project_id);
	public List<UserJoinMultiProjectDTO> getmultiuser();
}
