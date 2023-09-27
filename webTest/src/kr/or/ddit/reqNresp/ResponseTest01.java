package kr.or.ddit.reqNresp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/responseTest01.do")
public class ResponseTest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		  - forward
			특정 문서(서블릿,JSP)에 대한 요청을 다른 문서(서블릿,JSP)로 넘겨준다
			(이 때 HttpServletRequest객체와 HttpServletResponse객체를 공유한다.) 
			클라이언트의 주소창의 URL주소는 처음 요청할 때의 주소가 바뀌지 않는다.
			서버 내부에서만 접근이 가능하다. 
		   
	   		이동할 페이지로 값을 넘기려면 HttpSevletRequest객체의 setAttrubute()메서드로
	   		데이터를 셋팅하여 보내고, 받는 쪽에서는 getAttrubute()메서드로 데이터를 읽어온다.
	   		보낼 때 형식) request객체.setAttribute("key값", 셋팅할 데이터)
	   		=> key값은 문자열로 지정하고 셋팅할 데이터는 자바의 모든 자료형을 지정할 수 있다.
	   		받을 때 형식) request객체.getAttribute("key값");
		*/
		request.setAttribute("tel", "010-1234-5678");
		
		/* 
		  - 다른 문서로 이동하기
		 	request객체의 getRequestDispatcher()메서드에 이동할 서블릿이나 JSP를 지정해주는데
		 	전체 URI 경로 중 ContextPath 이후의 경로를 지정해준다
		*/
		// 이동할 경로가 "WebContent/forwardTest.do"
		RequestDispatcher rd = request.getRequestDispatcher("/forwardTest.do");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}