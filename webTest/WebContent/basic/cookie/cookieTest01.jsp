<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cookie 연습</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/cookieAdd.do">Cookie 정보 저장하기</a><br>
<a href="<%=request.getContextPath()%>/cookieRead.do">Cookie 정보 읽어오기</a><br>
<a href="<%=request.getContextPath()%>/cookieDelete.do">Cookie 정보 삭제하기</a><br>
</body>
</html>