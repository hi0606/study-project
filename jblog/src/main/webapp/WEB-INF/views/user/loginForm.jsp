<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div class="center-content">
		
		<!-- 메인해더 -->
	 	<a href="${pageContext.servletContext.contextPath }/main">
			<img class="logo" src="${pageContext.request.contextPath}/assets/images/logo.jpg">
		</a>
		<ul class="menu">
				 <c:choose>
               <%-- 로그인 전 화면 --%>
                  <c:when test='${empty authUser}'>
                     <li><a href="${pageContext.servletContext.contextPath }/user/loginForm">로그인</a></li>
                     <li><a href="${pageContext.servletContext.contextPath }/user/joinForm">회원가입</a></li>
                  </c:when>
               <%-- 로그인 후 화면 --%>
                  <c:otherwise>
                     <li><a href="${pageContext.servletContext.contextPath }/user/logout">로그아웃</a></li>
                     <li><a href="${pageContext.servletContext.contextPath }/blog/blog-main">내블로그</a></li> 
                  </c:otherwise>
               </c:choose>
				
 		</ul>
		
		<form class="login-form" method="post" action="${pageContext.servletContext.contextPath }/user/auth">
      		<label>아이디</label> 
      		<input type="text" name="id">
      		
      		<label>패스워드</label> 
      		<input type="text" name="password">
      		<c:if test='${result eq "fail" }'>
				<p>로그인 실패</p>
				<p>아이디/패스워드를 확인해 주세요</p>
		</c:if>
      		<input type="submit" value="로그인">
		</form>
		
      		
	</div>
</body>

</html>