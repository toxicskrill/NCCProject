package com.example.Controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.LogTimeSheet;
import com.example.Model.ProjectLogTimeSheetDTO;
import com.example.Model.UserLogTimeSheetProjectWithoutIdDTO;
import com.example.Model.LogTimeSheetDTO;
import com.example.Model.LogTimeSheetUserWithProjectNameDTO;
import com.example.Service.LogTimeSheetService;
import com.example.Service.impl.LogTimeSheetServiceImpl;
@CrossOrigin
@RestController
@RequestMapping("/log-time-sheet")
public class LogTimeSheetController {
	
	/*@GetMapping("/test/{regx}")
	public BigInteger test(@PathVariable String regx) {
		return  logTimeSheetService.test(regx);
	}*/
	
	@Autowired
	private LogTimeSheetService logTimeSheetService;
	
	@GetMapping("/get-count-log")
	public BigInteger getCountLogTimeSheet() {
		return logTimeSheetService.getCountLogTimeSheet();
	}
	
	@PostMapping("/get-data-paging")
	public List<UserLogTimeSheetProjectWithoutIdDTO> getDataPaging(@RequestBody Map<String, Integer> map){
		return logTimeSheetService.findDataPaging(map.get("from"), map.get("offset"));
	}
	
	@GetMapping("/all")
	public List<LogTimeSheet> getAllLogTimeSheetOfAllUser() {
		return logTimeSheetService.findAll();
	}
	
	@CrossOrigin
	@PostMapping("/user")
	public List<LogTimeSheetUserWithProjectNameDTO> getAllLogTimeSheetOfOneUser(@RequestBody Map<String, Integer> map){
		return logTimeSheetService.findLogTimeSheetByUserId(map.get("user_id"));
	}
	
	@PostMapping("/create")
	public LogTimeSheetDTO createLogTimeSheet(@RequestBody LogTimeSheet logTimeSheet) throws Exception {
		return logTimeSheetService.createLogTimeSheet(logTimeSheet);
	}
	
	@PutMapping("/update")
	public String updateLogTimeSheet(@RequestBody LogTimeSheet logTimeSheet) {
		return logTimeSheetService.updatelogTimeSheet(logTimeSheet);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteLogTimeSheetByLogId(@PathVariable int id) {
		return logTimeSheetService.deleteLogTimeSheet(id);
	}
	
	@GetMapping("/user/{user_id}/project/{project_id}")
	public List<LogTimeSheet> getLogTimeSheetOfOneProjectOfOneUser(@PathVariable int user_id, @PathVariable int project_id){
		return logTimeSheetService.findLogTimeSheetByUserIdAndProjectId(user_id, project_id);
	}
	
	@PostMapping("/user/project")
	public ProjectLogTimeSheetDTO getLogTimeSheetByProjectIdWithListUser( @RequestBody Map<String, Integer> map){
		return logTimeSheetService.findLogTimeSheetByProjectIdWithListUser(map.get("project_id"));
	}
	
	@PostMapping("/log-time-sheet-filter")
	public List<UserLogTimeSheetProjectWithoutIdDTO> getFullLogTimeFilter( @RequestBody Map<String, String> map){
		return logTimeSheetService.findDataPagingFilter(map.get("field"),map.get("value"),Integer.parseInt(map.get("index_of_page")),Integer.parseInt(map.get("pageSize")));
	}
	
	@PostMapping("/get-count-filter-log")
	public BigInteger countDataPagingFilter(@RequestBody Map<String, String> map){
		return logTimeSheetService.countDataPagingFilter(map.get("field"),map.get("value"));
	}
	
	@GetMapping("/get-sum-hours")
	public BigDecimal countDataPagingFilter(){
		return logTimeSheetService.countHoursLogtimesheet();
	}
}
