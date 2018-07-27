package com.example.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.DAO.ProjectDetailDao;
import com.example.Entity.Project;
import com.example.Entity.ProjectDetailRequest;
import com.example.Entity.ProjectDetailResponse;
import com.example.Entity.User;
//import com.example.Entity.User;
import com.example.Entity.UserDTOProjectDetail;
import com.example.Model.UserJoinMultiProjectDTO;
import com.example.Service.ProjectDetailService;


@CrossOrigin
@RestController
public class ProjectDetailController {
//	 
	@Autowired
	private ProjectDetailDao projectdao;
//	
	@Autowired
	private ProjectDetailService projectdetailservice;
	
	public ProjectDetailController() {
	}
	
	@PostMapping("/project-detail")
	public ProjectDetailResponse getDetail(@RequestBody ProjectDetailRequest projectdetail){
		List<Project> project = projectdao.getProjectDetail(projectdetail.getProject_id());
		List<UserDTOProjectDetail> listuser = projectdao.findByProjectID(projectdetail.getProject_id());
		if(listuser==null) return new ProjectDetailResponse(project.get(0),null,0);
		else return new ProjectDetailResponse(project.get(0),listuser,listuser.size());
	}
	@GetMapping("/get-project-pages")
	public String getProjectPage() {
		return projectdetailservice.getProjectNumber();
	}
	@GetMapping("/get-user-pages")
	public String getUserPage() {
		return projectdetailservice.getUserNumber();
	}
	@GetMapping("/get-log-time-sheet-pages")
	public String getLOgTimeSheetPage() {
		return projectdetailservice.getLogTimeSheetNumber();
	}
	@GetMapping("/get-pm")
	public List<UserDTOProjectDetail> findPM(){
		return projectdetailservice.findPM();
	}
	@PostMapping("/count-project-hours")
	public String getNumberOfHourInProject(@RequestBody Map<String,Integer> project_id) {
		return projectdetailservice.getNumberOfHourInProject(project_id.get("project_id"));
	}
	@GetMapping("/user-multi-project")
	public List<UserJoinMultiProjectDTO> getmultiuser(){
		return projectdao.getmultiuser();
	}
	@PostMapping("/project-detail-user-list")
	public ProjectDetailResponse getProjectDetailUserList(@RequestBody Map<String,Integer> map){
		List<UserDTOProjectDetail> listuser = projectdao.findByProjectID(map.get("project_id"));
		if(listuser.isEmpty()) {
			return new ProjectDetailResponse(null,null,0);
		}
		else {
			int size = listuser.size();
			if(size < 10||size == 10) {
				return new ProjectDetailResponse(null,listuser,size);
			}
			else {
				return new ProjectDetailResponse(null,listuser.subList((map.get("index_of_page")-1)*10, map.get("index_of_page")*10),size);
			}
		}
	}
}
