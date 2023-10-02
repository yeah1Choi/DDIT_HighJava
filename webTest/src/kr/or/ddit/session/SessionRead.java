package kr.or.ddit.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionRead.do")
public class SessionRead extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html charset=utf-8");
		PrintWriter out = response.getWriter();
		
    	// 저장된 Session값 읽어오기
    	// 1. Session객체를 생성하거나 현재 세션 가져오기
    	HttpSession session = request.getSession();

    	out.println("<html>");
    	out.println("<head><meta charset='utf-8'><title>Session 읽기</title></head>");
    	out.println("<body>");
    	out.println("<h3>저장된 Session값 확인하기</h3>");
    	
    	// 2. Session값 가져오기 => getAttribute() 메서드 이용
    	// 형식) Session객체.getAttribute("key값");
    	out.println("<h3>Session값 1개 확인하기</h3>");
    	String sessionValue = (String)session.getAttribute("testSession");
    	
    	if(sessionValue == null) {
    		out.println("<h4>세션의 key값이 'testSession'인 세션 정보는 없습니다.</h4>");
    	} else {
    		out.println("<h4>'testSession'의 세션값 : "+ sessionValue +"</h4>");
    	}
    	
    	out.println("<br><hr><br>");
    	out.println("<h3>전체 Session 데이터 확인하기</h3>");
    	
    	out.println("<ol>");
    	// 세션 전체의 세션이름(key값) 가져오기 // Enumeration <= etelator의 이전 버전 정도
    	Enumeration<String> sessionKeys = session.getAttributeNames();
    	int cnt = 0;
    	while(sessionKeys.hasMoreElements()) {
    		cnt++;
    		String sessionKey = sessionKeys.nextElement();
    		out.println("<li>"+sessionKey+" : "+session.getAttribute(sessionKey)+"</li>");
    	}
    	if(cnt == 0) out.println("<li>세션이 하나도 없습니다.</li>");
    	out.println("</ol>");

    	out.println("<br><hr><br>");
    	out.println("<h3>Session 관련 정보</h3>");
    	// 세션ID : 세션을 구분하기 위한 고유한 값
    	out.println("세션ID : " + session.getId() + "<br>"); 
    	// 세션 생성 시간 : 1970.01.01 부터 경과한 시간 (단위:밀리세컨드)
    	out.println("세션 생성 시간 : " + session.getCreationTime() + "<br>"); 
    	// 가장 최근 세션에 접근한 시간 : 1970.01.01 부터 경과한 시간 (단위:밀리세컨드)
    	out.println("세션 최근 접근 시간 : " + session.getLastAccessedTime() + "<br>"); 
    	// 세션 유효 시간 : 유효시간의 설정은 Session객체.getMaxInactiveInterval(설정시간)으로 설정할 수 있다 (단위:초)
    	out.println("세션 유효 시간 : " + session.getMaxInactiveInterval() + "<br>"); 
    	
    	out.println("<br><a href='"+ request.getContextPath() +"/basic/session/sessionTest.jsp'>시작문서로 이동</a>");
    	out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}