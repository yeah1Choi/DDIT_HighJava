package kr.or.ddit.basic;

import java.util.Vector;

public class VetorTest {

	public static void main(String[] args) {
		// 객체생성
		Vector v1 = new Vector();

		System.out.println("처음 크기 : " + v1.size());

		// 데이터 추가하기 1: add(추가할 데이터)
		// 반환값: 추가 성공시 (true), 추가 실패시 (false)
		v1.add("aaaa");
		v1.add(new Integer(111)); // 전에는 객체가 아닌 (기본타입인)정수를 저장할때
		v1.add(999); // 현재는 값에 따라 박싱을 알아서함(오토박싱), 반대인 오토언박싱도 지원
		v1.add('a');
		v1.add(true);

		boolean r = v1.add(3.14);

		System.out.println("현재 크기 : " + v1.size());
		System.out.println("반환값 : " + r); // true인 경우에 배열에 저장이 되는 것을 알수있음

		// 데이터 추가하기 2: addElement(추가할 데이터)
		// 이전 버전에서 addElement를 썼지만 현재는 저장시 모두 add사용할 수 있음, 현재 호환성이슈로 사용중

		v1.addElement("ccc");

		System.out.println("v1 => " + v1.toString()); // .toString() 생략가능

		// 데이터 추가하기 3: add(index, 추가할 데이터)
		// 'index'번째에 '추가할 데이터'를 대입, index는 0번부터 시작, 반환값은 없다

		v1.add(1, "AAA");

		System.out.println("v1 => " + v1);

		System.out.println();
		System.out.println("-------------------------------------------------------");
		System.out.println();
		
		// 데이터 꺼내오기 get(index)
		// 'index'번째의 데이터를 꺼내서 반환
		System.out.println("0번째 데이터 : " + v1.get(0));

		// int iTemp = v1.get(2); 배열에 저장된 값은 무조건 객체로 변환되어 있다 오토박싱 전 기본으로 넣었던 것을 기억하고 그냥
		// 꺼내면 안됨
		int iTemp = (int) v1.get(2); // Integer로 꺼내면 계산이 힘들어서 기본 int로 꺼냄
		System.out.println("2번째 데이터 : " + iTemp);

		System.out.println();
		System.out.println("-------------------------------------------------------");
		System.out.println();
		// 데이터 수정하기 set(index, 새로운데이터)
		// 'index'번째의 데이터를 '새로운데이터'로 변경
		// 반환값 : 변경되기 전의 원래 데이터가 반환
		String sTemp = (String) v1.set(0, "ZZZ");

		System.out.println("v1 => " + v1);
		System.out.println("원래의 데이터 : " + sTemp);

		System.out.println();
		System.out.println("-------------------------------------------------------");
		System.out.println();
		// 데이터 삭제하기 1: remove(index)
		// 반환값 : 삭제된 데이터
		v1.remove(0);
		System.out.println("삭제 후 v1 : " + v1);
		System.out.println("삭제된 데이터 : " + sTemp);

		sTemp = (String) v1.remove(0);
		System.out.println("또 삭제 후 v1 : " + v1);
		System.out.println("또 삭제된 데이터 : " + sTemp);

		// 데이터 삭제하기 2: remove(삭제할 데이터)
		// '삭제할데이터'를 찾아서 삭제, '삭제할데이터'가 여러개면 이중에 제일 앞의 자료가 삭제
		// 반환값 : 삭제성공시 (true), 삭제실패시 (false)
		// '삭제할데이터'가 정수형이거나 char형일 경우 반드시 객체 타입
		v1.remove("ccc");
		System.out.println("ccc 삭제 후 v1 => " + v1);

		// v1.remove(111); // 데이터 삭제시 정수를 받을 때 인덱스로 인지할 수 있다 - 오버라이딩
		// v1.remove(new Integer(111)); // 타입을 인지해야 배열의 데이터로 인지한다, 이 방법은 버전업뎃 후 사용이
		// 안되기도함
		v1.remove(Integer.valueOf(111)); // 이 방법으로 삭제하기
		System.out.println("111 삭제 후 v1 => " + v1);

		// v1.remove('a'); // char는 숫자형(컴퓨터는 처리위해 숫자로 바꿨기때문)으로 인지해 인덱스인지 알고 오류남
		v1.remove(Character.valueOf('a'));
		System.out.println("a 삭제 후 v1 => " + v1);

		v1.remove(3.14);
		System.out.println("3.14 삭제 후 v1 => " + v1);

		v1.remove(true);
		System.out.println("true 삭제 후 v1 => " + v1);

		// 전체 데이터 삭제하기 clear()
		v1.clear();
		System.out.println("전체삭제 후 v1 => " + v1);
		System.out.println("현재크기 : " + v1.size());

		System.out.println();
		System.out.println("-------------------------------------------------------");
		System.out.println();
		/*
		 * 제네릭타입(Generic Type) : 클래스 내부에서 사용할 데이터 타입을 객체를 생성할 때 외부에서 지정해주는 기법으로 객체를 선언할
		 * 때 괄호'<>' 안에 그 객체의 내부에서 사용할 데이터의 타입을 지정해주는 것을 말한다. 이런식으로 선언하게 되면 그 데이터 타입이외의
		 * 다른 종류의 데이터를 저장할 수 없다. 이럴때 제네릭으로 선언될 수 있는 데이터 타입이 클래스이어야한다. int -> Integer ..
		 * 이런식으로 대체해서 사용해야 한다. 제네릭 타입으로 선언하게 되면 데이터를 꺼내올 때 별도의 형변환이 필요없다.
		 */
		Vector<Integer> v2 = new Vector<Integer>(); // int형 자료만 저장가능한 Vector
		Vector<String> v3 = new Vector<String>(); // String형 자료만 저장가능한 Vector

		v3.add("Hello World");
		// v3.add(123); // 오류 : 지정한 제네릭 타입과 다른 종류의 데이터를 저장할 수 없다

		String sTemp2 = (String) v3.get(0); // 형변환없이 데이터를 꺼낼 수 있다.
		System.out.println("sTemp2 : "+sTemp2);

		Vector<Vector> vv = new Vector<Vector>(); // 벡터 타입의 데이터를 갖는 벡터, 2차원배열같은?
		Vector<Vector<Vector>> vvv = new Vector<Vector<Vector>>(); // 3차원배열같은?, 사용가능하긴함

		System.out.println();
		System.out.println("-------------------------------------------------------");
		System.out.println();

		v3.clear();
		System.out.println("v3의 size : " + v3.size());

		v3.add("AAA");
		v3.add("BBB");
		v3.add("CCC");
		v3.add("DDD");
		v3.add("EEE");

		Vector<String> v4 = new Vector<String>();
		v4.add("BBB");
		v4.add("EEE");

		System.out.println("v3 => " + v3);
		System.out.println("v4 => " + v4);

		// 데이터 삭제하기 3: removeAll(Collection 객체)
		// 벡터의 데이터 중에서 'Collection 객체'가 갖고 있는 모든 데이터 삭제
		// 반환값 : 삭제성공시(true), 삭제실패시(false)
		v3.removeAll(v4); // v3이 갖고있는 데이터 중에서 v4가 갖고 있는 데이터와 같은 데이터를 삭제
		System.out.println("삭제 후 v4 => " + v4); // 변동이 없음, 삭제는 v3 기준으로 v3만 삭제됨
		System.out.println("삭제 후 v3 => " + v3);

		System.out.println();
		System.out.println("-------------------------------------------------------");
		System.out.println();

		v3.clear();
		v3.add("AAA");
		v3.add("BBB");
		v3.add("CCC");
		v3.add("DDD");
		v3.add("EEE");

		// 벡터에 저장된 데이터들을 순서대로 가져와 사용하고 싶으면 반복문을 상용 - 주로 for문을 사용
		for (int i = 0; i < v3.size(); i++) {
			System.out.println("[" + i + "]번째 데이터 : " + v3.get(i));
		}
		System.out.println();
		for (String str : v3) {
			System.out.println(str);
		}
	}
}