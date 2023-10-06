package kr.or.ddit.fileupload.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.fileupload.service.FileinfoServiceImpl;
import kr.or.ddit.fileupload.service.IFileinfoService;
import kr.or.ddit.vo.FileInfoVO;
/*
  	- Servlet 3.0이상에서 파일 업로드를 처리하려면 서블릿에 @MultipartConfig 에노테이션을 설정해야한다
  	- @MultipartConfig 에노테이션에 설정하는 변수들 : 
  		1) location : 업로드한 파일이 임시적으로 저장될 경로를 지정한다. (기본값 : "")
  		2) fileSizeThreshold : 이 곳에 지정한 값보다 큰 파일이 전송되면 location에 지정한
  			임시 디렉토리에 저장한다. (단위:byte, 기본값: 0 (무조건 임시 디렉토리 사용))
  		3) maxFileSize : 1개 파일의 최대 크기 (단위:byte, 기본값:-1(무제한의 의미))
  		4) maxRequestSize : 서버로 전송되는 Request 데이터 전체의 최대 크기 
  			(단위:byte, 기본값:-1(무제한의 의미))
 */
@WebServlet("/fileList.do")
@MultipartConfig(
	fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 30,
	maxRequestSize = 1024 * 1024 * 100
)
public class FileList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IFileinfoService service = FileinfoServiceImpl.getInstance();
		
		// 전체 목록 가져오기
		List<FileInfoVO> list = service.getAllFileinfo();
		
		// 가져온 전체 목록을 뷰로 보낸다
		request.setAttribute("fileList", list);
		
		request.getRequestDispatcher("/basic/fileupload/fileList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
