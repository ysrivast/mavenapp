<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<script>
	function validateForm() {
		if (document.forms.userName.value == "") {
			alert("Username should not be blacnk")
			document.forms.userName.focus();
			return false;
		}

		if (document.forms.password.value == "") {
			alert("Password should not be blacnk")
			document.forms.Password.focus();
			return false;
		}
	}
</script>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>
	${error}
	<form:form action="login" method="post" modelAttribute="user" onsubmit="return validateForm();">
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
				<td>Remember me</td>
				<td><input type="checkbox" name="rememberMe" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" /> <a></a></td>
			</tr>
		</table>


	</form:form>

	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>