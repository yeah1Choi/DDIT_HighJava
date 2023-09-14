package kr.or.ddit.basic;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/*
URLConnection클래스 : 애플리케이션과 URL간의 통신 연결을 위한 클래스
*/
public class URLTest02 {

	public static void main(String[] args) throws IOException {
		// 특정 서버의 정보과 URL의 내용 가져와 출력하는 예제
		URL url = new URL("https://www.naver.com/index.html");

		// URLConnection객체 구하기 : URL객체를 이용하여 구한다
		URLConnection urlCon = url.openConnection();

		// Header정보 가져오기 : 구한 Map객체의 key값은 Header정보의 이름을 나타내고
		// value값은 Header정보의 값을 나타냄
		Map<String, List<String>> headerMap = urlCon.getHeaderFields();

		for (String headerKey : headerMap.keySet()) {
			System.out.println(headerKey + " : " + headerMap.get(headerKey));
		}
	}
}