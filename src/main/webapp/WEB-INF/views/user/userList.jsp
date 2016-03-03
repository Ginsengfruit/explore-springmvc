<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="website.title" /></title>
<style>
.errorClass {
	color: red
}
</style>
</head>
<body>
	<fmt:message key="user.userList.title" />
	<table>
		<c:forEach items="${userList }" var="user">
			<tr>
				<td><a
					href="<c:url value="/user/showUser/${user.userName }.html"/>">
						${user.userName } </a></td>
				<td>${user.realName }</td>
				<td><fmt:formatDate value="${topic.createDate }"
						pattern="yyyy-MM-dd" />
			</tr>
		</c:forEach>

	</table>
</body>
</html>