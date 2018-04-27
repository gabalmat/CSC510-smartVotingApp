<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
      		<c:if test="${not empty options}">
      			<table class="login-form" style="margin-bottom: 30px;">
	      			<c:forEach var="option" items="${options}" varStatus="status">
	      				<tr>
	      					<td>${status.count}: &nbsp;&nbsp;</td>
	      					<td>${option.getDescription()}</td>
	      				</tr>
	      			</c:forEach>
	      		</table>
      		</c:if>
      		
      		<form:form method="POST" action="${pageContext.request.contextPath}/savePollOption" modelAttribute="newOption">
      			<table class=login-form>
      				<tr>
      					<td class="first-td"><form:label path="description">Option: </form:label></td>
      					<td><form:input style="width:250px" path="description" rows="7"></form:input></td>
      				</tr>
      				<tr>
      					<td></td>
      					<td><span style="margin:25px; display:inline-block; font-size:1.5rem;"><a href='<spring:url value="/poll/${pollId}"/>'>View Poll</a></span>
      						<span><button type="submit">ADD</button></span>
      					</td>
      				</tr>
      			</table>
      			<form:hidden path="pollId" value="${pollId}"></form:hidden>
      		</form:form>
      	</div>
      </div>
	</div>

  <footer>
      <p class="footer-contents">Designed and developed by NCSU CSC 510 Team A</p>
  </footer>
</body>
</html>