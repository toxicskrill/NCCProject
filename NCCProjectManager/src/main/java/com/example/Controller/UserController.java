package com.example.Controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Entity.User;
import com.example.Model.LoginDTO;
import com.example.Model.UserDTO;
import com.example.Model.UserFullInfoDTO;
import com.example.Model.UserLogTimeSheetProjectWithoutIdDTO;
import com.example.Service.JwtService;
import com.example.Service.impl.UserServiceImpl;


@CrossOrigin
@Controller
public class UserController {

	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private JwtService jwtService;

	@PostMapping(value=("/register"))
	@ResponseBody
	public String creat_user(@RequestBody User user) {
		List<UserDTO> ursers = userService.findAll();
		for (UserDTO userExist : ursers) {
			if (StringUtils.equals(userExist.getEmail(),user.getUsername()))
			{
				return "{\"status\":\"false\"}"; 
			}
		}
		try {
			userService.save(user);
		} catch (Exception e) {
			return "{\"status\":\"false\"}"; 
		}

		return "{\"status\":\"true\"}";
	}

	@GetMapping(value = { "/get-users" })
	@ResponseBody
	public List<UserDTO> listUser(Model model) {

		return userService.findAll();
	}

	@GetMapping(value = ("/user/{userId}"))
	@ResponseBody
	public User user(@PathVariable(value = "userId") int userId) {
		return userService.findById(userId);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public LoginDTO login(HttpServletRequest request, @RequestBody User user) {
		String result = "";
		User u = new User();
		HttpStatus httpStatus = null;
		try {
			if (userService.checkLogin(user)) {
				result = jwtService.generateTokenLogin(user.getUsername());
				u = userService.loadUserByUsername(user.getUsername());
				httpStatus = HttpStatus.OK;
			} else {
				result = null;
				httpStatus = HttpStatus.BAD_REQUEST;
			}
		} catch (Exception ex) {
			result = "Server Error";
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return userService.TokenvsProfile(result, u);

	}

	@PostMapping(value= ("/user-filter"))
	@ResponseBody
	public List<User> searchUser(@RequestBody HashMap<String, String> Hmap)
	{
		
		return userService.searchUser(Hmap.get("field"), Hmap.get("name"), Hmap.get("index_of_page"));
		
	}


	@RequestMapping(value="/user/get-data-user-paging", method = RequestMethod.POST)
	@ResponseBody
	public List<UserFullInfoDTO> getUserDataPaging(@RequestBody Map<String, Integer> map){
		return userService.getUserDataPaging(map.get("from"), map.get("offset"));
	}
	
	@RequestMapping(value="/user/get-count-users", method = RequestMethod.GET)
	@ResponseBody
	public BigInteger getCountUser() {
		return userService.getCountUser();
	}
	
	
	@RequestMapping(value="/user/user-filter", method = RequestMethod.POST)
	@ResponseBody
	public List<UserFullInfoDTO> getFullLogTimeFilter( @RequestBody Map<String, String> map){
		return userService.filterUser(map.get("field"),map.get("value"),Integer.parseInt(map.get("index_of_page")),Integer.parseInt(map.get("pageSize")));
	}
	
	
	@RequestMapping(value = "/user/get-count-filter-user", method = RequestMethod.POST)
	@ResponseBody
	public BigInteger countDataPagingFilter(@RequestBody Map<String, String> map){
		return userService.countFilterUser(map.get("field"),map.get("value"));
	}

}
