<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/updateMember.do" method="post" >
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
			<td class="pass">
				<input type="password" id="updatePass">
			</td>
		</tr>
		<tr>
			<td>회원이름</td>
			<td class="name">
				<input type="text" id="updateName">
			</td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td class="tel">
				<input type="text" id="updateTel">
			</td>
		</tr>
		<tr>
			<td>회원주소</td>
			<td class="addr">
				<input type="text" id="updateAdd">
			</td>
		</tr>
		<tr>
			<td>프로필 사진</td>
			<td class="photo2">
				<input type="file" id="updatePhoto">
				<span id="fileinfo"></span>
			</td>
		</tr>
		<tr>
			<td  colspan="2">
				<input type="submit" value="저장" id="saveInfo">
				<input type="reset" value="취소" id="deleteInfo">
				<a href="<%=request.getContextPath()%>/basic/member/memList.jsp">회원 목록</a>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>