<%@page import="model.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.Post"%>
<%@page import="model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	Post currPost = (Post) request.getAttribute("post");
	User postUser = (User) request.getAttribute("user");
%>
<title><%=currPost.getTitle()%></title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<!--// bootstrap-css -->
<!-- css -->
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<!--// css -->
<!-- font-awesome icons -->
<link href="css/font-awesome.css" rel="stylesheet">
<!-- //font-awesome icons -->
<!-- font -->
<link
	href="//fonts.googleapis.com/css?family=Crimson+Text:400,400i,600,600i,700,700i"
	rel="stylesheet">
<link
	href="//fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<link
	href='//fonts.googleapis.com/css?family=Roboto+Condensed:400,700italic,700,400italic,300italic,300'
	rel='stylesheet' type='text/css'>
<!-- //font -->
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/bootstrap.js"></script>
</head>
<body>

		<%@ include file="header.jsp"%>

		<!-- single -->
		<div class="single">
			<div class="container">
				<div class="agileits-single-img">
					<!--<img src="images/s1.jpg" alt="" /-->
					<img id="placeImage" src="getThumbnail?id=<%= currPost.getPostID() %>">
					<h4><%=currPost.getTitle()%></h4>
					<div class="agileinfo-single-icons">
						<ul>
							<li><a href="profile?id=<%= postUser.getUserID() %>"><i class="fa fa-user" aria-hidden="true"></i>
									<span>Host: <%=postUser.getFirstName()%> <%=postUser.getLastName()%></span></a></li>
							<li><i class="fa fa-calendar" aria-hidden="true"></i><span>Date of posting: <%=currPost.getDateOfPosting().toString()%></span></li>
							<li><a href="#"><i class="fa fa-heart"
									aria-hidden="true"></i><span><%=currPost.getRating()%>/10
										Rating</span></a></li>
						</ul>
					</div>
					<h3>Description</h3>
					<p><%=currPost.getDescription()%></p>
					<p>
						Price: <b><%=currPost.getPrice()%></b>
					</p>
				</div>
				
				<%
				
				ArrayList<Comment> comments = new ArrayList();
				if(request.getAttribute("comments") != null) {
					comments = (ArrayList<Comment>)request.getAttribute("comments");
				}
				%>
				
				<!-- comments -->
				<div class="agileits_three_comments">
						<h3>Comments</h3>
				<% for(Comment comment: comments) { %>
					
						<div class="agileits_three_comment_grid">
							<div class="agileits_tom">
								<a href="profile?id=<%= comment.getUserID() %>"><img src="getPic?id=<%= comment.getUserID() %>" class="img-responsive"></a>
							</div>
							<div class="agileits_tom_right">
								<div class="hardy">
									<a href="profile?id=<%= comment.getUserID() %>"><h4><%= comment.getFullName() %></h4></a>
									<p><%= comment.getDate() %></p>
								</div>
								<div class="clearfix"></div>
								<p class="lorem"><%= comment.getContent() %></p>
							</div>
							<div class="clearfix"></div>
						</div>
					
				<% } %>
				</div>
				
				<!-- //comments -->
				
				
				<!-- leave-comments -->
				<div class="w3_leave_comment">
					<h3>Leave your comment</h3>
					<form action="#" method="post">
						<textarea placeholder="Message" name="Message" required=""></textarea>
						<input type="submit" value="Send">
					</form>
				</div>
				<!-- //leave-comments -->
				
				
			</div>
		</div>
		
		<script type="text/javascript">
			function upload() {
		
				req.open("Post", "upload");
				req.onreadystatechange = proccessUpload;
		
				var formData = new FormData();
				formData.append("file",
						document.getElementById("myFileField").files[0]);
				formData.append("title", document.getElementById("title").value);
				formData.append("description", document
						.getElementById("description").value);
				formData.append("price", document.getElementById("price").value);
				formData.append("type", document.getElementById("type").value);
				req.send(formData);
			}
			function proccessUpload() {
				alert("Arede");
			}
		</script>
</body>
</html>