package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Cookie를 추가하는 서블릿
@WebServlet("/cookieAdd.do")
public class CookieAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// Cookie를 저장하는 방법 및 순서
		
		// 1. Cookie객체 생성 => '쿠키이름'과 '쿠키값'은 문자열로 지정한다
		//   형식) Cookie cookie변수 = new Cookie("쿠키이름","쿠키값");
		//       - '쿠키값'에 한글이 포함되었을 경우, URLEncoder.encode()메서드로 인코딩 후 저장한다
		//       - 하나의 쿠키는 4KB(4096byte)까지 저장 가능하다
		//       - 하나의 도메인 당 20개 (총 300개)까지 가능하다.
		Cookie nameCookie = new Cookie("name",URLEncoder.encode("홍길동","utf-8"));
		Cookie ageCookie = new Cookie("age1","20"); // 정수로 입력하면 오류
		int age = 30;
		Cookie ageCookie2 = new Cookie("age2",String.valueOf(age));
		Cookie genderCookie = new Cookie("gender","Female");
		
		// 2. 쿠키 속성 설정
		// cookie변수.setPath("적용경로"); => 지정된 경로와 그 하위 경로에서만 사용 가능하다
		// cookie변수.setMaxAge(유지시간); => 단위 : 초, 
		//   * -1 : 브라우저가 종료될 때까지 유지, 0 : 즉시 삭제된다
		// cookie변수.setDomain("적용도메인명"); => 예) "ddit.or.kr" => www1.ddit.or.kr, www2.ddit.or.kr, cafe.ddit.kr.or
		// cookie변수.setSecure(보안여부); => true : 보안적용(https프로토콜사용), false : 미적용
		
		// 3. Response객체를 이용하여 쿠키를 웹브라우저로 보낸다
		//    그러면 웹브라우저가 이 쿠키를 받아서 저장한다
		//    형식) response객체.addCookie(1번에서 만든 Cookie객체);
		response.addCookie(nameCookie);
		response.addCookie(ageCookie);
		response.addCookie(ageCookie2);
		response.addCookie(genderCookie);
		
		/////////////////////////////////////////////////
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>쿠키저장</title></head>");
		out.println("<body>");
		out.println("<h3>Cookie가 저장되었습니다</h3><br><hr><br>");
		out.println("<a href="+request.getContextPath()+"/basic/cookie/cookieTest01.jsp>시작 문서로 가기</a>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}