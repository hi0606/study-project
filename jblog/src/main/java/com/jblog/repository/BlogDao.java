package com.jblog.repository;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jblog.vo.BlogVo;
import com.jblog.vo.CategoryVo;
import com.jblog.vo.CommentsVo;
import com.jblog.vo.PostVo;



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
		System.out.println("blogdao: " +  sqlSession.selectOne("blog.getByblog",id));
		return sqlSession.selectOne("blog.getByblog",id);
	}
	
	public List<CategoryVo> getCateNo(long userNo){
		System.out.println("categorydao:"+sqlSession.selectList("blog.getCateNo",userNo));
		return sqlSession.selectList("blog.getCateNo",userNo);
	}
	
	public List<PostVo> getfirstPostList(long userNo){
			return sqlSession.selectList("blog.getfirstPostList",userNo);
	}
	public List<PostVo> getpostlist(int cateNo){
		return sqlSession.selectList("blog.getpostlist",cateNo);
	}
	public List<PostVo> getcontentlist(int postNo){
		return sqlSession.selectList("blog.getcontentlist",postNo);
	}
	public PostVo getPostContent(long userNo){
		return sqlSession.selectOne("blog.getPostContent",userNo);//List가 아닌 one (하나만 담아오므로)
	}
	public Long getUserNo(String userId) {
		return sqlSession.selectOne("blog.getUserNo",userId);
	}
	public List<CommentsVo> getReply(Long userNo){
		return sqlSession.selectList("blog.getReply",userNo);
	}
	public List<CommentsVo> getCommentsList(int postNo){
		return sqlSession.selectList("blog.getCommentsList",postNo);
	}
	public List<CommentsVo> addReply(CommentsVo commentsvo){
		sqlSession.insert("blog.addReply",commentsvo);
		return sqlSession.selectOne("blog.firstReply");
	}
	public void modify(BlogVo blogVo) {
	      sqlSession.update("blog.modify",blogVo);
	   }
}