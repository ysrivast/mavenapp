<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>

	<p style="color: red;">${message}</p>
	<form:form action="register" method="post" modelAttribute="user">
		<table border="0">
			<tr>
				<td>User Name</td>
				<td><input type="text" name="userName" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="text" name="password" /></td>
			</tr>
			<tr>
				<td>Male</td>
				<td><input type="radio" name="gender" value="M"/></td>
			</tr>
			<tr>
				<td>Female</td>
				<td><input type="radio" name="gender" value="F"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" align="left" /> <a></a></td>
			</tr>
			<tr>
				<td colspan="4"><input type="reset" value="reset" align="right" /> <a></a></td>
			</tr>
		</table>


	</form:form>

	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>