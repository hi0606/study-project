<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script>
//cateNo 숨김처리 함수
	$(document).ready(function(){
		var index=0;
	      var cateNo=0;
	      var authUser = sessionStorage.getItem("authUser");
	      $('.cateNo').hide();
	      $(".cateName").off('click').on('click',function(e){
	         e.preventDefault();
	         index = $(".cateName").index(this);
	          cateNo = $(".cateNo:eq("+index+")")[0].innerHTML;
	         cateNameClick();
	         $('.postNo').hide();
	         postTitleClick();
	      })
	       function cateNameClick(){
			console.log("cateNo"+cateNo);
			$.ajax({
	         type : "GET",  // GET= 정보를 안 숨기고 POST = 정보를 숨기고
	         data : {
	        	 cateNum: cateNo
	         },
	         url :  "${pageContext.servletContext.contextPath}/catemenu", //이건 컨트롤러로 접속해서 서비스 dao xml가서 db에서 값 가져오려고 하는거 url접속 
	         async:false,//async 비동기식을 동기식(순차적으로실행)으로 강제로 변환=false	         
	         success : function(obj){         //obj는 컨트롤러/서비스에서 리턴값 받을 변수 설정    (근데 obj 아니면 data로 하는게 좋아) 
	        	 $(".blog-list").empty();//blog list의 값을 비운다. 안비우면 카테고리 클릭 시 기존 리스트에 추가되어 나타남(강제리셋)
	        	 console.log(obj)
                 var resultCate="";
                 var blogcontent="";
                 if(obj.length > 0){
                    for(var i=0; i<obj.length;i++){
                       console.log(obj[i]);
                       resultCate += "<li class='postList'>"
                       resultCate += "<a href='' class='postTitle'>"+obj[i]['postTitle']+"</a>"
                       resultCate += "<span class='postNo'>"+obj[i]['postNo']+"</span>"
                       resultCate += "<span>"+obj[i]['regDate']+"</span>"
                       resultCate += "</li>"
                    }

	        	 }else{
	        		 $(".blog-content").empty();
	        		 blogcontent+="<h4>등록된 글이 없습니다.</h4>"
	        		 $(".blog-content").append(blogcontent);
	        		 $("#commentTable").empty();
	        	 }                 
                 $(".blog-list").append(resultCate);
              }, 
              error : function(xhr,status,error){ 
            	  alert(error+"에러1");   
              }			
           })
	      }
			
	      var postNo = 0;
	      function postTitleClick(){
			$('.postNo').hide();
			$(".postTitle").off('click').on('click',function(e){
				e.preventDefault();
				var index=$(".postTitle").index(this);//this : catename을 클릭했을때 catename 그 자체를 말함
				postNo = $(".postNo:eq("+index+")")[0].innerHTML;
	               getPostContent();
	               getCommentList();
	               replySaveClick();
	            })
	         }
	          
	       function getPostContent(){ 
				console.log("postNo"+postNo);
				$.ajax({
		         type : "GET",  //이건 나도 모르겠는데? 값을 가져오려면 GET으로 해야하는거 같던데 이건 소미가 설명해줘 GET= 정보를 안 숨시고 POST = 정보를 숨기고
		         data : {
		        	 postNum: postNo
		         },
		         url : "${pageContext.servletContext.contextPath}/postmenu", //이건 컨트롤러로 접속해서 서비스 dao xml가서 db에서 값 가져오려고 하는거 url접속 
		         async:false,//async 비동기식을 동기식(순차적으로실행)으로 강제로 변환=false
		         
		         success : function(obj){         //obj는 컨트롤러/서비스에서 리턴값 받을 변수야 랜덤으로 하고싶은거 하면되    (근데 obj 아니면 data로 하는게 좋아)
		        	
		         	$('.blog-content').empty();//blog list의 값을 비운다. 안비우면 카테고리 클릭 시 기존 리스트에 추가되어 나타남(강제리셋)
		         	  console.log(obj)
	                     var postContent="";
	                        for(var i=0; i<obj.length;i++){
	                           console.log(obj[i]);
	                           postContent += "<h4>"+obj[i]['postTitle']+"</h4>"
	                           postContent += "<p>"+obj[i]['postContent']+"</p>"//p태그 : 문장을 나타냄 a태그 : 하이퍼링크로 페이지로 이동	                   
	                 }
	                        $(".blog-content").append(postContent);//append : 64번째줄 null값이므로 h4와 p태그를 덧붙여준다.
	              }, 
	              error : function(xhr,status,error){ 
	            	  alert(error+"에러");   
	              }
	           })
	       }
				
	       function getCommentList(){
	                $.ajax({
	                  type : "GET",
	                  data : {
	                     postNum: postNo
	                  },
	                  async: false,
	                  url : "${pageContext.servletContext.contextPath}/${blogvo.userNo}/getCommentsList",
	                  success : function(obj){
	                     $("#commentTable").empty();
	                     console.log(obj)
	                     var commentContent="";
	                     commentContent += "<table class='commentTable'>"
	                        for(var i=0; i<obj.length;i++){
	                           console.log(obj[i]);
	                           commentContent += "<tr class='commentTr'>"
	                           commentContent += "<td>"+obj[i]['coName']+"</td>"
	                           commentContent += "<td>"+obj[i]['cmtContent']+"</td>"
	                           commentContent += "<td>"+obj[i]['regDate']+"</td>"
	                           commentContent += "</tr>"
	                        }
	                     commentContent += "</table>"
	                     $("#commentTable").append(commentContent);
	                  },
	                  error : function(xhr,status,error){
	                     alert(error+"에러2");
	                  }
	               })
	       }

				function replySaveClick(){
         $(".replySave").off('click').on('click', function(e){
							e.preventDefault();
							replySave();
         				})
      					}      
      
      						 function replySave(){
							var name = $(".name").text();
							var replyContent = $('.replyContent')[0].value;
							$.ajax({
								type : "GET",
								date : {
									postNum: postNo,
									name : name,
									replyContent: replyContent
								},
								async : false, 
								url : "${pageContext.servletContext.contextPath}/${blogvo.userNo}/addReply",
								success : function(obj){
			                        console.log(obj)
			                        var commentContent="";
			                           commentContent += "<tr>";
			                           commentContent += "<td>"+obj['coName']+"</td>"
			                           commentContent += "<td>"+obj['cmtContent']+"</td>"
			                           commentContent += "<td>"+obj['regDate']+"</td>"
			                           commentContent += "</tr>";
			                           $(".commentTable").prepend(commentContent);
			                     },
									error : function(xhr,status,error){
										alert(error+"에러3");
									}
							})
						}		
	
			$('.postNo').hide();
			$(".postTitle").off('click').on('click', function(e) {
		          index = $(".postTitle").index(this);
		          postNo = $(".postNo:eq("+index+")")[0].innerHTML;
		          e.preventDefault();
		          getPostContent();
		          getCommentList();
		          replySaveClick();
		       });
		       function getPostContent(){
				console.log("postNo"+postNo);
				$.ajax({
		         type : "GET",  //이건 나도 모르겠는데? 값을 가져오려면 GET으로 해야하는거 같던데 이건 소미가 설명해줘 GET= 정보를 안 숨시고 POST = 정보를 숨기고
		         data : {
		        	 postNum: postNo
		         },
		         url : "${pageContext.servletContext.contextPath}/postmenu", //이건 컨트롤러로 접속해서 서비스 dao xml가서 db에서 값 가져오려고 하는거 url접속 
		         async:false,//async 비동기식을 동기식(순차적으로실행)으로 강제로 변환=false		         
		         success : function(obj){         //obj는 컨트롤러/서비스에서 리턴값 받을 변수야 랜덤으로 하고싶은거 하면되    (근데 obj 아니면 data로 하는게 좋아)
		        	 $(".blog-content").empty();
	                  console.log(obj)
	                  var postContent="";
	                 for(var i=0; i<obj.length; i++){
	                	 console.log(obj[i]);
	                	 postContent += "<h4>"+obj[i]['postTitle']+"</h4>"
	                        postContent += "<p>"+obj[i]['postContent']+"</p>"
	                     }
	                     $(".blog-content").append(postContent);
	               },
	              error : function(xhr,status,error){ 
	                 alert(error+"에러4");  
	              }
	           })
		       }
		       function getCommentList(){
		            $("#commentTable").empty();				
				$.ajax({
		         type : "GET",  
		         data : {
		        	 postNum: postNo
		         },
		         url : "${pageContext.servletContext.contextPath }/${blogvo.userNo}/getCommentsList", //이건 컨트롤러로 접속해서 서비스 dao xml가서 db에서 값 가져오려고 하는거 url접속 
		         async:false,//async 비동기식을 동기식(순차적으로실행)으로 강제로 변환=false
		         
		         success : function(obj){         //obj는 컨트롤러/서비스에서 리턴값 받을 변수야 랜덤으로 하고싶은거 하면되    (근데 obj 아니면 data로 하는게 좋아)
		        	 $('#commentTable').empty();//blog list의 값을 비운다. 안비우면 카테고리 클릭 시 기존 리스트에 추가되어 나타남(강제리셋)
	                console.log(obj)
	                var commentContent="";
	                commentContent += "<table class='commentTable'>"
	                 for(var i=0; i<obj.length; i++){
	                	 console.log(obj[i]);
	                  commentContent += "<tr class='commentTr'>"
	                  commentContent += "<td>"+obj[i]['coName']+"</td>"
	                  commentContent += "<td>"+obj[i]['cmtContnet']+"</td>"
	                  commentContent += "<td>"+obj[i]['regDate']+"</td>"
	                  commentContent += "</tr>"
	                 }
		        	 commentContent +="</table>"
	                 $("#commentTable").append(commentContent);
	              }, 
	              error : function(xhr, status, error){ 
	                 alert(error+"에러5");   
	              }
	           })
		       }
		
		       function replySaveClick(){
		           $(".replySave").off('click').on('click',function(e){
			e.preventDefault();
			replySave();
		           })
		        }
		              
		         function replySave(){

			var name = $(".name").text();
			var replyContent = $('.replyContent')[0].value;
			$.ajax({
				type : "GET",
				date : {
					postNum: postNo,
                    name: name,
                    replyContent: replyContent
                 },
				async : false, 
				url : "${pageContext.servletContext.contextPath}/${blogvo.userNo}/addReply",
				success : function(obj){
				console.log(obj)
				var commentContent="";
                commentContent += "<tr>";
                commentContent += "<td>"+obj['coName']+"</td>"
                commentContent += "<td>"+obj['cmtContent']+"</td>"
                commentContent += "<td>"+obj['regDate']+"</td>"
                commentContent += "</tr>";
                $(".commentTable").prepend(commentContent);
          },

					error : function(xhr,status,error){
						alert(error+"에러6");
					}
			})
		}
	
	})
