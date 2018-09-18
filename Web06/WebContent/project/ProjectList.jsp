<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>������Ʈ ���</title>
</head>
<body>
<jsp:include page="/Header.jsp"/>
<h1>������Ʈ ���</h1>
<p><a href="add.do">�ű� ������Ʈ</a></p>
<table border="1">
	<tr>
		<th>��ȣ</th>
		<th>����</th>
		<th>������</th>
		<th>������</th>
		<th>����</th>
		<th></th>
	</tr>
	<c:forEach var="project" items="${projects}">
	<tr>
		<td>${project.no}</td>
		<td><a href="update.do?no=${project.no}">${project.title}</a></td>
		<td>${project.startDate}</td>
		<td>${project.endDate}</td>
		<td>${project.state}</td>
		<td><a href="delete.do?no=${project.no}">[����]</a></td>
	</tr>
	</c:forEach>
</table>
<jsp:include page="/Tail.jsp"></jsp:include>
</body>
</html>