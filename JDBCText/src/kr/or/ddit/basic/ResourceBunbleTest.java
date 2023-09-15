package kr.or.ddit.basic;

import java.util.ResourceBundle;

/*
ResourceBundle객체 : 파일의 확장자가 '.properties'인 파일의 내용을 읽어와
				   key값과 value값을 분리해서 정보를 갖고 있는 객체
*/
public class ResourceBunbleTest {

	public static void main(String[] args) {
		// ResourceBundle 객체를 이용해 '.properties' 파일 읽어오기

		// ResourceBundle 객체 생성
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.jdbc.config.dbinfo");

		// 읽어온 내용 출력하기
		System.out.println("driver : "+bundle.getString("driver"));
		System.out.println("url : "+bundle.getString("url"));
		System.out.println("user : "+bundle.getString("user"));
		System.out.println("pass : "+bundle.getString("pass"));
		
		
	}
}