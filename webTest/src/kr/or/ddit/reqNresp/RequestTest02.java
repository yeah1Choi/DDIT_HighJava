package kr.or.ddit.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/requestTest02.do")
public class RequestTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 파라미터 값 받기
		int firNum = Integer.parseInt(request.getParameter("firstNum"));
		String operator = request.getParameter("operator");
		int secNum = Integer.parseInt(request.getParameter("secondNum"));
		
		// 계산된 결과가 저장될 변수
		double result = 0;
		boolean calcok = true;
		/*
		 * if (operator.equals("+")) { result = firNum + secNum; } else if
		 * (operator.equals("-")) { result = firNum - secNum; } else if
		 * (operator.equals("*")) { result = firNum * secNum; } else if
		 * (operator.equals("/")) { result = firNum / secNum; }
		 */
		switch(operator) {
		case "+":
			result = firNum + secNum;
			break;
		case "-":
			result = firNum - secNum; 
			break;
		case "*":
			result = firNum * secNum;
			break;
		case "/":
			if(secNum != 0) {
				result = (double)firNum / secNum;
			} else {
				calcok = false;
			}
			break;
		case "%":
			if(secNum != 0) {
				result = firNum % secNum;
			} else {
				calcok = false;
			}
			break;
		}
		
		out.println("<html><head><meta charset='utf-8'><title>Request 연습 계산기</title></head>");
		out.println("<body>");
		out.println("<hr>");
		out.println("<h3>계산결과</h3>");
		out.println("<hr>");
		out.println("<p>");
		out.printf("%d %s %d = ", firNum, operator, secNum);
		
		if(calcok) { //계산성공
			out.print(result);
		} else { //계산실패
			out.print("계산 불능 (0으로 나눔)");
		}
		
		out.println("</p>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
