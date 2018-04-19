<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>Airbnb</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8">
<script type="application/x-javascript">
</script>
<!-- bootstrap-css -->
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
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1000);
		});
	});
</script>
<body>
	<!-- banner -->
	<div class="banner1">
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
								<div class="collapse navbar-collapse"
									id="bs-example-navbar-collapse-1">
									<ul class="nav navbar-nav">

										<div id="myOverlay" class="overlay">
											<span class="closebtn" onclick="closeSearch()"
												title="Close Overlay">×</span>
											<div class="overlay-content">
												<input type="text" id="search" name="search">
												<button type="button" onclick="search();">Search</button>
											</div>
										</div>
										<li><a id="openBtn" class="active"
											onclick="openSearch();">Search</a></li>

										<li><a href="host.html">Host</a></li>
										<li><a href="explore.html" onclick="explore(this);"
											rel="explore.html">Explore</a></li>
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
		<div class="w3layouts-banner-slider">
			<div class="container">
				<div class="slider">
					<div class="callbacks_container">
						<ul class="rslides callbacks callbacks1" id="slider4">
							<table id="search-table"></table>
						</ul>
					</div>
				</div>
			</div>
		</div>

	</div>
	<!-- //banner -->

	<!-- welcome -->
	<!-- //services -->
	<!-- copyright -->
	<!-- //copyright -->
	<script src="js/responsiveslides.min.js"></script>
	<script src="js/SmoothScroll.min.js"></script>
	<script type="text/javascript" src="js/move-top.js"></script>
	<script type="text/javascript" src="js/easing.js"></script>

	<!-- here stars scrolling icon -->
	<script type="text/javascript">
		$(document).ready(function() {
			/*
				var defaults = {
				containerID: 'toTop', // fading element id
				containerHoverID: 'toTopHover', // fading element hover id
				scrollSpeed: 1200,
				easingType: 'linear'
				};
			 */

			$().UItoTop({
				easingType : 'easeOutQuart'
			});

		});
	</script>



	<script>
		var req = new XMLHttpRequest();
		function openSearch() {
			document.getElementById("myOverlay").style.display = "block";
		}

		function closeSearch() {
			document.getElementById("myOverlay").style.display = "none";
		}
		function search() {
			//true means - async requests
			req.open("Get", "search?search="
					+ document.getElementById("search").value, true);
			req.onreadystatechange = proccesSearch;
			req.send(null);
		}
		function proccesSearch() {
			if (req.readyState == 4 && req.status == 200) {
				closeSearch();
				var jsonSearch = eval('(' + req.responseText + ')');
				var table = document.getElementById("search-table");
				table.innerHTML = "";
				var headRow = table.insertRow(0);
				var headCell = headRow.insertCell(0);
				var results = jsonSearch;
				var i = 0;
				while (i < results.length) {
					row = table.insertRow(i + 1);
					cell = row.insertCell(0);
					cell.innerHTML = results[i++].title;
				}

			}

		}
	</script>
	<!-- //here ends scrolling icon -->

	<script src="js/jarallax.js"></script>
	<script type="text/javascript">
		/* init Jarallax */
		$('.jarallax').jarallax({
			speed : 0.5,
			imgWidth : 1366,
			imgHeight : 768
		})
	</script>
</body>
</html>