package kr.or.ddit.basic;

public class SingletonTest {

	public static void main(String[] args) {
		//MySingleton test1 = new MySingleton(); 
		// 오류 : not visible <= 생성자가 private으로 가려놓아 외부에서 new 명령으로 객체 생성 불가
		
		// 대신 메소드를 호출할 수 있다 => 싱글톤아 인스턴스를 생성하고 반환한 메서드
		MySingleton test2 = MySingleton.getInstance();
		MySingleton test3 = MySingleton.getInstance(); // 더 호출해도 한번한 호출한다

		System.out.println("test2 => "+test2.toString());
		System.out.println("test3 => "+test3);
		/* result :
		 * test2 => kr.or.ddit.basic.MySingleton@15db9742 test3 =>
		 * kr.or.ddit.basic.MySingleton@15db9742
		 */
		
		System.out.println(test2 == test3);
		System.out.println(test2.equals(test3));
		/* result :
		 * true 
		 * true
		 */
	}
}