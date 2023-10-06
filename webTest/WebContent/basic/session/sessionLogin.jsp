<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	// JSP문서에서 HttpSession객체는 'session'이라는 이름으로 이미 생성되어있다.
	String loginId = (String) session.getAttribute("loginId");
%>
</head>
<body>
	<%
		if (loginId == null) { // 로그인이 안되었을 때
	%>
	<form action="<%=request.getContextPath()%>/sessionLogin.do"
		method="post">
		<table border="1">
			<tr>
				<th><label for="id">ID : </label></th>
				<td><input type="text" placeholder="ID를 입력하세요." name="id"
					id="id"></td>
			</tr>
			<tr>
				<th><label for="pass">PASS : </label></th>
				<td><input type="password" placeholder="PASSWORD를 입력하세요."
					name="pass" id="pass"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Login"></td>
			</tr>
		</table>
	</form>
	<%
		} else { // 로그인이 되었을 때
	%>
	<h2><%=loginId%>님 반갑습니다.
	</h2>
	<a href="<%=request.getContextPath()%>/sessionLogout.do">로그아웃</a>
	<%
		}
	%>
</body>
</html>