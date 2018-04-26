<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Category Created</title>
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
	    
	    <div class="message poll-content">
	    	<div class="text">
				<c:if test="${not empty success}">
                    <h2>${success}</h2>
                    <h2>Please go back to the home page and login to proceed.</h2>
				</c:if>
                <c:if test="${not empty failure}">
                    <h3>Category not created, internal error:</h3>
                    <h3>${failure}</h3>
                </c:if>
                <c:if test="${not empty categoryExists}">
                    <h2>${categoryExists}</h2>
                    <h3>${reason}</h3>
                </c:if>
                <p></p>
                <br/>
                <p><b><a href='<spring:url value="/addPoll"/>'>Create Poll</a></b></p>
                <p></p>
                <br/>
                <p><b><a href='<spring:url value="/"/>'>Home Page</a></b></p>
	    	</div>
	    </div>
	</div>
	
	<script src="${setBackgroundJs}"></script>
</body>
</html>