<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring Application</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>

	<h1>
		Hello
		<user> ${user.userName}</user>
		! welcome to stock webstie
	</h1>



	<form action="logout" method="post">
		<input type="submit" value="Logout" />
	</form>

	<h1>SOP : After login</h1>

	<table border="black" bgcolor="yellow">
		<tr>
			<td>
				<h1>
					<user> ${user.userName}</user>
				</h1>
			</td>
		</tr>
		<tr>
			<td><h1>
					<user> ${user.gender}</user>
				</h1></td>
		</tr>
	</table>



	<p>Test application</p>

	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>