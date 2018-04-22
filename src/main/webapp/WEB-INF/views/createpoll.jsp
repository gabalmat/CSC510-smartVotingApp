<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Create Poll</title>
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
				<h2>Create a New Poll</h2>
				<form method="POST" name="add_poll" action="<%=request.getContextPath()%>/add/poll">
					<table class=login-form>
						<tr>
							<td class="first-td"><label>Title: </label></td>
							<td><input class="new-title" name="title" value="${title}" type="text"/></td>
						</tr>
						<tr>
							<td class="first-td"><label>Description: </label></td>
							<td><textarea name="description" rows=7></textarea></td>
						</tr>
						<!-- <tr>
							<td class="cat-selection first-td"><label>Category: </label></td>
							<td class="cat-selection">Select One: </td>
						</tr>
						<tr>
							<td colspan=2>
								<table>
									<tr>
										<td>Category</td>
										<td>Description</td>
									</tr>
								</table>
							</td>
						</tr> -->
						<tr>
							<td colspan=2><button type="submit">CREATE</button></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<script src="${setBackgroundJs}"></script>
</body>
</html>