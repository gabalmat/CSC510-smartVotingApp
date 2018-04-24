<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Create Category</title>
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
        <h2>Create a New Category</h2>
        <form method="POST" name="add_category" action="<%=request.getContextPath()%>/categoryCreateResult">
          <table class=login-form>
            <tr>
              <td class="first-td"><label>* Category Name: </label></td>
              <td><input name="categoryName" value="${categoryName}" type="text"/></td>
            </tr>
            <tr>
              <td class="first-td"><label>* Description: </label></td>
              <td><input name="description" value="${description}" type="text"/></td>
            </tr>
            <tr>
              <td colspan=2><button type="submit">CREATE</button></td>
            </tr>
          </table>
        </form>
        <h3>* Denotes a required field.</h3>
        <p></p>
        <br/>
        <p><b><a href='<spring:url value="/login"/>'>Login Page</a></b></p>
      </div>
    </div>
  </div>
  <script src="${setBackgroundJs}"></script>
</body>
</html>