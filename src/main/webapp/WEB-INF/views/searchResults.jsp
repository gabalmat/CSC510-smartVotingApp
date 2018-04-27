<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>SearchResults</title>
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
          <h3>Search criteria:</h3>
          <h3>Query: "${criteriaChoice}"  -  Category: ${categoryChoice}</h3>
          <h3>Found ${searchCount} results</h3>
          <p></p>
          <p></p>
          <table border="1" class="pollsTables">
              <th>No</th>
              <th>User</th>
              <th>Title</th>
              <th>Description</th>
              <th>Created on</th>
              <th>Category</th>
              <th>Link</th>
              <c:forEach var="poll" items="${listPolls}" varStatus="status">
              <tr>
                  <td>${status.index + 1}</td>
                  <td><a href='<spring:url value="/profile/${poll.username}"/>'>${poll.username}</a></td>
                  <td>${poll.title}</td>
                  <th>${poll.description}</th>
                  <td>${poll.created}</td>
                  <td>${poll.categoryName}</td>
                  <th><b><a href='<spring:url value="/poll/${poll.pollId}"/>'>View Poll</a></b></th>
              </tr>
              </c:forEach>   
          </table>
          <p></p>
          <br/>
          <p><b><a href='<spring:url value="/searchPolls"/>'>Another Search</a></b></p>
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
