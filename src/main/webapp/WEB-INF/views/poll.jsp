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

                <c:if test="${not empty addCommentSuccess}">
                    ${addCommentSuccess}
                    <br/>
                    <p><b><a href='<spring:url value="/poll/${poll.pollId}"/>'>Poll #${poll.pollId}</a></b></p>
                </c:if>

				<table width="100%" class="display-poll">
					<tr class="line-height">
                    	<td><b>Category: </b> ${category.name} - ${category.description}</td>
                    </tr>
					<tr>
						<td>${poll.description}</td>
                    </tr>
				</table>
				
	            <c:choose>
	            	<c:when test="${not empty userPollOption}">
		            	<table width="100%" class="display-poll vote-options-tbl">
			            	<tr>
		                    	<td><h3>Submitted Vote:</h3></td>
		                    </tr>
			            </table>
		            	<table width="100%" class="display-poll">
		            		<tr>
		            			<td>
		            				<input type="radio" checked />
		            				<label>${userPollOption.description}</label>
		            			</td>
		            		</tr>
		            	</table>
		            </c:when>
		            <c:otherwise>
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
		            </c:otherwise>
	            </c:choose>

                <tr class="line-height">
                        <td><h3>Discussion:</h3></td>
                </tr>
    	        <c:forEach var="singleTree" items="${htmlTree}" varStatus="status">
                    ${singleTree}
                </c:forEach>   
            
	    	</div>

            <p></p>
            <br/>
            <p><b><a href='<spring:url value="/"/>'>Home Page</a></b></p>
        
	    </div>
	</div>
	
	<script src="${setBackgroundJs}"></script>
</body>
</html>