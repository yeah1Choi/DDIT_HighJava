<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 입력 페이지</title>
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
	<h2>회원 정보 입력 폼</h2>
	<table border="1">
		<tr>
			<th>회원 ID</th>
			<td><input type="text" id="id"> <input type="button"
				id="idCheck" value="중복확인"></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" id="pass"></td>
		</tr>
		<tr>
			<th>비밀번호 확인</th>
			<td><input type="password" id="passCheck"></td>
		</tr>
		<tr>
			<th>회원이름</th>
			<td><input type="text" id="name"></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td><input type="text" id="tel"></td>
		</tr>
		<tr>
			<th>회원주소</th>
			<td><input type="text" id="add"></td>
		</tr>
		<tr>
			<th>프로필 사진</th>
			<td><input type="button" id="filebtn" value="파일선택"> <span
				id="fileName"></span></td>
		</tr>
		<tr>
			<td colspan="2">
			<input type="submit" id="idCheck" value="저장">
			<input type="reset" id="idCheck" value="취소">
			<a href="<%=request.getContextPath()%>/basic/member/memList.jsp">회원 목록</a>
			</td>
		</tr>
	</table>
</body>
</html>