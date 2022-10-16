package com.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jblog.repository.BlogDao;
import com.jblog.vo.BlogVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogdao;
	
	public BlogVo getId(String id) {
		return blogdao.getId(id);
	}
	
	
	public BlogVo getBlog(String userId) {
		// TODO Auto-generated method stub
		System.out.println("blogservice:"+userId);
		return blogdao.getBlog(userId);
	}
	
}
