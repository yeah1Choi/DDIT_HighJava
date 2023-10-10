<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%

%>
<style type="text/css">
table{
	border: 1px solid blue;
}
th, td {
	border: 1px solid blue;
	padding: 5px;
}
</style>
</head>
<body>
	<h2>회원 정보 상세보기</h2>
	<table border="1">
		<tr>
			<td colspan="2" class="photo"></td>
		</tr>
		<tr>
			<td>회원ID</td>
			<td class="id"></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td class="pass"></td>
		</tr>
		<tr>
			<td>회원이름</td>
			<td class="name"></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td class="tel"></td>
		</tr>
		<tr>
			<td>회원주소</td>
			<td class="addr"></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정" id="updateInfo">
				<input type="button" value="삭제" id="deleteInfo">
				<a href="<%=request.getContextPath()%>/basic/member/memList.jsp">회원 목록</a>
			</td>
		</tr>

	</table>
</body>
</html>