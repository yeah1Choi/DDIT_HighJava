package kr.or.ddit.reqNresp;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/responseTest02.do")
public class ResponseTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		  - redirect 방식
			다른 문서로 이동한다. (직접 파라미터를 넘길 수 없다)
			응답 시 브라우저에게 '이동할 URL'을 전송하여 브라우저가 해당 URL로 재요청하는 방식이다
			(이 때 GET방식으로 요청하기 때문에 URL주소의 길이에 제한이 있다.)
			
			사용방법은 HttpServletResponse객체의 sendRedirect()메서드를 이용한다.
			형식) response객체.sendRedirect('이동할 URL주소');
				=> '이동할 URL주소'는 전체 URI경로를 지정해주어야 한다.
		 */
		
		// redirect는 Request객체를 공유하지 않는다
//		request.setAttribute("tel", "010-9999-9999");
		
//		response.sendRedirect(request.getContextPath()+"/redirectTest.do");
		
		request.setCharacterEncoding("utf-8");
		
		// 파라미터 데이터를 받는다
		String userName = request.getParameter("username");
	    String tel = "010-9999-9999";
	    
	    // URI경로에 한글이 포함될 경우에는 URLEncoder객체를 이용하여 인코딩해서 지정해 주어야한다
	    userName = URLEncoder.encode(userName,"utf-8");
	    
	    response.sendRedirect(request.getContextPath()+"/redirectTest.do?username="+userName+"&tel="+tel);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
