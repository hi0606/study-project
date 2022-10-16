package com.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jblog.repository.BlogDao;
import com.jblog.vo.BlogVo;
import com.jblog.vo.CategoryVo;
import com.jblog.vo.CommentsVo;
import com.jblog.vo.PostVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogdao;
	
	public BlogVo getId(String id) {
		return blogdao.getId(id);
	}
	
	//
	public BlogVo getBlog(String userId) {
		// TODO Auto-generated method stub
		System.out.println("blogservice:"+userId);
		System.out.println("blogservice : " +blogdao.getBlog(userId));
		return blogdao.getBlog(userId);
	}
	//
	public List<CategoryVo> getCateNo(long userNo){
		return blogdao.getCateNo(userNo);
	}
	
	public List<PostVo> getfirstPostList(long userNo){
		return blogdao.getfirstPostList(userNo);
	}
	
	public List<PostVo> getpostlist(int cateNo){
		return blogdao.getpostlist(cateNo);
	}
	public List<PostVo> getcontentlist(int postNo){
		return blogdao.getcontentlist(postNo);
	}
	public PostVo getPostContent(long userNo){ //하나만 담아옴 주의!
		return blogdao.getPostContent(userNo);
	}
	public Long getUserNo(String userId) {
		return blogdao.getUserNo(userId);
	}
	public List<CommentsVo> getReply(Long userNo){
		return blogdao.getReply(userNo);
	}
	public List<CommentsVo> getCommentsList(int postNo){
		return blogdao.getCommentsList(postNo);
	}
	public List<CommentsVo> addReply(CommentsVo commentsvo){
		return blogdao.addReply(commentsvo);
	}
	public void modify(BlogVo blogVo) {
	      blogdao.modify(blogVo);
	   }
}
