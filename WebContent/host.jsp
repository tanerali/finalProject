<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Airbnb</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8">
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />

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
	<!-- banner -->
	<div id="top" class="banner">
		
		<%@ include file="WEB-INF/jsp/header.jsp" %>
		
		<div class="w3layouts-banner-slider">
			<div class="container">
				<div class="slider">
					<div class="callbacks_container">
						<table id="search-table"></table>
						<div class="inputPost">
							<table>
								<tr>
									<td><input type="text" name="title" id="title"
										placeholder="Title"></td>
								</tr>
								<tr>
									<td><textarea name="description" id="description"
											placeholder="description"></textarea></td>
								</tr>

								<tr>
									<td><input type="number" name="price" id="price"
										placeholder="Price"></td>
								</tr>

								<tr>
									<td><select name="type" id="type">
											<option id="opt1" value="Hotel">Hotel</option>
											<option id="opt2" value="Hotel">Apartment</option>
											<option id="opt3" value="Hotel">House</option>
											<option id="opt4" value="Hotel">Cottage</option>
									</select></td>
								</tr>
								<tr>
									<td>
										<form method="post" action="upload"
											enctype="multipart/form-data">
											<input type="file" id="myFileField" accept="image/*"
												name="file"><br>
										</form>
									</td>
								</tr>
								<tr>
									<td>
										<button type="button" onclick="upload();">Upload</button>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
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
	