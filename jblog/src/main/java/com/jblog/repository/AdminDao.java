package com.jblog.repository;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jblog.vo.CategoryVo;
import com.jblog.vo.PostVo;



@Repository
public class AdminDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	
	
	  //카테고리 리스트 출력
	   public List<CategoryVo> getList(long userNo){      
	      return sqlSession.selectList("admin.getList",userNo);
	   }
	   
	   //카테고리 삭제 
	   public boolean delete(CategoryVo catevo) {
	      int count = sqlSession.delete("admin.delete", catevo);
	      return 1==count;
	   }
	   //글작성 카테고리
	   public List <CategoryVo> getcate(long userNo){
		   return sqlSession.selectList("admin.getcate", userNo);
	   }
	   // post 글 추가 insert -> service(void)
	   public void pwrite (PostVo postVo) {
		   sqlSession.insert("admin.pwrite",postVo);
	   }
}