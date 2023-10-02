package kr.or.ddit.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionDelete.do")
public class SessionDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Session 정보 삭제하기
		// 1. Session객체 생성하거나 현재 생성 가져오기
		HttpSession session = request.getSession();
		
		// 2-1. 개별적인 Session값 삭제하기
		// 형식) Session객체.removeAttribute("key값");
		//  => 세션 자체는 삭제되지 않고 개별적인 세션값만 삭제된다.
//		session.removeAttribute("testSession");
		
		// 2-2. 세션 자체를 삭제하기
		// 형식) Session객체.invalidate();
		session.invalidate();
		
		response.setCharacterEncoding("utf-8");
    	response.setContentType("text/html charset=utf-8");
    	PrintWriter out = response.getWriter();
    	
    	out.println("<html>");
    	out.println("<head><meta charset='utf-8'><title>Session 삭제</title></head>");
    	out.println("<body>");
    	out.println("<h3>Session값이 삭제되었습니다</h3>");
    	out.println("<a href='"+ request.getContextPath() +"/basic/session/sessionTest.jsp'>시작문서로 이동</a>");
    	out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}