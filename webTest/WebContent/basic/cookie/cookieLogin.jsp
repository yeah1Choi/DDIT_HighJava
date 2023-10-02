<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	// 쿠키에 저장된 ID값을 구한다
String cookieUserId = "";
String chkBox = "";

Cookie[] cookieArr = request.getCookies();
if (cookieArr != null) {
	for (Cookie cookie : cookieArr) {
		if ("USERID".equals(cookie.getName())) { // 원하는 쿠키이름을 찾는다
			cookieUserId = cookie.getValue(); // 쿠키값을 저장
			chkBox = "checked";
		}
	}
}
%>

<style type="text/css">
form {
	margin: 0 auto;
	border: 1px solid #ccc;
	width: 250px;
	padding: 20px 40px;
}

label.w {
	display: inline-block;
	width: 50px;
	line-height: 25px;
}

label {
	font-weight: bold;
}

#checkbox {
	margin: 15px 0;
}

#btn {
	display: block;
	margin: 0 auto;
}
</style>
</head>
<body>
	<div>
		<form action="<%=request.getContextPath()%>/cookieLoginServlet.do" method="post">
			<label class="w" for="id">ID : </label> 
			<input type="text" placeholder="ID 입력하세요" id="id" name="id" value="<%=cookieUserId%>"> <br> 
			<label class="w" for="pass">PASS : </label> 
			<input type="password" placeholder="PASS 입력하세요" id="pass" name="pass"><br>
			<input type="checkbox" id="checkbox" name="chkid"> 
			<label for="checkbox">ID 기억하기</label><br> <input type="submit" value="Login" id="btn">
		</form>
	</div>
</body>
</html>