<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>File Upload 연습</h2>
<form action="<%=request.getContextPath()%>/fileUpload.do" method="post" enctype="multipart/form-data"> 
<!-- 파일 업로드는 post만 가능 --><!-- "multipart": 서버로 제출되는 데이터가 여러 파트로 ? -->
작성자 이름 : <input type="text" name="username"> <br><br>
한 개 파일 선택 : <input type="file" name="upfile1"> <br><br>
여러 개 파일 선택 : <input type="file" name="upfile2" multiple="multiple"> <br><br>
<input type="submit" value="전 송">
</form>
</body>
</html>