package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/*
벡터를 잘 사용하지 않는것은 동기화 기능을 갖고 있어서 멀티스레드 환경의 여부와 상관없이 대부분의 조건에서 성능 저하를 일으킨다.
*/
public class ThreadTest17 {
	/*
	 * Vector, Hashtable 등과 같이 예전부터 존재하던 Collection 객체들은 내부에 동기화 처리가 되어 있다. 그런데 새로
	 * 구성된 Collection들은 동기화 처리가 되어 있지 않다. 그래서 동기화가 필요한 프로그램에 이런 Collection들을 사용하려면
	 * 동기화 처리를 한 후에 사용해야 한다.
	 */
	private static Vector<Integer> vec = new Vector<Integer>();

	// 동기화 처리가 되지 않은 List
	private static List<Integer> list1 = new ArrayList<Integer>();
	// ArrayList - 정해진 크기에서 더 받기 위해 더 큰 배열을 만들고 복사 하는 과정 중 쓰레드 교착상태가 생겨 오류가 생길 수 있다

	// 동기화 처리를 한 List
	private static List<Integer> list2 = Collections.synchronizedList(new ArrayList<Integer>());

	public static void main(String[] args) {
		// 익명 구현체로 쓰레드 구현
		Runnable r = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					//vec.add(i);
					//list1.add(i);
					list2.add(i);
				}
			}
		};
		Thread[] thArr = new Thread[] { new Thread(r), new Thread(r), new Thread(r), new Thread(r), new Thread(r) };
		for (Thread th : thArr) {
			th.start();
		}
		for (Thread th : thArr) {
			try {
				th.join();
			} catch (InterruptedException e) {
				// System.out.println("vec의 갯수 : " + vec.size());
				// System.out.println("list1의 갯수 : " + list1.size());
				System.out.println("list2의 갯수 : " + list2.size());
			}
		}
	}
}