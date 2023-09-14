package kr.or.ddit.basic;

/*
 enum(열거형) 
 - 서로 관련있는 상수들의 집합을 나타낸다.
 - 클래스처럼 보이게하는 상수
      
 - 작성방법 : 
 	1. 일반 클래스처럼 독립된 java파일로 작성할 수 있다.
 	2. 하나의 java파일에 클래스와 같이 작성할 수 있다.
 	3. 클래스 안에 내부 클래스처럼 작성할 수 있다.
 	
 - 열거형의 속성 및 메서드
    1. name() : 열거형 상수의 이름을 문자열로 반환한다.
    2. ordinal() : 열거형 상수가 정의된 순서값(index값)을 반환한다. *0부터 시작
    3. valueOf("상수명") : 지정된 열거형에서 '상수명'과 일치하는 열거형 상수를 반환한다. 
    4. 열거형이름.상수명 : valueOf() 메소드와 같다
    5. 열거형이름.values() : 열거형의 모든 상수들을 배열에 담아 반환한다.
    
 - 열거형 선언 방법 :
    1. enum 열거형이름 { 상수명1, 상수명2, ..., 상수명n };
    2. 열거형 상수에 데이터를 셋팅해서 처리하는 방법 
     : enum 열거형이름 { 
			상수명1(값들...), ..., 상수명n(값들...) 
			// '값들'이 저장될 변수들을 선언한다. '값들'개수에 맞게 변수선언을 한다
			private 자료형이름 변수명1;
			private 자료형이름 변수명2;
			...
			// 열거형의 생성자를 만든다
			//  - 열거형의 생성자는 '열거형 상수에 지정된 값들'을 '변수'에 셋팅하는 역할을 한다
			//  - 열거형 생성자는 묵시적으로 접근 제한자가 'private'
			 
			// '변수명'은 '값들'과 개수가 같고, 각각의 '값들'과 자료형이 맞아야한다 
			private 열거형이름 (자료형 변수명, ...) { // 접근제한자는 기본적으로 private
				// 위에 선언한 변수들을 초기화하는 작업을 한다.
				...
			}
			// 위에서 구성된 '값들'을 외부에서 불러올 수 있는 getter 메소드를 작성한다
	   };
     
*/
public class EnumTest {

	public enum Color {
		RED, GREEN, BLUE
	}

	public enum Count {
		ONE, TWO, THREE
	}

	public enum Season {
		// 상수명(값들) 형식의 선언
		봄("3월부터 5월까지", 13), 여름("6월부터 8월까지", 30), 가을("9월부터 11월까지", 15), 겨울("12월부터 2월까지", 1);

		// 값들이 저장될 변수 선언
		private String span;
		private int temp;

		// 생성자
		Season(String span, int temp) { // private 생략가능
			this.span = span;
			this.temp = temp;
		}

		// getter 메서드
		public String getSpan() {
			return span;
		}

		public int getTemp() {
			return temp;
		}
	}

	public static void main(String[] args) {

//		System.out.println("RED : " + ConstTest.RED);
//		System.out.println("THREE : " + ConstTest.THREE);
//
//		if (ConstTest.RED == ConstTest.ONE) {
//			System.out.println("RED == ONE");
//		} else {
//			System.out.println("RED != ONE");
//		}

		Color mycolor = Color.valueOf("GREEN"); // Color.GREEN; 와 같다
		Count mycount = Count.ONE; // Count.valueOf("ONE") 와 같다

		System.out.println("mycolor: " + mycolor.name());
		System.out.println("mycount: " + mycount.name());

		System.out.println();

		System.out.println("mycolor ordinal : " + mycolor.ordinal());
		System.out.println("mycount ordinal : " + mycount.ordinal());

		System.out.println();

		// 서로 다른 종류의 열거형끼리의 비교는 불가
//		if(mycolor == mycount) { // 오류
//		}
		if (mycolor == Color.GREEN) {
			System.out.println("같다");
		}
		// 열거형을 switch문에서 사용할 때 case문에는 '열거형이름'을 생략한 '상수명'만 지정해 비교함
		switch (mycount) {
		case ONE:
			System.out.println("ONE 상수값");
			break;
		case TWO:
			System.out.println("TWO 상수값");
			break;
		case THREE:
			System.out.println("THREE 상수값");
			break;
		}

		System.out.println("----------------------------------------------");
		Season ss = Season.valueOf("봄");
		System.out.println("name : " + ss.name());
		System.out.println("ordinal : " + ss.ordinal());
		System.out.println("span : " + ss.getSpan());
		System.out.println("temp : " + ss.getTemp());
		System.out.println();

		for (Season time : Season.values()) {
			System.out.println(time.name() + "==" + time + "==> ( " + time.getSpan() + ", " + time.getTemp() + " )");
		}
	}
}