<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
        <form:form method="POST" action="${pageContext.request.contextPath}/add/poll" modelAttribute="newPoll">
          <table class=login-form>
            <tr>
              <td class="first-td"><form:label path="title">Title: </form:label></td>
              <td><form:input class="new-title" path="title"></form:input></td>
            </tr>
            <tr>
              <td class="first-td"><form:label path="description">Description: </form:label></td>
              <td><form:textarea path="description" rows="7"></form:textarea></td>
            </tr>
            <tr class="cat-selection">
              <td class="first-td"><form:label path="categoryId">Category: </form:label></td>
              <td>
              	<form:select path="categoryId" class="cat-dropdown">
              		<form:options items="${categoryList}" itemValue="id" itemLabel="name" />
              	</form:select>
              </td>
            </tr>
            <tr>
              <td colspan=2><button type="submit">CREATE</button></td>
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