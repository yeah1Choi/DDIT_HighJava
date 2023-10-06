<%@page import="kr.or.ddit.vo.LprodVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	// Controller(서블릿)이 보낸 데이터 받기
List<LprodVO> list = (List<LprodVO>) request.getAttribute("lprodList");
%>
</head>
<body>
	<h2>Lprod 자료 목록</h2>
	<table border="1">
		<tr>
			<th>LPROD_ID</th>
			<th>LPROD_GU</th>
			<th>LPROD_NM</th>
		</tr>
		<%
			for (LprodVO vo : list) {
		%>
		<tr>
			<td><%=vo.getLprod_id()%></td>
			<td><%=vo.getLprod_gu()%></td>
			<td><%=vo.getLprod_nm()%></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>