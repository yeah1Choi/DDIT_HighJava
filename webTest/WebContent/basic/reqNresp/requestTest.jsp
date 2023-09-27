<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 
form 태그의 속성
1) action : form에서 구성한 데이터를 서버로 보내면 이 데이터를 받아서 처리할 문서명(서블릿, JSP), 
                          생략하면 '현재 문서'가 기본값이 된다
2) method : 전송방식 지정 (GET 또는 POST), 기본방식은 GET방식
3) target : 응답이 나타날 프레임
4) enctype : 서버로 파일을 전송할 때 이 속성에 'multipart/form-data'값을 지정한다.
 -->

	<%-- 이것은 JSP 주석 : 작업 관리에도 보이지 않음 --%>
	<%
	// 이 영역은 JSP문서에서 JAVA명령을 사용할 수 있는 영역 
	// 이 영역은 '스크립트릿'이라고 한다 
	String name = "홍길동";
	%>
	<%-- 
 <%= 변수 또는 수식 %> : JSP문서에서 변수나 수식의 결과를 출력할 때 사용한다.
 --%>
	<h2><%=name%>의 Request 연습 form
		<%=3 + 4%></h2>
	<form action="/webTest/requestTest01.do" method="get">
		<table border="1">
			<tr>
				<td>이름</td>
				<td> <input type="text" size="10" name="username"> </td>
			</tr>
			<tr>
				<td>직업</td>
				<td> <select name="job">
					<option value="학생">=학생=</option>
					<option value="회사원">=회사원=</option>
					<option value="전문직">=전문직=</option>
					<option value="무직">=무직=</option>
				</select> </td>
			</tr>
			<tr>
				<td>취미</td>
				<td> 
					<input type="checkbox" name="hobby" value="여행">여행 
					<input type="checkbox" name="hobby" value="독서">독서
					<input type="checkbox" name="hobby" value="게임">게임 
					<input type="checkbox" name="hobby" value="운동">운동 
					<input type="checkbox" name="hobby" value="영화">영화 
					<input type="checkbox" name="hobby" value="쇼핑">쇼핑 
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center;">
					<input type="submit" value="전송">
					<input type="reset" value="취소">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>