package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 쿠키 정보를 삭제하는 서블릿
@WebServlet("/cookieDelete.do")
public class CookieDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		// 저장된 쿠키 삭제하기

		// 쿠키의 삭제는 쿠키의 '유지시간'을 '0'으로 설정하여 다시 설정하여 다시 저장하는 방법을 사용한다

		// 1. 전체 쿠키 정보 가져오기
		Cookie[] cookieArr = request.getCookies();

		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>쿠키 삭제하기</title></head>");
		out.println("<body>");
		out.println("<h3>저장된 cookie정보 삭제하기</h3>");

		if (cookieArr != null) {
			// 2. 전체 쿠키 정보 중에서 삭제할 쿠키를 찾는다 (예 : gender 쿠키 삭제하기)
			for (Cookie cookie : cookieArr) {
				String name = cookie.getName(); // 쿠키이름 구하기
				if ("gender".equals(name)) { // 삭제할 쿠키이름과 같은지 비교하여 찾는다
					// 3. 찾은 쿠키의 유지시간을 '0'으로 설정하여 다시 저장한다
					cookie.setMaxAge(0); // 유지시간 설정
					response.addCookie(cookie); // 쿠키 다시 저장
				}
			}
		}
		// JSP 문서, CSS 문서 등이 저장된 폴더 중에서 'ContextPath'에 해당하는 폴더는
		// 'WebContent'폴더 이다
		out.println("<a href=" + request.getContextPath() + "/basic/cookie/cookieTest01.jsp>시작 문서로 가기</a>");

		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
