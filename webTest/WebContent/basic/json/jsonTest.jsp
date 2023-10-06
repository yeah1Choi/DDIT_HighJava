<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">

$(function(){
	// 문자열처리
	$("#strBtn").on('click',function(){
		$.ajax({
			// 요청
			url : "<%=request.getContextPath()%>/json/jsonTest.do",
			type : "post", // json의 경우 post,get 차이가 다르지 않음
			data : "choice=string", // choice는 파라미터변수, string은 반환값타입
			
			// 응답
			success : function(data){
				let htmlCode = "<h3>문자열 응답 결과</h3>";
				htmlCode += data;
				
				$('#result').html(htmlCode);
			},
			dataType : 'json' // 위의 응답시 사용하는 파라미터 'data'의 type
			});
		});

	// 배열처리
	$("#arrayBtn").on('click',function(){
		$.ajax({
			// 요청
			url : "<%=request.getContextPath()%>/json/jsonTest.do",
				type : "post", // json의 경우 post,get 차이가 다르지 않음
				data : "choice=array", // choice는 파라미터변수, string은 반환값타입

				// 응답
				success : function(data) {
					// data = [1,2,3,4,5]
					let htmlCode = "<h3>배열 응답 결과</h3>";
					$.each(data,function(i,v){
						htmlCode += i+"번째 데이터 : "+v+"<br>";
					})
					$('#result').html(htmlCode);
				},
				dataType : 'json' // 위의 응답시 사용하는 파라미터 'data'의 type
			});
		});

	// 객체 처리
	$("#objBtn").on('click',function(){
		$.ajax({
			// 요청
			url : "<%=request.getContextPath()%>/json/jsonTest.do",
				type : "post", // json의 경우 post,get 차이가 다르지 않음
				data : "choice=object", // choice는 파라미터변수, string은 반환값타입

				// 응답
				success : function(data) {
					// data = {"num":1,"name":"홍길동"}
					let htmlCode = "<h3>객체 응답 결과</h3>";
					
					htmlCode += "num : "+data.num+"<br>";
					htmlCode += "name : "+data.name+"<br>";
					
					$('#result').html(htmlCode);
				},
				dataType : 'json' // 위의 응답시 사용하는 파라미터 'data'의 type
			});
		});

	// 리스트 처리
	$("#listBtn").on('click',function(){
		$.ajax({
			// 요청
			url : "<%=request.getContextPath()%>/json/jsonTest.do",
				type : "post", // json의 경우 post,get 차이가 다르지 않음
				data : "choice=list", // choice는 파라미터변수, string은 반환값타입

				// 응답
				success : function(data) {
					let htmlCode = "<h3>리스트 응답 결과</h3>";
					
					$.each(data,function(i,v){
						htmlCode += i+"번째 자료 <br>";
						htmlCode += "num : "+v.num+"<br>";
						htmlCode += "name : "+v.name+"<br>";
					})
					
					$('#result').html(htmlCode);
				},
				dataType : 'json' // 위의 응답시 사용하는 파라미터 'data'의 type
			});
		});

	// Map객체 처리
	$("#mapBtn").on('click',function(){
		$.ajax({
			// 요청
			url : "<%=request.getContextPath()%>/json/jsonTest.do",
				type : "post", // json의 경우 post,get 차이가 다르지 않음
				data : "choice=map", // choice는 파라미터변수, string은 반환값타입

				// 응답
				success : function(data) {
					let htmlCode = "<h3>Map 응답 결과</h3>";
					
/* 					htmlCode += "name : "+data.name+"<br>";
					htmlCode += "tel : "+data.tel+"<br>";
					htmlCode += "addr : "+data.addr+"<br>"; */
					
					$.each(data,function(i,v){
						htmlCode += i+" : "+v+"<br>";
					})
					
					$('#result').html(htmlCode);
				},
				dataType : 'json' // 위의 응답시 사용하는 파라미터 'data'의 type
			});
		});
	});
</script>
</head>
<body>
	<form>
		<input type="button" id="strBtn" value="문자열"> 
		<input type="button" id="arrayBtn" value="배 열"> 
		<input type="button" id="objBtn" value="객 체"> 
		<input type="button" id="listBtn" value="리스트"> 
		<input type="button" id="mapBtn" value="Map객체">
	</form>
	<div id="result"></div>
</body>
</html>