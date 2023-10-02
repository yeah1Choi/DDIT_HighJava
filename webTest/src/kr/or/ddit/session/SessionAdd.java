package kr.or.ddit.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionAdd.do")
public class SessionAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Session 정보 저장하는 방법 및 순서
    	// 1. Session객체를 생성하거나 현재 세션 가져오기
    	// 형식1) Request객체.getSession(); 또는 Request객체.getSession(true);
    	//  => 현재 세션이 존재하면 현재 세션을 반환하고, 세션이 존재하지 않으면 새로운 세션을 생성하여 반환한다.
    	// 형식2) Request객체.getSession(false);
    	//  => 현재 세션이 존재하면 현재 세션을 반환하고, 세션이 존재하지 않으면 null을 반환한다
    	HttpSession session = request.getSession();
    	
    	// 2. Session객체의 setAttribute()메서드를 이용하여 세션값을 저장한다
    	// 형식) Session객체.setAttribute("key값", session값);
    	//  => "key값"은 문자열, session값은 자바의 모든 자료형(객체도 저장가능)
    	session.setAttribute("testSession", "연습용세션입니다.");
    	session.setAttribute("username", "홍길동");
    	session.setAttribute("age", 30); // 숫자사용시 형변환이 필요없음! 
    	
    	response.setCharacterEncoding("utf-8");
    	response.setContentType("text/html charset=utf-8");
    	PrintWriter out = response.getWriter();
    	
    	out.println("<html>");
    	out.println("<head><meta charset='utf-8'><title>Session 저장</title></head>");
    	out.println("<body>");
    	out.println("<h3>Session값이 저장되었습니다</h3>");
    	out.println("<a href='"+ request.getContextPath() +"/basic/session/sessionTest.jsp'>시작문서로 이동</a>");
    	out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}