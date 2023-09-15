package kr.or.ddit.basic;

public class DocTest {

	public static void main(String[] args) {
		System.out.println("test");
		javaDocTest test = null;
		
		test.methodAdd(1, 2);
		test.methodSub();
		
		// MVC 패턴에서 사용하는 클래스들 - 모델과 관련있는 객체들
		// 1. VO 또는 DTO 객체
		// 2. DAO 객체
		// 3. Service 객체
		// 위 객체들의 역할(쓰임새)에 대해 공부..
	}

}
