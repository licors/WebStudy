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
	<jsp:include page="/Header.jsp" />
	<h1>������Ʈ ���</h1>
	<p>
		<a href='add.do'>������Ʈ �߰�</a>
	</p>
	<table border="1">
		<tr>
			<th><c:choose>
					<c:when test="${orderCond == 'PNO_ASC'}">
						<a href="list.do?orderCond=PNO_DESC">��ȣ��</a>
					</c:when>
					<c:when test="${orderCond == 'PNO_DESC'}">
						<a href="list.do?orderCond=PNO_ASC">��ȣ��</a>
					</c:when>
					<c:otherwise>
						<a href="list.do?orderCond=PNO_ASC">��ȣ</a>
					</c:otherwise>
				</c:choose></th>
			<th><c:choose>
					<c:when test="${orderCond == 'TITLE_ASC'}">
						<a href="list.do?orderCond=TITLE_DESC">�����</a>
					</c:when>
					<c:when test="${orderCond == 'TITLE_DESC'}">
						<a href="list.do?orderCond=TITLE_ASC">�����</a>
					</c:when>
					<c:otherwise>
						<a href="list.do?orderCond=TITLE_ASC">����</a>
					</c:otherwise>
				</c:choose></th>
			<th><c:choose>
					<c:when test="${orderCond == 'STARTDATE_ASC'}">
						<a href="list.do?orderCond=STARTDATE_DESC">�����ϡ�</a>
					</c:when>
					<c:when test="${orderCond == 'STARTDATE_DESC'}">
						<a href="list.do?orderCond=STARTDATE_ASC">�����ϡ�</a>
					</c:when>
					<c:otherwise>
						<a href="list.do?orderCond=STARTDATE_ASC">������</a>
					</c:otherwise>
				</c:choose></th>
			<th><c:choose>
					<c:when test="${orderCond == 'ENDDATE_ASC'}">
						<a href="list.do?orderCond=ENDDATE_DESC">�����ϡ�</a>
					</c:when>
					<c:when test="${orderCond == 'ENDDATE_DESC'}">
						<a href="list.do?orderCond=ENDDATE_ASC">�����ϡ�</a>
					</c:when>
					<c:otherwise>
						<a href="list.do?orderCond=ENDDATE_ASC">������</a>
					</c:otherwise>
				</c:choose></th>
			<th><c:choose>
					<c:when test="${orderCond == 'STATE_ASC'}">
						<a href="list.do?orderCond=STATE_DESC">���¡�</a>
					</c:when>
					<c:when test="${orderCond == 'STATE_DESC'}">
						<a href="list.do?orderCond=STATE_ASC">���¡�</a>
					</c:when>
					<c:otherwise>
						<a href="list.do?orderCond=STATE_ASC">����</a>
					</c:otherwise>
				</c:choose></th>
			<th></th>
		</tr>
		<c:forEach var="project" items="${projects}">
			<tr>
				<td>${project.no}</td>
				<td><a href='update.do?no=${project.no}'>${project.title}</a></td>
				<td>${project.startDate}</td>
				<td>${project.endDate}</td>
				<td>${project.state}</td>
				<td><a href='delete.do?no=${project.no}'>[����]</a></td>
			</tr>
		</c:forEach>
	</table>
	<jsp:include page="/Tail.jsp" />
</body>
</html>