</script>
<style>
   table {
      border-collapse: collapse;
      border:1px solid gray;
   }
   table td {
      border: 1px solid gray;
      padding: 3px;
   }
</style>
</head>
<body>

	<div id="container">

		<!-- 블로그 해더 -->
		<div id="header">
			<h1><a href="">${blogvo.blogTitle}</a></h1>
			<ul>
				<!-- 로그인 전 메뉴 -->
				<li><a href="${pageContext.servletContext.contextPath }/user/loginForm">로그인</a></li>
				
				<!-- 로그인 후 메뉴 -->
				
				<li><a href="${pageContext.servletContext.contextPath }/user/logout">로그아웃</a></li>
				<li><a href="${pageContext.servletContext.contextPath }/${authUser.id}/admin/basic">내블로그 관리</a></li>
				 		
			</ul>
		</div>
		
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
				<c:choose>
				<c:when test="${empty postContent}">
					<h4>등록된 글이 없습니다!</h4>
					</c:when>
					<c:otherwise>
					<h4>${postcontent.postTitle}</h4>
					<p>${postcontent.postContent}</p>
					</c:otherwise>
					</c:choose>
					
				</div>
				
				<ul class="blog-list">
					<c:forEach var="postvo" items="${postvo}" step="1">
					<li class="postlist">
					<a href="" class="postTitle">${postvo.postTitle}</a>
					<span class="postNo">${postvo.postNo}</span>
					<span>${postvo.regDate }</span>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="reply">
               <form>
                  <table>
                     <tr>
                        <td class="name">${authUser.userName}</td>
                        <td>
                           <input type="text" class="replyContent"/>
                        </td>
                        <td>
                           <input type="button" value="저장" class="replySave" />
                        </td>
                     </tr>
                  </table>               
               </form>
               <div id="commentTable">
                  <table class="commentTable">
                     <c:forEach var="commentsVo" items="${commentsVo}" step="1">
                        <tr class="commentTr">
                           <td>${commentsVo.coName}</td>
                           <td>${commentsVo.cmtContent}</td>
                           <td>${commentsVo.regDate}</td>
                        </tr>
                     </c:forEach>
                  </table>
               </div>
            </div>


		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">				
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach var="cateNo" items="#{cateNo}" step="1">
				<li><a href="" class="cateName">${cateNo.cateName}</a></li>
				<li class="cateNo">${cateNo.cateNo}</li>
				</c:forEach>
			</ul>
		</div>
		
		<!-- 푸터-->
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2018
			</p>
		</div>
		
	</div>
</body>
</html>