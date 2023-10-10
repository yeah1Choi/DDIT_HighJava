package kr.or.ddit.member.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;


@WebServlet("/member/memberInsert.do")
@MultipartConfig		// 파일 업로드 처리용 (프로필 사진 업로드)
public class MemberInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// GET방식으로 요청할 때는 회원 입력 폼이 나타나도록 구현한다.
		request.getRequestDispatcher("/WEB-INF/view/member/memberForm.jsp")
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST방식으로 요청할 때는 입력한 회원 정보를 받아서 프로필 사진을 저장하고
		// 회원 정보는 DB에 저장하는 기능을 구현한다.
		
		// 사용자가 업로드한 파일이 저장될 서버쪽의 폴더 경로 설정
		String uploadPath = "d:/d_other/uploadFiles";
		
		// 저장될 폴더가 없으면 폴더를 만들어 준다.
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		request.setCharacterEncoding("utf-8");
		
		// 클라이언트가 보내온 회원 정보를 받는다.
		String memId = request.getParameter("mem_id");
		String memPass = request.getParameter("mem_pass");
		String memName = request.getParameter("mem_name");
		String memTel = request.getParameter("mem_tel");
		String memAddr = request.getParameter("mem_addr");
		
		// 받아온 회원 정보를 VO에 저장한다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_pass(memPass);
		memVo.setMem_name(memName);
		memVo.setMem_tel(memTel);
		memVo.setMem_addr(memAddr);
		
		// form의 file입력요소의 name값은 Part객체의 이름 역할을 한다.
		// 이 이름을 이용하여 Part객체를 구한다.
		Part part = request.getPart("mem_photo");
		
		if(part!=null) {
			String fileName = extractFilename(part);
			
			if(!"".equals(fileName)) {
				try {
					part.write(uploadPath + File.separator + fileName);
					
					memVo.setMem_photo(fileName);  // DB에 저장할 파일명을 VO객체에 저장
				} catch (Exception e) {
					memVo.setMem_photo(null);  // 프로필 사진이 오류가 생기면...
				}
			}
		}
		
		// 회원 정보를 DB에 insert한다.
		IMemberService service = MemberServiceImpl.getInstance();
		service.insertMember(memVo);
		
		// 작업이 완료되면 List페이지로 이동
		response.sendRedirect(request.getContextPath()+"/member/memberList.do");
	}
	
	// Part구조 안에서 파일명을 찾는 메서드
	private String extractFilename(Part part) {
		String fileName = "";
		
		String contentDisposition = part.getHeader("content-disposition");
		String[] items = contentDisposition.split(";");
		for(String item : items) {
			if(item.trim().startsWith("filename")) {  
				fileName = item.substring(item.indexOf("=")+2, item.length()-1);
			}
		}
		
		return fileName;
	}

}











