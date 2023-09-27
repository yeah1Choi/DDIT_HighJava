<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>forward 방식과 redirect방식 연습</h3>
<br><hr><br>
<form action="<%= request.getContextPath() %>/responseTest01.do" method="post">
forward 방식 : <input type="text" name="username">
<input type="submit" value="확인">
</form>
<br><hr><br>
<form action="<%= request.getContextPath() %>/responseTest02.do" method="post">
redirect 방식 : <input type="text" name="username">
<input type="submit" value="확인">
</form>
</body>
</html>