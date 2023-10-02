<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session 연습</title>
</head>
<body>
<a href="<%= request.getContextPath() %>/sessionAdd.do">Session 저장하기</a> <br>
<a href="<%= request.getContextPath() %>/sessionRead.do">Session 확인하기</a> <br>
<a href="<%= request.getContextPath() %>/sessionDelete.do">Session 삭제하기</a>
</body>
</html>