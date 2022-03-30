<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Signup</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<div class="container">

		<h2 class="text-justify">Signup</h2>

		<form action="/add">

			<div class="form-group">
				<label for="username">Username:</label> <input type="text"
					class="form-control" id="username" placeholder="Enter email"
					name="username" required>
			</div>

			<div class="form-group">
				<label for="email">Email:</label> <input type="email"
					class="form-control" id="email" placeholder="Enter email"
					name="email" required>
			</div>

			<div class="form-group">
				<label for="pwd">Password:</label> <input type="password"
					class="form-control" id="pwd" placeholder="Enter password"
					name="password" required>
			</div>

			<div class="form-group">
				<label for="confirmpassword">Confirm Password:</label> <input
					type="password" class="form-control" id="pwd"
					placeholder="Re-enter password" name="confirmpassword" required>
			</div>

			<button type="submit" class="btn btn-primary">Signup</button>
		</form>
	</div>
</body>
</html>