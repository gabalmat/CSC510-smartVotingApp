<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>${poll.title}</title>
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
                    <br/>
                    <p><b><a href='<spring:url value="/poll/${poll.pollId}"/>'>Poll #${poll.pollId}</a></b></p>
				</c:if>
				<table width="100%" class="display-poll">
					<tr>
						<td>${poll.description}</td>
                    </tr>
                    <tr>
                    	<td><h3>Discussion:</h3></td>
                    </tr>
				</table>
				
				<table width="100%" border="1">
	                <th>No</th>
	                <th>Username</th>
	                <th>Content</th>
	                <th>TIme Posted</th>
	                <c:forEach var="comment" items="${listComments}" varStatus="status">
		                <tr>
		                    <td>${status.index + 1}</td>
		                    <td>${comment.userId}</td>
		                    <th>${comment.content}</th>
		                    <th>${comment.created}</th>
		                </tr>
	                </c:forEach>             
	            </table>
	            
	            <table width="100%" class="display-poll vote-options-tbl">
	            	<tr>
                    	<td><h3>Voting Options:</h3></td>
                    </tr>
	            </table>
	            
	            <form:form method="POST" action="${pageContext.request.contextPath}/vote" modelAttribute="newVote">
	            	<table width="100%" class="display-poll login-form">
	                    <c:forEach var="option" items="${listPollOptions}" varStatus="status">
	                    	<tr>
	                    		<td><form:radiobutton path="pollOptionId" value="${option.id}" label="${option.description}"></form:radiobutton></td>
	                    		<form:hidden path="pollId" value="${option.pollId}"></form:hidden>
	                    	</tr>
	                    </c:forEach>
	                    <tr>
	                    	<td><button type="submit">SUBMIT VOTE</button></td>
	                    </tr>
		            </table>
	            </form:form>
	            
                <p></p>
                <br/>
                <p><b><a href='<spring:url value="/"/>'>Home Page</a></b></p>
	    	</div>
	    </div>
	</div>
	
	<script src="${setBackgroundJs}"></script>
</body>
</html>