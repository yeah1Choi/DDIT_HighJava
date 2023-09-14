package kr.or.ddit.basic;

import java.util.Properties;

/*
 <Properties 객체>
- Properties는 Map보다 축소된 기능의 객체라고 할 수 있다

- Map은 key값과 value값에 모든 종류의 객체를 사용할 수 있지만
  Properties는  key값과 value값에 String만 사용할 수 있다.

- Map은 put(), get() 메서드를 이용해 데이터를 입출력하지만
  Properties는 setProperty(), getProperty() 메서드를 통해서 데이터를 입출력한다

- Properties는 데이터를 파일로 입출력하는 기능이 있다.

*/
public class PropertiesTest {

	public static void main(String[] args) {
		Properties prop = new Properties();

		prop.setProperty("name", "홍길동");
		prop.setProperty("age", "25");

		int age = 20;
		// Properties 객체에 정수 타입 데이터 입력하기
//		prop.setProperty("age2", age + "");
		prop.setProperty("age2", String.valueOf(age));

		prop.setProperty("tel", "010-1234-5678");
		prop.setProperty("addr", "대전");

		///////////////////////////////////////////////////////////		

		String name = prop.getProperty("name");

		int tempAge = Integer.parseInt(prop.getProperty("age"));
		int tempAge2 = Integer.parseInt(prop.getProperty("age2"));

		String tel = prop.getProperty("tel");
		String addr = prop.getProperty("addr");

		System.out.println("이름 : " + name);
		System.out.println("나이 : " + tempAge);
		System.out.println("나이2 : " + tempAge2);
		System.out.println("전화번호 : " + tel);
		System.out.println("주소 : " + addr);
	}
}