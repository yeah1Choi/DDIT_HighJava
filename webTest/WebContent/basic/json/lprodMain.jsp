<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#btn').on('click',function(){
		$.ajax({
			url : "<%=request.getContextPath()%>/lprodList.do",
			type : "post",
			// data : 서버로 보낼 파라미터(데이터)가 없을 경우 생략가능
			success : function(data){
				code = "<table border='1'>";
				code += "<tr><th>LPROD_ID</th><th>LPROD_GU</th><th>LPROD_NM</th></tr>";
				$.each(data,function(i,v){
					code += "<tr><td>"+v.lprod_id+"</td><td>"+v.lprod_gu+"</td><td>"+v.lprod_nm+"</td></tr>";
				})		
				code += "</table>";
				
				$('#result').html(code);
			},
			dataType : 'json'
		})
	})
	// 동기 , MVC 형식, view를 jsp로 지정
	$('#btn2').on('click',function(){
		location.href = "<%=request.getContextPath()%>/lprodList2.do"
	})
})
</script>
</head>
<body>
<input type="button" value="Lprod자료 가져오기(AJAX)" id="btn">
<input type="button" value="Lprod자료 가져오기(NON-AJAX)" id="btn2">
<h2>Lprod 자료 목록</h2>
<div id="result"></div>
</body>
</html>