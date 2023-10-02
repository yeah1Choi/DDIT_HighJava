package kr.or.ddit.cookie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieLoginServlet.do")
public class CookieLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		// id, pass, checkbox 파라미터의 값을 구한다
		String userId = request.getParameter("id");
		String userPass = request.getParameter("pass");
		String chkId = request.getParameter("chkid");

		// userId값을 갖는 Cookie객체 생성
		Cookie loginCookie = new Cookie("USERID", userId);

		System.out.println("checkbox의 체크여부 : " + chkId);

		if (chkId == null) { // 체크박스가 체크되지 않으면
			loginCookie.setMaxAge(0); // 쿠키를 삭제하기 위해 유지시간을 0으로 설정
		}
		// 쿠키를 다시 저장
		response.addCookie(loginCookie);

		String contextPath = request.getContextPath();

		if ("test".equals(userId) && "1234".equals(userPass)) { // 로그인 성공
			response.sendRedirect(contextPath + "/basic/cookie/cookieMain.jsp");
		} else { // 로그인 실패
			response.sendRedirect(contextPath + "/basic/cookie/cookieLogin.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}