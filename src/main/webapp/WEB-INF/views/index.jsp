<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>${siteName}</title>
    <meta name="description" content="" />

    <spring:url value="/resources/gradients.css" var="gradientsCss" />
    <spring:url value="/resources/styles.css" var="stylesCss" />
    <spring:url value="/resources/set-background.js" var="setBackgroundJs" />
    <spring:url value="/resources/tweet.svg" var="tweetSvg" />

    <link href="${stylesCss}" rel="stylesheet">
    <link href="${gradientsCss}" rel="stylesheet">
  </head>
  <body class="">
    <div class="wrapper">
      <header>
        <nav class="website-nav"></nav>
      </header>

      <div class="message">
        <div class="text">
          <h1>${siteName}</h1> 
  	  	  <h3>${welcomeMessage}</h3>
          <p></p>
          <br/>
          <img src="${pageContext.request.contextPath}/resources/images/blank-profile-picture.png" width="256" height="256">
          <p></p>
  	  	  <br/>
          <p><b><a href='<spring:url value="/addPoll"/>'>${createPoll}</a></b></p>
          <br/>
          <p><b><a href='<spring:url value="/viewMyPolls"/>'>${viewMyPolls}</a></b></p>
          <br/>
          <p><b><a href='<spring:url value="/searchPolls"/>'>${searchPolls}</a></b></p>
          <br/>
          <p><b><a href='<spring:url value="/createCategory"/>'>Create cat</a></b></p>
          <br/>
          <p><b><a href='<spring:url value="/createUser"/>'>create user</a></b></p>
          <br/>
	  	    <p><b><a href='<spring:url value="/signout"/>'>Logout</a></b></p>
        </div>
      </div>
    </div>

    <footer>
      <p class="footer-contents">Designed and developed by NCSU CSC 510 Team A</p>
    </footer>

    <script src="${setBackgroundJs}"></script>
  </body>
</html>
