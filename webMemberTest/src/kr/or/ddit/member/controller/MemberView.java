package kr.or.ddit.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;


@WebServlet("/member/memberView.do")
public class MemberView extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// 회원ID를 받는다.
		String memId = request.getParameter("mem_id");
		
		IMemberService service = MemberServiceImpl.getInstance();
		
		// 회원ID를 이용해서 해당 회원 정보를 구한다.
		MemberVO memVo = service.getMember(memId);  
		
		// 구해진 회원 정보를 Request객체에 저장한다.
		request.setAttribute("memberVo", memVo);
		
		// View페이지로 포워딩 한다.
		request.getRequestDispatcher("/WEB-INF/view/member/memberView.jsp")
		.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
