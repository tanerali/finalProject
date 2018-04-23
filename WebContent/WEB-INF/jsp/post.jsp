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

	<!-- banner -->
	<div class="banner">

		<%@ include file="header.jsp"%>

		<!-- //banner -->
		<!-- single -->
		<div class="single">
			<div class="container">
				<div class="agileits-single-img">
					<!--<img src="images/s1.jpg" alt="" /-->
					- <img id="placeImage" src="images/place.jpg">
					<h4><%=currPost.getTitle()%></h4>
					<div class="agileinfo-single-icons">
						<ul>
							<li><a href="#"><i class="fa fa-user" aria-hidden="true"></i>
									<span><%=postUser.getFirstName()%> <%=postUser.getLastName()%></span></a></li>
							<li><i class="fa fa-calendar" aria-hidden="true"></i><span><%=currPost.getDateOfPosting().toString()%></span></li>
							<li><a href="#"><i class="fa fa-heart"
									aria-hidden="true"></i><span><%=currPost.getRating()%>/10
										Rating</span></a></li>
						</ul>
					</div>
					<p><%=currPost.getDescription()%></p>
					<p>
						Price: <b><%=currPost.getPrice()%></b>
					</p>
				</div>
				<!-- comments -->
				<div class="agileits_three_comments">
					<h3>Comments</h3>
					<div class="agileits_three_comment_grid">
						<div class="agileits_tom">
							<img src="images/t2.jpg" alt=" " class="img-responsive">
						</div>
						<div class="agileits_tom_right">
							<div class="hardy">
								<h4>David Son</h4>
								<p>21 June 2016</p>
							</div>
							<div class="reply">
								<a href="#">Reply</a>
							</div>
							<div class="clearfix"></div>
							<p class="lorem">There are many variations of passages of
								Lorem Ipsum available, but the majority have suffered alteration
								in some form, by injected humour, or randomised words which
								don't.</p>
						</div>
						<div class="clearfix"></div>
					</div>
					<div
						class="agileits_three_comment_grid agileits_three_comment_grid1">
						<div class="agileits_tom">
							<img src="images/t3.jpg" alt=" " class="img-responsive">
						</div>
						<div class="agileits_tom_right">
							<div class="hardy">
								<h4>Shane Watson</h4>
								<p>21 June 2018</p>
							</div>
							<div class="reply">
								<a href="#">Reply</a>
							</div>
							<div class="clearfix"></div>
							<p class="lorem">There are many variations of passages of
								Lorem Ipsum available, but the majority have suffered alteration
								in some form, by injected humour, or randomised words which
								don't.</p>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="agileits_three_comment_grid">
						<div class="agileits_tom">
							<img src="images/t4.jpg" alt=" " class="img-responsive">
						</div>
						<div class="agileits_tom_right">
							<div class="hardy">
								<h4>Steve Smith</h4>
								<p>21 June 2018</p>
							</div>
							<div class="reply">
								<a href="#">Reply</a>
							</div>
							<div class="clearfix"></div>
							<p class="lorem">There are many variations of passages of
								lorem ipsum available, but the majority have suffered alteration
								in some form, by injected humour, or randomised words which
								don't.</p>
						</div>
						<div class="clearfix"></div>
					</div>
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
</body>
</html>