package kr.or.ddit.basic;

/*
1 ~ 20억까지의 합계를 구하는 프로그램을
하나의 쓰레드가 단독으로 처리할 때와
   여러 쓰레드가 협력해서 처리할 때의 경과시간을 비교
 */
public class ThreadTest04 {

	public static void main(String[] args) {
		// 단독으로 처리하는 쓰레드 객체 생성
		SumThread sm = new SumThread(1L, 2_000_000_000L);

		// 협력해서 처리할 쓰레드 객체 생성 - 각 객체 생성해서 객체 배열로 저장
		SumThread[] sumArr = new SumThread[] { new SumThread(1L, 500_000_000L),
				new SumThread(500_000_000L, 1_000_000_000L), new SumThread(1_000_000_000L, 1_500_000_000L),
				new SumThread(1_500_000_000L, 2_000_000_000L) };

		///////////////////////////////////////////////

		// 단독으로 처리하기
		long startTime = System.currentTimeMillis();

		sm.start();

		try {
			sm.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}

		long endTime = System.currentTimeMillis();

		System.out.println("단독으로 처리한 경과 시간 : " + (endTime - startTime));

		System.out.println();
		System.out.println("------------------------------------------");

		// 협력해서 처리하기
		startTime = System.currentTimeMillis();
		
//		sumArr[0].start();
//		sumArr[1].start();
//		sumArr[2].start();
//		sumArr[3].start();
//		
//		sumArr[0].join();
//		sumArr[1].join();
//		sumArr[2].join();
//		sumArr[3].join();
				
		// 배열에서 쓰레드를 하나씩 꺼내 각각의 쓰레드들을 꺼냄
		for (SumThread th : sumArr) {
			th.start();
		}
		// 여기선 만들어낸 것이 아니라 배열형태에서 꺼내 각 join메소드를 주어 시간을 멈추게한다 - endtime 알려고
		for (SumThread th : sumArr) {
			try {
				th.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	
		endTime = System.currentTimeMillis();

		System.out.println("협력해서 처리한 경과 시간 : " + (endTime - startTime));
	}

}

class SumThread extends Thread {
	// 합계를 구할 영역의 시작값과 종료값이 저장될 변수 선언
	private long start;
	private long end;

	// 생성자
	public SumThread(long start, long end) {
		this.start = start;
		this.end = end;
	}

	// run() 재정의
	@Override
	public void run() {
		long sum = 0L;
		for (long i = start; i <= end; i++) {
			sum += i;
		}
		System.out.println(start + "부터" + end + "까지의 합계 : " + sum);
	}
}