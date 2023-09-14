package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class SetTest {

	public static void main(String[] args) {

		// - List와 Set의 차이점
		// 1. List
		// - 데이터의 순서(index)가 있다
		// - 중복되는 데이터를 저장할 수 있다
		// 2. Set
		// - 데이터의 순서(index)가 없다
		// - 중복되는 데이터를 저장할 수 없다 -> 중복되는 데이터 제거용으로 set이 많이 쓰임

		HashSet hs1 = new HashSet();

		// Set에 데이터 추가하기 => add() 메서드 이용
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2);
		hs1.add("CC");
		hs1.add("BB");
		hs1.add(1);
		hs1.add(3);

		System.out.println("set 데이터 => " + hs1);
		System.out.println("set 개수 => " + hs1.size());
		System.out.println();

		// Set에 중복되는 데이터를 추가하면 false를 반환하고 데이터는 추가되지 않는다
		boolean isAdd = hs1.add("FF");
		System.out.println("중복되지 않을 때 isAdd = " + isAdd);
		System.out.println("set 데이터 => " + hs1);
		System.out.println();

		isAdd = hs1.add("CC");
		System.out.println("중복될 때 isAdd = " + isAdd);
		System.out.println("set 데이터 => " + hs1); // set 데이터 => [DD, AA, CC, BB, FF, 1, 2, 3] << 중복값을 추가하지 않는다

		// Set의 데이터를 수정하려면 수정하는 명령이 따로 없어서 해당 자료를 삭제한 후 추가해주는 방법 사용
		// 삭제하는 메서드 => remove(삭제할자료) // 반환값 : 삭제성공시 (true), 실패시 (false)
		// 전체 삭제하는 메서드 => clear()

		// "FF"데이터를 "EE"로 변경하기
		System.out.println();
		hs1.remove("FF");
		System.out.println("삭제 후 set 데이터 => " + hs1);
		hs1.add("EE");
		System.out.println("set 데이터 => " + hs1);
//		hs1.clear();
//		System.out.println("clear 후 set 데이터 => " + hs1);
		System.out.println();

		// Set의 데이터는 순서(index)가 없기 때문에 List처럼 인덱스로 데이터를 하나씩 불러올 수 없다
		// 그래서 데이터를 하나씩 차례로 얻기 위해서 Iterator형 객체로 변환해야한다

		// - Set형 데이트들을 Interator형의 객체로 변환하는 메서드 : iterator()
		Iterator it = hs1.iterator(); // Set 데이터를 Iterator로 변환

		// Iterator객체의 hasNext()메서드 : Iterator의 포인터가 가리키는 곳 다음번째에 데이터가 있는지 검사한다
		// 반환값: 데이터가 있으면 true, 없으면 false 반환
		System.out.println("Iterator를 이용한 데이터 출력:");
		while (it.hasNext()) { // 있는지 검사후 참(있다)이면 실행, 그리고 알아서 다음으로 이동
			// Iterator객체의 next() : Iterator의 포인터를 다음번째 위치로 이동한 후 이동한 위치의 데이터를 반환한다
			System.out.println(it.next()); // next가 데이터 꺼내감
		}
		System.out.println("향상된 for문 이용");
		for (Object obj : hs1) { // hashSet에 제네릭을 쓰지 않아 저장되는 데이터들의 타입이 Object
			System.out.println(obj);
		}

		// 우리반 학생들 중 번호를 이용하여 추첨하는 프로그램을 작성하라
		// 번호는 1 ~23번까지 있고 추첨 인원은 3명이다
		// 당첨 번호를 출력하라

		// 최소값부터 최대값 사이의 정수형 난수값 만들기
		// (int)(Math.random()*(최대값-최소값+1)+최소값)

		HashSet<Integer> testSet = new HashSet<Integer>();
		while (testSet.size() < 3) {
			testSet.add((int) (Math.random() * (23) + 1));
			// 만약 같은 값의 난수가 생겨도 set에 집어넣을때 중복값을 저장하지 않기 때문에 중복값이 없는 난수 배열이 만들어진다
		}
		System.out.println("당첨자번호 : " + testSet);

		// Set유형의 자료를 List형으로 변환하기
		ArrayList<Integer> testList = new ArrayList<Integer>(testSet); // () <= 소괄호에 set의 이름을 넣어준다
		System.out.println("List 데이터 출력");
		for (int i = 0; i < testList.size(); i++) {
			System.out.println(i + "번째 자료 : " + testList.get(i));
		}
	}

}
