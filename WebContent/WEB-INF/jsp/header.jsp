<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="header">
	<div class="container">
		<div class="header-left">
			<div class="w3layouts-logo">
				<h1>
					<a href="index.jsp"><span>re </span> AIRBNB</a>
				</h1>
			</div>
		</div>
		<div class="header-right">
			<div class="w3-header-bottom">
				<div class="top-nav">
					<nav class="navbar navbar-default">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse" 
							data-target="#bs-example-navbar-collapse-1">
							
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
					</div>
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse in navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">

							<div id="myOverlay" class="overlay">
								<span class="closebtn" onclick="closeSearch()"
									title="Close Overlay">Ã</span>
								<div class="overlay-content">
									<form action="index.html">
										<input type="text" placeholder="Search.." name="search">
										<button type="submit">
											<i class="fa fa-search"></i>
										</button>
									</form>
								</div>
							</div>
							<li><a id="openBtn" class="active" onclick="openSearch()">Search</a></li>
							<li><a href="host.html">Host</a></li>
							<li><a href="explore.html">Explore</a></li>
							<%
								if (session.getAttribute("user") == null) {
							%>
							<li><a href="login.jsp">Login</a></li>
							<li><a href="register.jsp">Register</a></li>
							<%
								} else {
							%>
							<li><a href="profile">Profile</a></li>
							<li><a href="logout">Logout</a></li>
							<%
								}
							%>

						</ul>
						<div class="clearfix"></div>
					</div>
					</nav>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
</div>