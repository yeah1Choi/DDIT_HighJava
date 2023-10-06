package kr.or.ddit.fileupload.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.fileupload.service.FileinfoServiceImpl;
import kr.or.ddit.fileupload.service.IFileinfoService;
import kr.or.ddit.vo.FileInfoVO;

@WebServlet("/images/imageView.do")
public class ImageView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		// 파라미터로 넘어온 파일번호를 구한다
		String strFileNo = request.getParameter("fileno");
		int fileno = Integer.parseInt(strFileNo);

		// 파일 번호를 이용하여 DB에서 해당 파일 정보를 가져온다
		IFileinfoService service = FileinfoServiceImpl.getInstance();

		FileInfoVO fvo = service.getFileinfo(fileno);

		// 업로드된 파일이 저장될 폴더 설정
		String uploadPath = "d:/d_other/uploadFiles";

		// 지정된 폴더가 없으면 새로 만든다
		File f = new File(uploadPath);
		if (!f.exists()) {
			f.mkdirs();
		}

		// 다운받을 파일의 File객체 생성 => 실제 저장된 파일명을 이용하여 생성한다
		File downFile = new File(f, fvo.getSave_file_name());

		if (downFile.exists()) {

			// 자바의 입출력 스트림을 이용해서 서버에 있는 파일을 읽어서 클라이언트로 출력하는 작업을 진행하면 된다.
			BufferedInputStream bin = null;
			BufferedOutputStream bout = null;

			try {
				// 출력용 스트림 객체 생성 ( Response객체 이용 )
				bout = new BufferedOutputStream(response.getOutputStream());

				// 파일 입력용 스트림 객체 생성
				bin = new BufferedInputStream(new FileInputStream(downFile));

				byte[] temp = new byte[1024];
				int len = 0;
				while ((len = bin.read(temp)) > 0) {
					bout.write(temp, 0, len);
				}
				bout.flush();
			} catch (Exception e) {
				System.out.println("파일 입출력 오류 : " + e.getMessage());
			} finally {
				if (bin != null)
					try {
						bin.close();
					} catch (IOException e) {
					}
				if (bout != null)
					try {
						bout.close();
					} catch (IOException e) {
					}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}