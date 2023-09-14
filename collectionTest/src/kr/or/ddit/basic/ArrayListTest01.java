package kr.or.ddit.basic;

import java.util.ArrayList;

public class ArrayListTest01 {

	public static void main(String[] args) {
		// ArrayList의 기본적인 사용법은 Vector와 같다

		ArrayList list1 = new ArrayList();

		// 데이터 추가하기 : add()
		list1.add("aaa");
		list1.add("bbb");
		list1.add(123);
		list1.add('c');
		list1.add(false);
		list1.add(12.34);

		System.out.println("list1의 size : " + list1.size());
		System.out.println("list1 => " + list1);

		// 데이터 끼워넣기 : add
		list1.add(3, "ZZZ");
		System.out.println("수정 후 list1 => " + list1);

		System.out.println("----------------------------------------------------");

		// 데이터 꺼내기 : get()
		System.out.println("1번째 자료 : " + list1.get(1));

		System.out.println("----------------------------------------------------");

		// 데이터 변경하기 : set() , 반환값 : 변경전 데이터
		String sTemp = (String) list1.set(3, "HAPPY");
		System.out.println("변경 후 list1 => " + list1);
		System.out.println("sTemp : " + sTemp);

		System.out.println("----------------------------------------------------");

		// 데이터 삭제 : remove()
		list1.remove(3);
		System.out.println("3번째 삭제후 list1 => " + list1);

		list1.remove("bbb");
		System.out.println("bbb 삭제후 list1 => " + list1);

		System.out.println("----------------------------------------------------");

		// 제네릭 사용
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("AAA");
		list2.add("BBB");
		list2.add("CCC");
		list2.add("DDD");
		list2.add("EEE");

		for (int i = 0; i < list2.size(); i++) {
			System.out.println("[" + i + "]번째 데이터 : " + list2.get(i));
		}
		System.out.println();
		for (String str : list2) {
			System.out.println(str);
		}

		System.out.println("----------------------------------------------------");

		// contains(비교객체) => 리스트에 저장된 데이터 중에서 '비교객체'가 있으면 true, 없으면 false
		System.out.println("DDD 데이터의 존재여부 : " + list2.contains("DDD"));
		System.out.println("ZZZ 데이터의 존재여부 : " + list2.contains("ZZZ"));

		list2.add("AAA");
		list2.add("BBB");
		list2.add("CCC");
		list2.add("DDD");
		list2.add("EEE");

		// indexOf(비교객체) / lastIndexOf(비교객체)
		// : 리스트에 '비교객체'가 있으면 '비교객체'가 저장된 index값을 반환하고 없으면 -1을 반환한다.
		// indexOf()는 앞에서 뒤로 검색하고, lastIndexOf()는 뒤에서 앞으로 검색.
		// 찾은 데이터가 여러개면 먼저 찾은 데이터의 위치(index)값을 반환한다.

		System.out.println("list2 : " + list2);
		System.out.println("DDD의 위치값 : " + list2.indexOf("DDD"));
		System.out.println("ZZZ의 위치값 : " + list2.indexOf("ZZZ"));

		System.out.println("DDD의 위치값 : " + list2.lastIndexOf("DDD"));

		System.out.println("----------------------------------------------------");

		// toArray() => 리스트 안의 데이터를 배열로 변환해 반환
		// 반환되는 배열은 기본적으로 Object형 배열이 된다

		Object[] strArr = list2.toArray();
		// String[] strArr = (String[])list2.toArray(); // 실행시 오류, 사용할 수 없음
		
		// toArray(new 제네릭타입이름[0]) => 제네릭타입의 배열로 변환해 반환가능

		System.out.println("list2의 개수 : " + list2.size());
		System.out.println("배열의 개수 : " + strArr.length);

		for (int i = 0; i < strArr.length; i++) {
			System.out.println("[" + i + "]번째 데이터 : " + strArr[i]);
		}

		System.out.println("----------------------------------------------------");

		// 제네릭 타입의 배열로 변환해서 가져오기
		String[] strArr2 = list2.toArray(new String[0]);
		for (String s : strArr2) {
			System.out.println(s);
		}
	}
}