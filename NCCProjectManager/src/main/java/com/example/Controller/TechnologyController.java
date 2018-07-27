package com.example.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.DAO.TechnologyDAO;
import com.example.Entity.TechnologyInfo;
import com.example.Service.TechnologyService;
@CrossOrigin
@Controller

public class TechnologyController {
   
   @Autowired
   private TechnologyService technologyService;
   
   @GetMapping(value= "/get-all-technology")
   @ResponseBody
   public List<TechnologyInfo> findAll() {
	   return technologyService.findAll();
   }
   
   @PostMapping(value= "/get-technology")
   @ResponseBody
   public List<TechnologyInfo> findTechnology(@RequestBody HashMap<String, String> Hmap) {
	return technologyService.findTechnology(Hmap.get("id"));
   }

}
