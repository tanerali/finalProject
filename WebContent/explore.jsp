<%@page import="model.Post"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Airbnb</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8">
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
<body>


	<%@ include file="WEB-INF/jsp/header.jsp"%>

	<%
	ArrayList<Post> posts = (ArrayList)request.getAttribute("posts");
	%>

	<div class="container">
		<h2>Image Gallery</h2>
		<p>The .thumbnail class can be used to display an image gallery.</p>
		<p>The .caption class adds proper padding and a dark grey color to
			text inside thumbnails.</p>
		<p>Click on the images to enlarge them.</p>
		<div class="row">
			<%
			if (posts != null) {
				for (Post post: posts) { %>

					<div class="col-md-4">
						<div class="thumbnail">
							<a href="/w3images/lights.jpg" target="_blank"> <img
								src="/w3images/lights.jpg" alt="Lights" style="width: 100%">
								<div class="caption">
									<p><%= post.getTitle() %></p>
								</div>
							</a>
						</div>
					</div>

			<% }
			}
			%>
			
			
		</div>
	</div>



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
			if (req.readyState == 4 && req.status == 200
					&& req.responseText != "[]") {
				closeSearch();
				var jsonSearch = eval('(' + req.responseText + ')');
				document.getElementById("top").className = "n";
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

</body>
</html>
