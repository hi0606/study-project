package com.jblog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jblog.repository.AdminDao;
import com.jblog.repository.BlogDao;
import com.jblog.vo.BlogVo;
import com.jblog.vo.CategoryVo;
import com.jblog.vo.PostVo;

@Service
public class AdminService {
	
	@Autowired
	private BlogDao blogdao;
	@Autowired
	private AdminDao admindao;
	public BlogVo getId(String id) {
		return blogdao.getId(id);
	}
	
	
	public BlogVo getBlog(String userId) {
		// TODO Auto-generated method stub
		System.out.println("blogservice:"+userId);
		System.out.println("blogservice : " +blogdao.getBlog(userId));
		return blogdao.getBlog(userId);
	}
	
	   //카테고리 리스트 Dao로 이동
	   public ArrayList<CategoryVo> getList(long userNo){
	      ArrayList<CategoryVo> obj = (ArrayList<CategoryVo>) admindao.getList(userNo);
	      for(int i=0; i<obj.size(); i++) {
	      //   System.out.println(obj.get(i) + "list"); 
	      }
	      return obj;
	   }
	   
	   //카테고리 삭제 Dao로 이동
	   public Boolean delete(CategoryVo categoryvo) {
	      return admindao.delete(categoryvo);
	   } 
	   //admin-controller model.addAtribute에서 adminservice.getcate선언
	   public List<CategoryVo> getcate(long userNo){
		   return admindao.getcate(userNo);
		   
	   }
	   //post테이블에 글 추가 (void)
	   public void pwrite(PostVo postVo) {
		   admindao.pwrite(postVo);
		   
	   }
	   
	   
	   
	   }






/*
   //아무 값이 반환되지 않아서 사용
 	public Boolean modify(BlogVo blogVo) {
 			sysout
 	}*/