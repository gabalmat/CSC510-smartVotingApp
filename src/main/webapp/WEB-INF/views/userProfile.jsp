<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>UserProfile</title>
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

      <div class="message poll-content">
        <div class="text">
          <h2 class="margin-bottom-3">${user.firstName} ${user.lastName}</h2>
          <h3 class="margin-bottom-3">Email: ${user.email}</h3>
          <h3 class="margin-bottom-3">Username: ${user.username}</h3>
          <h3 class="margin-bottom-3">Member since: ${user.created}</h3>
          <p></p>
          <br/>
          <img src="${pageContext.request.contextPath}/resources/images/blank-profile-picture.png" width="256" height="256">

          <h3>${user.username} has created ${pollsCount} polls:</h3>

          <table border="1">
              <th>No</th>
              <th>Title</th>
              <th>Description</th>
              <th>Link</th>
              <c:forEach var="poll" items="${listPolls}" varStatus="status">
              <tr>
                  <td>${status.index + 1}</td>
                  <td>${poll.title}</td>
                  <td>${poll.description}</td>
                  <th><b><a href='<spring:url value="/poll/${poll.pollId}"/>'>View Poll</a></b></th>
              </tr>
              </c:forEach>             
          </table>
          <p></p>
          <br/>
          <p><b><a href='<spring:url value="/"/>'>Home Page</a></b></p>
        </div>
      </div>
    </div>

    <footer>
      <p class="footer-contents">Designed and developed by NCSU CSC 510 Team A</p>
    </footer>

    <script src="${setBackgroundJs}"></script>
  </body>
</html>
