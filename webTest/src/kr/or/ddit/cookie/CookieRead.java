package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 저장도니 Cookie를 읽어오는 서블릿
@WebServlet("/cookieRead.do")
public class CookieRead extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 저장된 쿠키 읽어오기
		
		// 1. 전체 쿠키 정보를 Request객체를 통해서 가져온다
		//   => 가져온 쿠키 정보들은 배열에 저장된다
		//   형식) Cookie[] 쿠키배열변수 = request객체.getCookies();
		Cookie[] cookieArr = request.getCookies();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>쿠키 읽기</title></head>");
		out.println("<body>");
		out.println("<h3>저장된 cookie 데이터 확인하기</h3>");
		out.println("</body></html>");
		
		if(cookieArr == null || cookieArr.length == 0) {
			out.println("<h3>저장된 cookie가 하나도 없습니다</h3>");
		} else {
			// 2. 쿠키배열에서 해당 쿠키 정보를 구해온다
			for(Cookie cookie : cookieArr) {
				String name = cookie.getName(); // 쿠키이름 가져오기
//				String value = cookie.getValue(); // 쿠키값 가져오기
				// 한글로 저장된 쿠키값을 읽어온 후 디코딩해서 사용한다
				String value = URLDecoder.decode(cookie.getValue(), "utf-8");
				out.println("쿠키이름 : "+name+"<br>");
				out.println("쿠키값 : "+value+"<hr>");
			}
		}
		// JSP 문서, CSS 문서 등이 저장된 폴더 중에서 'ContextPath'에 해당하는 폴더는 
		// 'WebContent'폴더 이다
		out.println("<a href="+request.getContextPath()+"/basic/cookie/cookieTest01.jsp>시작 문서로 가기</a>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}