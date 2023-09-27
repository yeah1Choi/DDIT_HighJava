<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<hr>
	<h3>Request연습 Form (숫자입력은 정수형으로 입력하세요.)</h3>
	<hr>
	<form action="/webTest/requestTest02.do" method="post">
		<table>
			<tr>
				<td><input type="text" size="11" name="firstNum" placeholder="첫번째 숫자 입력"></td>
				<td>
					<select name="operator">
						<option value="+">+</option>
						<option value="-">-</option>
						<option value="*">*</option>
						<option value="/">/</option>
						<option value="%">%</option>
					</select>
				</td>
				<td><input type="text" size="11" name="secondNum" placeholder="두번째 숫자 입력"></td>
				<td><input type="submit" value="전송"></td>
			</tr>
		</table>
	</form>
</body>
</html>