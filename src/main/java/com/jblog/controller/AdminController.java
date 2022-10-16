package com.jblog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/admin")
public class AdminController {
	
	/*@RequestMapping(value="{id}/admin/basic",method=RequestMethod.GET)
	public String jblog(@PathVariable("id") String id, HttpSession session) {
	 id=session.getId();
	 
		return "blog/admin/blog-admin-basic";
	}*/
	
}
