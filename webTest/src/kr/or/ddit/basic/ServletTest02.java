package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
/*
	이 예제는 서블릿 등록을 배포 서술자(web.xml)를 이용하지 않고 
	@WebServlet이라는 애노테이션을 이용하여 등록하는 예제이다
	
	@WebServlet 애노테이션은 Servlet버전 3.0이상에서 사용 가능하다
	
	@WebServlet 애노테이션의 속성들
	1) name : 서블릿의 이름을 설정, 기본값 = 빈문자열("")
	2) urlPatterns : 서블릿의 URL패턴의 목록을 설정, 기본값 = 빈배열({ })
	       예) urlPatterns="/url패턴1" 또는 urlPatterns={"/url패턴1"} <= 패턴이 1개인 경우
	       예) urlPatterns={"/url패턴1","/url패턴2",...} <= 패턴이 2개 이상인 경우
	3) value : urlPatterns와 동일한 기능을 한다
	4) description : 주석(설명글)을 지정한다
*/
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/servletTest02.go" }, description = "애노테이션을 이용한 서블릿 설정")
public class ServletTest02 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("uft-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		// 처리한 내용 출력하기 
		// 방법2) print(), println(), printf()를 이용한 내용 출력
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>두번째 서블릿</title></head>");
		out.println("<body>");
		out.println("<h2>안녕하세요. 두번째 Servlet 프로그램입니다.<br>");
		out.println("@WebServlet 애노테이션을 이용한 예제입니다. </h2>");
		out.println("</body></html>");
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
