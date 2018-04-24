<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Poll Options</title>
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
      		<h2>Add Poll Voting Options</h2>
      		<form:form method="POST" action="${pageContext.request.contextPath}/addPollOptions" modelAttribute="newOption">
      		</form:form>
      	</div>
      </div>
	</div>
</body>
</html>