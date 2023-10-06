package kr.or.ddit.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/json/jsonTest.do")
public class JsonTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setCharacterEncoding("utf-8");
		// 응답을 JSON데이터로 할 때의 ContentType 설정 방법
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter(); // PrintWriter: 문자기반 스트림
		
		// 클라이언트가 보내온 데이터 받기
		String choice = request.getParameter("choice");
		// JSON처리용 객체 생성
		Gson gson = new Gson();
		// JSON문자열로 변환된 데이터가 저장될 변수 선언
		String jsonData = null;
		
		// 응답용 데이터를 만들어서  JSON문자열로 반환한다
		switch(choice) {
		case "string" :
			String str = "안녕~";
			jsonData = gson.toJson(str);
			break;
		case "array" :
			int[] intArr = new int[] {1,2,3,4,5};
			jsonData = gson.toJson(intArr);
			break;
		case "object" :
			SampleVO svo = new SampleVO(1, "홍길동");
			jsonData = gson.toJson(svo);
			break;
		case "list" :
			ArrayList<SampleVO> list = new ArrayList<SampleVO>();
			list.add(new SampleVO(10,"강감찬"));
			list.add(new SampleVO(20,"이순신"));
			list.add(new SampleVO(30,"을지문덕"));
			
			jsonData = gson.toJson(list);
			break;
		case "map" :
			HashMap<String, String> map = new HashMap<String, String>();
			
			map.put("name", "성춘향");
			map.put("tel", "010-1234-2682");
			map.put("addr", "대전시 중구 오류동");
			
			jsonData = gson.toJson(map);
			break;
		}
		
		// 변환된 JSON문자열을 응답으로 전송한다.
		System.out.println("jsonData = "+jsonData);
		out.write(jsonData);
		
		response.flushBuffer(); // 버퍼비우기
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
