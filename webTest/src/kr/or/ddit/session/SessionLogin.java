package kr.or.ddit.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionLogin.do")
public class SessionLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("utf-8");
		response.setContentType("text/html charset=utf-8");
		PrintWriter out = response.getWriter();
		
    	String userId = request.getParameter("id"); // id값 받기
    	String userPass = request.getParameter("pass"); // pass값 받기
    	
    	HttpSession session = request.getSession();
    	
    	if("admin".equals(userId) && "1234".equals(userPass)) { // 로그인성공
    		// Session에 로그인 정보 저장
    		session.setAttribute("loginId", userId);
    	}
    	
    	response.sendRedirect(request.getContextPath()+"/basic/session/sessionLogin.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}