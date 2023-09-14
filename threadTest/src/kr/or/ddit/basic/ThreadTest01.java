package kr.or.ddit.basic;
/*
'*'문자를 200개 출력하는 기능과 '$'문자를 200개를 출력하는 기능 구현하기
 */
public class ThreadTest01 {
	public static void main(String[] args) {
		// 싱글 쓰레드 프로그램
		for (int i = 1; i < 200; i++) {
			System.out.println("*");
		}
		
		System.out.println();
		System.out.println();

		for (int i = 1; i <= 200; i++) {
			System.out.println("$");
		} 
		// 순서에 따라 *을 출력을 다하고 $출력한 것을 알 수 있음
		System.out.println();
	}
}