<html>
	<head>
		<title>Login Page</title>
	</head>
	<body>
	<div class="container">
		<h1>Welcome to the login page!</h1>
		<pre>${errorMessage}<pre>       <%--this is comment....expression $ is used to pass from model to view--%>
		<form method="post">
			Name: <input type="text" name="name">
			Password: <input type="password" name="password">
			<input type="submit">
		</form>
		</div>
</body>
</html>