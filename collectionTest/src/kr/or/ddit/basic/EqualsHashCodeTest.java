package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Objects;

public class EqualsHashCodeTest {

	public static void main(String[] args) {
		Person p1 = new Person();
		// Person 객체의 참조값이 저장된 p1 변수 = 새로운 객체를 만든다 Person객체를 이 정보를 이용해 () 생성자를 만든다
		p1.setNum(1);
		p1.setName("홍길동");

		Person p2 = new Person();
//		p2.setNum(2);
//		p2.setName("임꺽정");
		p2.setNum(1);
		p2.setName("홍길동");

		Person p3 = p1;

		// '=='
		// : 참조값을 가지고 비교하는 연산자
		System.out.println(p1 == p2); // false
		System.out.println(p1 == p3); // true
		System.out.println();

		// 'equals'
		System.out.println(p1.equals(p2)); // false
		System.out.println(p1.equals(p3)); // true

		// 변수. 다음에는 속한 클래스에 속한 애들만 호출이 가능함
		// equals() 메소드는 속한 것이 없는데도 사용가능한 이유 :
		// 객체를 만들때 자바의 모든 객체는 Object 상속한다, equals() 또한 조상인 Object에 포함함
		// 'extends Object' <= 컴파일될 때 자동으로 붙어서 생략이 가능한 것

		// Object에 포함된 equals()는 '=='와 같은 대답을 함 - 참조값을 갖고 비교함

		// String 객체에서 equals()를 재정의해 사용하는 것이다

		HashSet<Person> testSet = new HashSet<Person>();
		testSet.add(p1);
		testSet.add(p2);
		System.out.println("set의 개수 : " + testSet.size());

		System.out.println("p1 : " + p1.hashCode());
		System.out.println("p2 : " + p2.hashCode());
		System.out.println("p3 : " + p3.hashCode());

		// - equals() 메서드 => 두 객체의 내용이 같은지 비교하는 메소드 (동등성비교)
		// - hashCode() 메서드 => 두 객체가 같은 객체인지를 비교하는 메서드 (동일성비교)
	}
}

class Person {

//	public Person() { // 기본 생성자 - 아무것도 안함 (보통 생략함)
//		super();
//		// TODO Auto-generated constructor stub
//	}

	private int num;
	private String name;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
		// 스택영역에는 각 메소드에는 기본으로 this(자기자신)가 있다 여기에 저장된 값은 새로운 객체에 저장된 참조값을 가르킨다
		// this.num의 num은 새 객체의 따라 온 변수 num이다
		// = num의 num은 같은 공간에 저장된 num이다 (여기에 저장된 값은 main에서 넣어준 값 1이 들어있다)
	} // 이 메소드가 끝나면 스택영역에서 공간 저장이 사라진다

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// num변수의 값과 name변수의 값 두가지 모두 같으면 true를 반환하도록 재정의해보자
	@Override
	public boolean equals(Object obj) {
		if (this == obj) { // 자기자신과 obj 참조값을 비교
			return true;
		}
		if (obj == null) {
			return false; // this는 값이 있기에 무조건 null이 될 수 없음?
		}
		if (this.getClass() != obj.getClass()) {
			return false; // 서로 다른 종류기 때문에 항상 false
		}
		Person that = (Person) obj;
		// 매개변수의 값을 현재 객체 유형으로 형변환한다 - 값을 손쉽게 꺼내올 수 잇다

		// 각 변수값들이 모두 같은지 검사한다
		return this.num == that.num && Objects.equals(this.name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(num, name);
	}
}