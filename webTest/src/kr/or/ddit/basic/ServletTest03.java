package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	Servlet 동작 과정에 대하여
	
	1. 사용자(클라이언트)가 URL을 클릭하여 HTTP요청(Request)를 Servlet Container로 전송(요청)한다.
	
	2. 컨테이너는 web.xml에 정의된 'url패턴'을 확인하여 어느 서블릿을 통해 처리해야 할지를 검색한다.
		* 로딩이 안된 경우에는 로딩을 한다. 처음 로딩 시 init()가 호출된다.
		* 이 부분 설정의 서블릿 버전 3.0이상에서는 @WebServlet 애너테이션을 이용하여 설정 가능하다
		
	3. Servlet Container는 개별 요청을 처리할 쓰레드를 생성하여 해당 서블릿 객체의 service()를 호출한다. 
		* 이 때 HttpServletRequest객체와 HttpServletResponse객체를 생성하여 매개변수에 넘겨준다.
		
	4. service()는 전송 방식(GET, POST 등의 메서드 타입)을 체크하여 적절한 메서드를 호출한다.
		* doGet(), doPost(), doPut(), doDelete() 등..
	
	5. 요청 및 응답 처리가 모두 완료되면 HttpServletRequest객체와 HttpServletResponse객체는 자동으로 소멸된다.
	
	6. 컨테이너로부터 서블릿이 제거되는 경우에는 destroy()가 호출된다.
*/

// Servlet Lifecycle 예제
@WebServlet("/servletTest03.do")
public class ServletTest03 extends HttpServlet {

	@Override
	public void init() throws ServletException {
		System.out.println(this.getServletName() + "에서 init() 호출");
	}

	@Override
	public void destroy() {
		System.out.println(this.getServletName() + "에서 destroy() 호출");
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service() 시작");
		
		// GET방식과 POST방식에 맞는 메소드 호출
		// 방법 1) 부모 클래스인 HttpServlet의 service()에게 위임하기
		//super.service(request, response);
		
		// 방법 2) 클라이언트의 전송방식(GET, POST 등)을 구분해서 직접 메서드 호출하기
		//		HttpServletRequest객체의 getMethod()메서드를 이용한다
		String method = request.getMethod();
		System.out.println("method : "+method);
		
		if("GET".equals(method)) {
			doGet(request,response);
		} else {
			doPost(request,response);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() 시작");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title></title></head>");
		out.println("<body><h2 style='color:blue';>doGet()을 처리한 결과입니다.</h2></body></html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
System.out.println("doPost() 시작...");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title></title></head>");
		out.println("<body><h2 style='color:blue';>doPost()을 처리한 결과입니다.</h2></body></html>");
	}
}
