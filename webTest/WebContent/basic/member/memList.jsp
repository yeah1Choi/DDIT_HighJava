<%@page import="kr.or.ddit.vo.MymemberVO"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록 페이지</title>
<%
	// 서블릿(controller)에서 보내온 자료 받기
List<MymemberVO> memList = (List<MymemberVO>) request.getAttribute("memList");
%>
<style type="text/css">
th, td {
padding: 5px;
}
.right {
	width: 100%;
}
</style>
</head>
<body>
	<h2>회원 목록 보기</h2>
	<table border="1" style="border: 1px solid blue">
		<tr>
			<td colspan="5" class="right">
				<a href="<%=request.getContextPath()%>/basic/member/createMem.jsp">회원추가</a>
			</td>
		</tr>
		<thead>
			<tr>
				<th>ID</th>
				<th>비밀번호</th>
				<th>이 름</th>
				<th>전 화</th>
				<th>주 소</th>
			</tr>
		</thead>
		<tbody>
			<%
				if (memList == null || memList.size() == 0) {
			%>
			<tr>
				<td colspan="5">파일 목록이 하나도 없습니다</td>
			</tr>
			<%
				} else {
			for (MymemberVO mvo : memList) {
			%>
			<tr>
				<td><a href="<%=request.getContextPath()%>/basic/member/memDetail.jsp"><%=mvo.getMem_id()%></a></td>
				<td><%=mvo.getMem_pass()%></td>
				<td><%=mvo.getMem_name()%></td>
				<td><%=mvo.getMem_tel()%></td>
				<td><%=mvo.getMem_addr()%></td>
			</tr>
			<%
				}
			}
			%>
		</tbody>
	</table>
</body>
</html>