package com.jblog.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.jblog.vo.UserVo;


@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
//	@Autowired
//	private DataSource dataSource;
	
	public UserDao() {
		System.out.println("userDao 생성");
	}
	public Boolean update(UserVo vo) {
		int count  = sqlSession.update("user.update",vo);
		return 1 == count;
	}

	public UserVo get(long no) {
		return sqlSession.selectOne("user.getByNo", no);
	}
	
	public UserVo get(String id) {
		return sqlSession.selectOne("user.getById", id);
	}

	public UserVo get(String id, String password) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("password", password);
		
		UserVo userVo = sqlSession.selectOne("user.getByIdAndPassword",map);
		
		return userVo;
	}

	public boolean insert(UserVo vo) {
		System.out.println(vo);
		int count = sqlSession.insert("user.insert",vo);
		System.out.println(vo);
		return 1==count;
	}
	public Boolean makeblog(UserVo userVo) {
		// TODO Auto-generated method stub
		int count = sqlSession.insert("user.makeblog",userVo);
		System.out.println(userVo);
		return 1==count;
	}

}
