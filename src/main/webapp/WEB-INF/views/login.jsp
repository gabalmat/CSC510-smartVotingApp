<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	    <title>SmartVote</title>
	    <meta name="description" content="" />
	
	    <spring:url value="/resources/gradients.css" var="gradientsCss" />
	    <spring:url value="/resources/styles.css" var="stylesCss" />
	    <spring:url value="/resources/set-background.js" var="setBackgroundJs" />
	    <spring:url value="/resources/tweet.svg" var="tweetSvg" />
	
	    <link href="${stylesCss}" rel="stylesheet">
	    <link href="${gradientsCss}" rel="stylesheet">
	  </head>

	<body>
		<div class="wrapper">
	      <header>
	        <nav class="website-nav"></nav>
	      </header>
	
	      <div class="message">
	        <div class="text">
	          <h1>SmartVote</h1> 
	          <h2>Sign In</h2>
	          <form action='<spring:url value="/signin"/>' method="post">
			    <table class="login-form">
			      <tr>
			        <td>Username</td>
			        <td><input type="text" name="userid"></td>
			      </tr>
			      <tr>
			        <td>Password</td>
			        <td><input type="password" name="passwd"></td>
			      </tr>
			      <tr>
			        <td colspan=2><button type="submit">LOGIN</button></td>
			      </tr>
			    </table>
			  </form>
			  <c:if test="${not empty sessionScope.message}">
			    <span style="color:red;display:block;margin-bottom:15px;"><c:out value="${sessionScope.message}"/></span>
			    <c:remove var="message" scope="session" />
			  </c:if>
			  <div><a href="">Register for a new account</a></div>
	        </div>
	      </div>
	    </div>
	  
  	  <br/>
	  
	  <footer>
	  	<p class="footer-contents">Designed and developed by NCSU CSC 510 Team A</p>
	  </footer>
	
	  <script src="${setBackgroundJs}"></script>
	</body>
</html>