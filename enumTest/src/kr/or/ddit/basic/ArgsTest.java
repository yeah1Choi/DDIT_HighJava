package kr.or.ddit.basic;

/*
접근제한자 반환값타입 메소드명(매개변수들...){

}
*/
public class ArgsTest {

//	public static void test(int a, int b) {
//		
//	}

	// 매개변수로 받은 정수들의 합계를 구하는 메서드
	// (단, 이정수들의 개수는 상황에 따라 달라진다)

	// 배열 이용하기
	public int sumArr(int[] data) {
		int sum = 0;

		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return sum;
	}

	// 가변형 인수 이용하기 :
	// 가변형인수는 메서드를 호출할 때의 사용되는 인수 개수가 호출할 때마다 다를때 사용
	// - 구현한 메서드 안에서는 배열로 처리된다
	// - 한가지 자료형(타입)만 사용할 수 있다
	public int sumArg(int... data) {
		int sum = 0;

		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return sum;
	}

	// 가변형 인수와 일반적인 인수를 같이 사용할 경우에는 가변형 인수를 제일 뒤쪽에 배치해야한다
	// => 일반적인 인수가 같은 타입일 경우, 가변형 인수가 맨 뒤 순서가 아니라면 
	//    갯수를 모르는 가변형인수과 그냥 일반인수를 구분할 수 없을 것
	public String SumArg2(String name, int... data) {
		int sum = 0;

		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return name + "의 점수 : " + sum;
	}

	public static void main(String[] args) {
//		test(10, 20);
		ArgsTest test = new ArgsTest();

		// 배열 선언과 초기화를 동시에 할 때
		int[] nums = { 100, 200, 300 };

		// 배열 선언과 초기화를 동시에도 나중에도 초기화 선언이 가능
//		int[] nums2 = new int[]{ 100, 200, 300 }; 
		int[] nums2;
		nums2 = new int[] { 100, 200, 300 };

		System.out.println(test.sumArr(nums));
		System.out.println(test.sumArr(new int[] { 1, 2, 3, 4, 5 })); // => 직접 초기화하는 방법
		System.out.println("-----------------------------------------------");
		System.out.println(test.sumArg(100, 200, 300, 400));
		System.out.println(test.sumArg(1, 2, 3, 4, 5));
		System.out.println("-----------------------------------------------");
		System.out.println(test.SumArg2("홍길동", 1, 2, 3, 4, 5));
	}
}