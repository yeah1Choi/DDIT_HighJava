package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieCountServlet.do")
public class CookieCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 쿠키이름은 'count'로 한다
		
		// 'count'라는 쿠키가 있는지 검사 
		Cookie[] cookieArr = request.getCookies();
		int count = 0;
		
		if(cookieArr != null) {
			for(Cookie cookie : cookieArr) {
				if("count".equals(cookie.getName())) { // 'count' 쿠키이름 찾기
					String value = cookie.getValue(); // 현재의 쿠키값(count) 구하기
					count = Integer.parseInt(value);
				}
			}
		}
		count++; // count값 증가
		
		Cookie countCookie = new Cookie("count", String.valueOf(count)); // 쿠키는 문자열 데이터만 저장 - 저장할 때 객체 생성 !
		response.addCookie(countCookie); // 쿠키 저장
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>쿠키 추가</title></head>");
		out.println("<body>");
		out.println("<h3>어서오세요. 당신은 "+ count +"번째 방문입니다.</h3>");
		out.println("<a href="+request.getContextPath()+"/cookieCountServlet.do>Cookie Count 증가하기</a><br>");
		out.println("<a href="+request.getContextPath()+"/basic/cookie/cookieTest02.jsp>시작 문서로 가기</a>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
