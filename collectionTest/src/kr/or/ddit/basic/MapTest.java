package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/*
 Map : key값과 value값을 한 쌍으로 관리하는 객체
- key값 : 중복을 허용하지 않고 순서(인덱스)가 없다 (set의 특징을 갖는다)
- value값 : 중복을 허용한다.
*/

public class MapTest {

	public static void main(String[] args) {
		HashMap<String, String> map = new HashMap<String, String>();

		// **자료추가 : put(key,value)
		map.put("name", "홍길동");
		map.put("add", "대전");
		map.put("tel", "010-1234-5678");

		System.out.println("map에 저장된 데이터의 갯수 : " + map.size());
		// map에 저장된 데이터의 갯수 : 3
		System.out.println("map : " + map);
		// map : {add=대전, name=홍길동, tel=010-1234-5678} - 추가한 대로 정렬되지 않음

		// **자료수정 : 데이터 추가할 때 key값이 같으면 추후 추가한 값으로 덮어쓴다
		map.put("add", "서울");
		System.out.println("map : " + map);
		// map : {add=서울, name=홍길동, tel=010-1234-5678}

		// **자료삭제 : remove(key값)
		// key값이 같은 자료를 찾아서 삭제 // 반환값 : 삭제된 자료의 value값
		String removeTel = map.remove("tel");
		System.out.println("map : " + map);
		// map : {add=서울, name=홍길동}
		System.out.println("삭제된 값(반환값) : " + removeTel);
		// 삭제된 값(반환값) : 010-1234-5678

		// **자료읽기 : get(key값)
		// key값과 같이 저장된 value값을 반환한다
		System.out.println("이름 : " + map.get("name"));
		// 이름 : 홍길동
		System.out.println();

		// **키값의 존재여부 확인 : containsKey(key값)
		// 해당 key값이 있으면 true, 없으면 false
		System.out.println("tel 키값의 존재여부 확인 : " + map.containsKey("tel"));
		// tel 키값의 존재여부 확인 : false
		System.out.println("name 키값의 존재여부 확인 : " + map.containsKey("name"));
		// age 키값의 존재여부 확인 : true
		System.out.println();
		// Map에 저장된 모든 데이터를 차례로 가져와 처리하기

		System.out.println("-----------------------------------------");

		// 1. keySet() : Map의 모든 key값들을 읽어와 Set형으로 반환
		Set<String> keySet = map.keySet();
		Iterator<String> it = keySet.iterator();
		while (it.hasNext()) { // 포인터로
			String key = it.next();
			String value = map.get(key);
			System.out.println(key + " : " + value);
		}
		System.out.println("-----------------------------------------");

		// 2. keySet을 향상된 for문으로 처리하기
		for (String key : map.keySet()) {
			String value = map.get(key);
			System.out.println(key + " => " + value);
		}
		System.out.println("-----------------------------------------");

		// 2. value값만 읽어와 처리하기 => values()메서드 이용
		for (String value : map.values()) {
			System.out.println(value);
		}
		System.out.println("-----------------------------------------");
	}
}