package kr.or.ddit.basic;

import java.net.MalformedURLException;
import java.net.URL;

/*
URL 클래스 : 인터넷에 존재하는 서버들의 자원에 접근할 수있는 주소를 다루는 클래스

URL주소 구조 : 프로토콜://호스트명:포트번호/경로명/파일명?쿼리스트링#참조
=> 예: https://ddit.or.kr:443/index.html?tttt=123
*/
public class URLTest01 {

	public static void main(String[] args) throws MalformedURLException {
//		URL url = new URL("https://ddit.or.kr:443/index.html?tttt=123");
		URL url = new URL("https", "ddit.or.kr", 443, "index.html?tttt=123");

		System.out.println("URL 클래스를 이용하 URL 정보 출력하기");
		System.out.println("Protocol : " + url.getProtocol());
		System.out.println("Host : " + url.getHost());
		System.out.println("Port : " + url.getPort());
		System.out.println("File : " + url.getFile());
		System.out.println("Path : " + url.getPath());
		System.out.println("Query : " + url.getQuery());
		System.out.println();

		System.out.println(url.toExternalForm());
	}
}