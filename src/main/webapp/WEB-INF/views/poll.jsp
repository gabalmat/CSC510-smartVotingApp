<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Some Poll...</title>
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
	    		<h2>${poll.title}</h2>
				<c:if test="${not empty msg}">
					${msg}
				</c:if>
				<table class="display-poll">
					<tr>
						<td>${poll.description}</td>
				</table>
	    	</div>
	    </div>
	</div>
	
	<script src="${setBackgroundJs}"></script>
</body>
</html>