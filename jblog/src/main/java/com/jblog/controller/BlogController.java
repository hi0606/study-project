package com.jblog.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jblog.service.BlogService;
import com.jblog.vo.BlogVo;
import com.jblog.vo.CommentsVo;
import com.jblog.vo.PostVo;
import com.jblog.vo.UserVo;

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
 	
	
	//내블로그 
	@RequestMapping(value="{userId}",method=RequestMethod.GET)
	public String myblog(@PathVariable("userId") String userId, Model model,HttpSession session) {
		Long userNo = blogservice.getUserNo(userId);
		UserVo uservo = (UserVo)session.getAttribute("authUser");
		System.out.println(uservo);
		long userno = uservo.getUserNo();
		model.addAttribute("blogvo",blogservice.getBlog(userId));
		model.addAttribute("cateNo",blogservice.getCateNo(userNo));
		model.addAttribute("postvo",blogservice.getfirstPostList(userNo));
		model.addAttribute("postcontent",blogservice.getPostContent(userNo));
		model.addAttribute("commentsVo",blogservice.getReply(userNo));
		System.out.println("확인"+blogservice.getCateNo(userNo));
		return "blog/blog-main";
	}
	@ResponseBody
	@RequestMapping(value="/catemenu",method=RequestMethod.GET)
	public List<PostVo> getpostlist(@RequestParam("cateNum") String cateNum){
		int cateNo=Integer.parseInt(cateNum); //Integer.parseInt : string함수로 받아온 catenum을 강제로 int로 변환 해주는 것
		return blogservice.getpostlist(cateNo);
	}
	@ResponseBody
	@RequestMapping(value="/postmenu",method=RequestMethod.GET)
	public List<PostVo> getcontentlist(@RequestParam("postNum") String postNum){
		int postNo=Integer.parseInt(postNum); //Integer.parseInt : string함수로 받아온 catenum을 강제로 int로 변환 해주는 것
		return blogservice.getcontentlist(postNo);
	}
	
	@ResponseBody
	@RequestMapping(value="/{userId}/getCommentsList",method=RequestMethod.GET)
	public List<CommentsVo> getCommentsList(@PathVariable("userId") String userId,@RequestParam("postNum") String postNum){
		int postNo=Integer.parseInt(postNum); //Integer.parseInt : string함수로 받아온 catenum을 강제로 int로 변환 해주는 것
		return blogservice.getCommentsList(postNo);
	}
	@ResponseBody
	@RequestMapping(value="/{userId}/addReply",method=RequestMethod.GET)
	public List<CommentsVo> addReply(@PathVariable("userId") String userId,
			@RequestParam("postNum") String postNum,@RequestParam("name") String name, @RequestParam("replyContent") String replyContent){
		int postNo=Integer.parseInt(postNum);
		BlogVo blogvo = blogservice.getBlog(userId);
		Long userNo = blogvo.getUserNo();		
		CommentsVo commentsvo = new CommentsVo();
		commentsvo.setUserNo(userNo);
		commentsvo.setCoName(name);
		commentsvo.setCmtContent(replyContent);
		commentsvo.setPostNo(postNo);		
		return blogservice.addReply(commentsvo);
	}

	@RequestMapping(value="/{id}/admin/update",method=RequestMethod.POST)
	public String modify(@PathVariable(value="id") String id,HttpSession session, MultipartFile file,@RequestParam("blogTitle")String blogTitle){
		System.out.println("들어옴");
		BlogVo blogVo = blogservice.getBlog(id);
	      if(!file.isEmpty()) {
		String uploadFolder = "C:\\Users\\admin\\eclipse-workspace\\jblog\\jblog\\src\\main\\webapp\\assets\\images";
		String uploadFileName = file.getOriginalFilename();
		uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
		UUID uuid = UUID.randomUUID();
		uploadFileName = uuid.toString()+"_"+uploadFileName;
		File saveFile = new File(uploadFolder, uploadFileName);
		try {
			file.transferTo(saveFile);
		}catch(Exception e) {
			e.getMessage();
		}
		blogVo.setLogoFile(uploadFileName);
	      }
	     /* blogVo.setBlogTitle(blogTitle);
	      System.out.println("블로그타이틀"+blogTitle);*/
	      System.out.println("blogVo"+blogVo);
	      blogservice.modify(blogVo);
	      return "redirect:/"+id+"/admin/basic";
	   }  
				
	}
