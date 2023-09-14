package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest01 {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		// ArrayList에 내부 정렬 기준이 이미 있음

		list.add("홍길동");
		list.add("이순신");
		list.add("성춘향");
		list.add("이몽룡");
		list.add("변학도");

		System.out.println("정렬 전 list : " + list);

		// 정렬은 Collections.sort() 메소드를 사용한다
		// Collections.sort() 메소드는 기본적으로 내부 정렬 기준으로 정렬한다
		Collections.sort(list);

		System.out.println("정렬 후 list : " + list);
		// 정렬 후 list : [변학도, 성춘향, 이몽룡, 이순신, 홍길동] <= 기본값인 오름차순을 기준으로 정렬됨

		Collections.shuffle(list); // 자료섞기
		System.out.println("자료 섞기 후 : " + list);

		// 외부 정렬 기준 클래스를 지정해 정렬
		Collections.sort(list, new Desc());
		System.out.println("내림차순 정렬 후 list : " + list);
	}
}

// 정렬 방식을 정해주는 클래스를 작성 (외부 정렬 기준 클래스라고 함)
// Comparator 인터페이스 구현해 작성
class Desc implements Comparator<String> {
	// compare()메서드의 매개변수는 서로 인접한 2개의 데이터가 저장된다고 가정하여 처리
	// compare()메서드의 반환값의 의미
	// 0 : 두 값이 같다 (순서 안 바꿈)
	// 양수 : 앞, 뒤의 순서를 바꿈
	// 음수 : 순서 안 바꿈

	// 예 ) 오름차순 정렬의 경우 - 앞의 값이 크면 양수, 같으면 0, 작으면 음수 반환하도록 구현
	@Override
	public int compare(String str1, String str2) {
		// 내림차순 정렬 구현하기
		/*
		 * if (str1.compareTo(str2) > 0) { return -1; } else if (str1.compareTo(str2) <
		 * 0) { return 1; } else { return 0; }
		 */
		return str1.compareTo(str2) * -1;
		// 위의 경우 음수면 양수가 되고, 양수면 음수, 0이면 0
	}
}