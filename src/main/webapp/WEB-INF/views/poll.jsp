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

	    </div>
	</div>
	
	<script src="${setBackgroundJs}"></script>
	<script type="text/javascript">
		//once the DOM has been created, initialize the tree
		$(function() {
			$('#commentTree').jstree({
				"core": {
					"themes": {
						"variant": "large"
					},
					"check_callback" : true
				},
				"plugins" : ["themes","html_data","ui","contextmenu"],
				"contextmenu": {
					"select_node": false,
					"items": function($node) {
						return {
							"Create": {
								"label": "Add Reply",
								"icon": false,
								"separator_after": true,
								"action": function(data) {
									var inst = $.jstree.reference(data.reference),
							        	obj = inst.get_node(data.reference),
							        	parentId = obj.data.id,
							        	pollId = obj.data.pollid;
									console.log(obj);
									window.location.href = "/createComment?parentId=" + parentId + "&pollId=" + pollId;
								}
							},
							"Mark": {
								"label": "Mark as Significant",
								"icon": false,
								"action": function(obj) {
									console.log("Reply  ", obj);
								}
							}
						}
					}
				}
			});
			
			$('#commentTree').on('select_node.jstree', function(e, data) {
				window.location = data.node.a_attr.href;
			});
		});
	</script>

	<p></p>
	<br/>
	<p style="text-align:center;"><b><a style="cursor:pointer; text-decoration:underline; " href='<spring:url value="/"/>'>Home Page</a></b></p>
	<footer>
      <p class="footer-contents">Designed and developed by NCSU CSC 510 Team A</p>
    </footer>
</body>
</html>