<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>SearchPolls</title>
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
        <div class="text full-width">
          <h2>Type the search criteria below to search for a poll:</h2>

          <form:form action="/searchResults" method="POST" modelAttribute="category">
            <table class="search-form">
              <tr>
                <td><b>Search Criteria: </b></td>
                <td><input type="text" name="criteria" value="${criteria}" ></td>
              </tr>
              <tr class="cat-selection">
                <td class="first-td"><form:label path="id">Category: </form:label></td>
                <td>
                  <form:select path="id" class="cat-dropdown">
                    <form:options items="${categoryList}" itemValue="id" itemLabel="name" />
                  </form:select>
                </td>
              </tr>
              <tr>
                <td colspan=2><button type="submit">Search</button></td>
              </tr>
            </table>
          </form:form>
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
