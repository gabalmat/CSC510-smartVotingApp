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
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"
  			integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
  			crossorigin="anonymous">
	</script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>
	
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
                <tr class="line-height">
                        <td><p><a href='/createComment?parentId=0&pollId=${myPollId}'>Create Root Comment</a><p></td>
                </tr>
    	        <c:forEach var="singleTree" items="${htmlTree}" varStatus="status">
                    ${singleTree}
                </c:forEach>   
	           
<!--                 <tr class="line-height"> -->
<!--                         <td><h3>Discussion Table:</h3></td> -->
<!--                 </tr> -->
<!--                 <table border="1"> -->
<!--                     <th>No</th> -->
<!--                     <th>Username</th> -->
<!--                     <th>Content</th> -->
<!--                     <th>Parent</th> -->
<!--                     <th>Time Posted</th> -->
<%--                     <c:forEach var="comment" items="${listComments}" varStatus="status"> --%>
<!--                     <tr> -->
<%--                         <td>${status.index + 1}</td> --%>
<%--                         <td>${comment.userId}</td> --%>
<%--                         <th>${comment.content}</th> --%>
<%--                         <th>${comment.parentId}</th> --%>
<%--                         <th>${comment.created}</th> --%>
<!--                     </tr> -->
<%--                     </c:forEach> --%>
<!--                 </table> -->
                <div style="text-align:center">
                	<table class="discussion-tree">
	                	<tr>
		                    <td><h3><b>Discussion Tree:</b></h3></td>
		                </tr>
	                	<tr>
	                		<td><div id="commentTree">${htmlTree}</div></td>
	                	</tr>
	                </table>
                </div>
	    	</div>
			
            <p></p>
            <br/>
            <p><b><a href='<spring:url value="/"/>'>Home Page</a></b></p>
        
	    </div>
	</div>
	
	<script src="${setBackgroundJs}"></script>
	<script type="text/javascript">
		//once the DOM has been created, initialize the tree
		$(function() {
			$.jstree.defaults.core.themes.variant = "large";
			$('#commentTree').jstree();
		});
	</script>
</body>
</html>