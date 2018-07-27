package com.example.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.Model.findUandP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Entity.Relation;
import com.example.Model.RelationDTO;
import com.example.Service.impl.ProjectServiceImpl;
import com.example.Service.impl.RelationServiceImpl;
import com.example.Service.impl.UserServiceImpl;
@CrossOrigin
@Controller
public class RelationController {
	@Autowired 
	private RelationServiceImpl relationServiceImpl;
	@Autowired
	private ProjectServiceImpl projectService;
	@Autowired
	private UserServiceImpl userService;
	@PostMapping(value= ("/add-user"))
	@ResponseBody
	public String add_user(@RequestBody RelationDTO relationDTO) {
		try {
			if(projectService.findById(relationDTO.getProject_ID())!=null && userService.findById(relationDTO.getUser_ID())!=null) {
				relationServiceImpl.addUser(relationDTO);

				return "{\"status\":\"true\"}";
			}else {
				return "{\"status\":\"false\"}";
			}
		} catch (Exception e) {
			return "{\"status\":\"false\"}";
		}
		
		



	}
	
	@PostMapping("/remove-user")
	@ResponseBody
	public String removeUser(@RequestBody List<RelationDTO> list1)
	{

		List<Relation> list = new ArrayList<>();
		try
		{
			for (RelationDTO relationDTO : list1) {
				Relation r = new Relation();
				r.getId().setUserId(relationDTO.getUser_ID());
				r.getId().setProjectId(relationDTO.getProject_ID());
				list.add(r);
			}
			if(list.size() != 0)
			{
				relationServiceImpl.removeUser(list);
				return "{\"status\":\"true\"}";	
			}else
				return "{\"status\":\"false\"}";
			
		}catch (Exception e) {
			
			return "{\"status\":\"false\"}";
		}
		
	}
	
	
	@PostMapping("/add-userProject")
	@ResponseBody
	public String addUser(@RequestBody List<RelationDTO> list1)
	{
		try
		{
			if(list1.size() != 0)
			{
				relationServiceImpl.addUserProject(list1);
				return "{\"status\":\"true\"}";	
			}else
				return "{\"status\":\"false\"}";
			
		}catch (Exception e) {
			return "{\"status\":\"false\"}";
		}
		
	}


	@PostMapping(value = "/find-user-and-project")
	@ResponseBody
	public findUandP findUserAndProject(@RequestBody HashMap<String,String> map){
		String name = map.get("valueString");
		findUandP find = new findUandP();
		find.setUser( userService.findByString(name));

		find.setProject(projectService.findByString(name));

		return find;
	}

}
