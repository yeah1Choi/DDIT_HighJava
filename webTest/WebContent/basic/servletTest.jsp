<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
window.onload = function(){
	document.getElementById("getBtn").addEventListener('click',function(){
		location.href = "<%= request.getContextPath() %>/servletTest03.do"
	})
}
</script>
</head>
<body>
<h2>서블릭 요청 연습</h2>
<hr><br>

<h3>GET 방식 요청 방법 1 : 링크 방식</h3>
<a href="<%= request.getContextPath() %>/servletTest03.do">Get방식 요청 1</a>
<br>

<h3>GET 방식 요청 방법 2 : form 태그의 method 속성 이용하기</h3>
<!-- 
	<form>의 속성
	action 속성 : form태그에서 구성하여 서버로 보낸 데이터를 받아서 처리할 문서명을 지정하는 속성
	method 속성 : get or post (*이 속성이 생략되면 기본적으로 Get방식으로 처리)
 -->
<form action="<%= request.getContextPath() %>/servletTest03.do" method="get">
	<input type="submit" value="Get 방식 요청 2">
</form>
<br>

<h3>GET 방식 요청 방법 3 : javaScript의 location.href 속성 이용하기</h3>
<form>
	<input type="button" value="Get 방식 요청 3" id="getBtn">
</form>

<br><hr><br>

<h3>POST 방식 요청 방법 1 : form태그의 method속성을 'post'로 지정한 경우</h3>
<form action="<%= request.getContextPath() %>/servletTest03.do" method="post">
	<input type="submit" value="POST 방식 요청 방법 1">
</form>
</body>
</html>