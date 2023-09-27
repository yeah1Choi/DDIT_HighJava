package kr.or.ddit.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/requestTest01.do")
public class RequestTest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST방식으로 전달되는 데이터의 문자 인코딩 방식 설정 - doPost메소드에서 이 메소드를 다시 불러오기 때문에
		request.setCharacterEncoding("utf-8");
		
		// 클라이언트에서 보내온 데이터를 받기
		// Request객체의 getParameter()메서드를 이용한다
		// 형식) Request객체.getParameter("파라미터명"); 
		// -> 해당 파라미터명에 설정된 값을 가져온다 -> 가져온 값의 자료형은 String이다
		String userName = request.getParameter("username");
		String job = request.getParameter("job");
		
		// 같은 파라미터명이 여러개일 경우, Request객체의 getParameterValues()메서드를 이용
		// 형식) Request객체.getParameterValues("파라미터명")
		// => 가져오는 값의 자료형은 String배열(String[])이다
		String[] hobbies = request.getParameterValues("hobby");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>Requet 객체 연습</title></head>");
		out.println("<body>");
		out.println("<h3>Request객체 테스트 결과</h3>");
		out.println("<table border='1'>");
		out.println("<tr><th>이름</th>");
		out.println("<td>"+userName+"</td></tr>");
		out.println("<tr><th>직업</th>");
		out.println("<td>"+job+"</td></tr>");
		out.println("<tr><th>취미</th>");
		out.println("<td>");
		if(hobbies == null) {
			out.println("취미가 없습니다");
		} else {
			for(String hobby : hobbies) {
				out.println(hobby+"<br>");
			}
		}
		out.println("</td></tr>");
		out.println("</table>");
		
		out.println("<br><hr color='blue'><br>");

		out.println("<h2>Request객체의 메서드</h2>");
		out.println("<ol>");
		out.println("<li>클라이언트의 IP주소 : "+request.getRemoteAddr()+"</li>");
		out.println("<li>요청 메서드 : "+request.getMethod()+"</li>");
		out.println("<li>ContextPath : "+request.getContextPath()+"</li>");
		out.println("<li>프로토콜 : "+request.getProtocol()+"</li>");
		out.println("<li>URL정보 : "+request.getRequestURL()+"</li>");
		out.println("<li>URI정보 : "+request.getRequestURI()+"</li>");
		out.println("</ol>");
				
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
