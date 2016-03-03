<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册用户</title>
<style>
.errorClass {
	color: red
}
</style>
</head>
<body>
	<form:form modelAttribute="user"
		action="/explore-springmvc/user/handle91.html">
		<form:errors path="*" />
		<table>
			<tr>
				<td>用户名：</td>
				<td><form:errors path="userName" cssClass="errprClass" /> <form:input
						path="userName" /></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><form:errors path="password" cssClass="errprClass" /> <form:input
						path="password" /></td>
			</tr>
			<tr>
				<td>姓名：</td>
				<td><form:errors path="realName" cssClass="errprClass" /> <form:input
						path="realName" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="submit" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>