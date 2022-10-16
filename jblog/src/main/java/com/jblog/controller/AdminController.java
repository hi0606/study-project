package com.jblog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jblog.service.AdminService;
import com.jblog.service.BlogService;
import com.jblog.vo.CategoryVo;
import com.jblog.vo.PostVo;
import com.jblog.vo.UserVo;
@Controller
@RequestMapping("/")
public class AdminController {
	@Autowired
private BlogService blogservice;
	@Autowired
private AdminService adminservice;
	//블로그 메인 화면 session에 담긴 id 값을 가져간다.
	
	/*@RequestMapping(value="{id}",method=RequestMethod.GET)
	public String jblog(@PathVariable("id") String id, HttpSession session) {
	 id=session.getId();
		return "blog/blog-main";
	}*/
 	
	//블로그 관리 클릭
	@RequestMapping(value="{id}/admin/basic",method=RequestMethod.GET)
	public String basic(@PathVariable("id") String id, Model model) {
		model.addAttribute("blogvo",blogservice.getBlog(id));
		return "blog/admin/blog-admin-basic";
	}
	
	
	@RequestMapping(value="{id}/admin/category",method=RequestMethod.GET)
	public String category(@PathVariable("id") String id, Model model) {
		model.addAttribute("blogvo",blogservice.getBlog(id));
		return "blog/admin/blog-admin-cate";
	}
	
	@RequestMapping(value="{id}/admin/write",method=RequestMethod.GET)
	public String write(@PathVariable("id") String id, Model model,HttpSession session) {
		model.addAttribute("blogvo",blogservice.getBlog(id));
		UserVo uservo = (UserVo)session.getAttribute("authUser");
		 long userNo = uservo.getUserNo();
		 model.addAttribute("catelist", adminservice.getcate(userNo));//m.aAb=adminservic에서 catelist에 담은 자료를 jsp에 넣어준다.
		return "blog/admin/blog-admin-write";
	}
	//카테고리(방명록) 리스트 띄우기
	   //ajax에 있는 url을 통해서 여기로 넘어온다
	   @ResponseBody 
	   @RequestMapping(value = "/catelist", method=RequestMethod.GET)
	   public List<CategoryVo> getCateList(HttpSession session){
	      UserVo uservo = (UserVo)session.getAttribute("authUser");
	      long userNo = uservo.getUserNo();
	      return adminservice.getList(userNo); 
	   }
	   
  
	 //카테고리(방명록) 삭제할 번호 가져오기
	    @RequestMapping(value="/{userId}/delete/{cateNo}", method = RequestMethod.GET)
	    public String delete(Model model, @PathVariable(value="cateNo") int cateNo, @PathVariable(value="userId") String userId) {
	        model.addAttribute("cateNo", cateNo);
	        return "redirect:/"+userId+"/admin/delete";
	    }

	      //카테고리(방명록) 삭제 처리  
	    @RequestMapping(value="/{userId}/admin/delete", method = RequestMethod.GET)
	    public String delete(@ModelAttribute CategoryVo categoryvo,Model model, @PathVariable(value="userId") String userId) {
	       boolean result =adminservice.delete(categoryvo);
	       if(result) {
	          return "redirect:/"+userId+"/admin/category";
	       }else {
	          model.addAttribute("result", "fail");
	          return "redirect:/"+userId+"/delete/"+categoryvo.getCateNo();
	       }
	    }
	    
	    @RequestMapping(value="/{userId}/admin/pwrite", method = RequestMethod.GET)
	    public String write(@ModelAttribute PostVo postVo,HttpSession session, @PathVariable(value="userId") String userId) {
	      adminservice.pwrite(postVo);
	      return "redirect:/"+userId+"/admin/write";
	    }
}







/*
//카테고리 추가
@ResponseBody
@PostMapping(value="{userid}/admin/insertcate")
public List<CategoryVo> insertcate(Model model, //리스트에 추가한 값(마지막 넘버)을 불러온다.
		@PathVariable(value="userid")String userid, HttpSesseion session, String catename, String description){//
		UserVo userVO = (UserVo)session.getAttribute("authUser");
		CategoryVo categoryVo = new CategoryVo();
		CategoryVo.setUserNo(userVo.getUserNo());
}*/
		
		