package com.jblog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jblog.service.BlogService;

@Controller
@RequestMapping("/")
public class BlogController {
	@Autowired
private BlogService blogservice;
	//블로그 메인 화면 session에 담긴 id 값을 가져간다.
	
	/*@RequestMapping(value="{id}",method=RequestMethod.GET)
	public String jblog(@PathVariable("id") String id, HttpSession session) {
	 id=session.getId();
		return "blog/blog-main";
	}*/
 	
	//블로그 관리 클릭
	@RequestMapping(value="{id}/admin/basic",method=RequestMethod.GET)
	public String basic(@PathVariable("id") String id) {
		return "blog/admin/blog-admin-basic";
	}
	
	//내블로그 메인 제목 내이름으로 변경
	@RequestMapping(value="{userId}",method=RequestMethod.GET)
	public String myblog(@PathVariable("userId") String userId, Model model) {
		model.addAttribute("blogVo",blogservice.getBlog(userId));
		System.out.println("blogcontroller:"+userId);
		return "blog/blog-main";
	}
}

