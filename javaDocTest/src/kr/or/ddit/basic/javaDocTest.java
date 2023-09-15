package kr.or.ddit.basic;
// JavaDoc 문서 파일 만들기 예제

/**
 * 
 * @author PC-24
 * @version 1.0
 * 
 * <p> 
 * 파일명 : JavaDocTest.java <br>
 * 설명 : JavaDoc 문서 작성을 위한 연습용 interface <br><br>
 * 
 * 변경이력 <br>
 * ---------------------------------<br>
 * 변경일자 : 2023-09-15<br>
 * 변경자 : 최예원<br>
 * 변경내용 : 최초 작성<br>
 * </p>
 * 
 */
public interface javaDocTest {
	
	/**
	 * 메서드명 : methodTest<br>
	 * 설      명 : 반환값이 없는 메서드<br>
	 * 
	 * @param a 첫번째 매개변수 (정수형)
	 * @param b 두번째 매개변수 (정수형)
	 */
	
	public void methodTest(int a,int b);
	
	/**
	 * 메서드명 : methodAdd<br>
	 * 설      명 : 반환값이 있는 메서드<br>
	 * 
	 * @param x 정수형 첫번째 매개변수
	 * @param y 정수형 두번째 매개변수
	 * 
	 * @return 처리된 결과를 정수형으로 반환한다.
	 */
	
	public int methodAdd(int x,int y);
	
	/**
	 * 메서드명 : methodSub<br>
	 * 설      명 : 매개변수가 없고 반환값이 있는 메서드<br>
	 * 
	 * @return 정수형으로 반환한다.
	 */
	public int methodSub();
}
