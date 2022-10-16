package com.jblog.repository;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jblog.vo.BlogVo;



@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	public BlogVo getId(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("blog.getById",id);
	}
	public BlogVo getBlog(String id) {
		// TODO Auto-generated method stub
		System.out.println("blogdao:"+id);
		return sqlSession.selectOne("blog.getByblog",id);
	}
	
	
	

	
}