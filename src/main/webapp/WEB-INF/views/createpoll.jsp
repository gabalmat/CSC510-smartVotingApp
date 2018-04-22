<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Poll</title>
</head>
<body>
	<h3>Create a New Poll</h3>
	<form:form method="POST" action="/SmartVoting/poll" modelAttribute="poll">
		<table>
			<tr>
				<td><form:label path="title">Title</form:label></td>
				<td><form:input path="title"/></td>
			</tr>
			<tr>
				<td><form:label path="description">Description</form:label></td>
				<td><form:textarea path="description"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="UPDATE"/></td>
			</tr>
		</table>
	</form:form>
</body>
</html>