package kr.or.ddit.basic;

public class ThreadText03 {

	public static void main(String[] args) {
		// 쓰레드가 수행되는 시간을 체크하기 - 실행시작과 실행끝 시간을 알아내서 계산
		Thread th = new Thread(new MyRunner());

		// 1970년 1월 1일 0시 0분 0초(표준시간)부터 현재까지 경과한 시간을
		// 밀리세컨드(1/1000s) 단위로 반환한다 (예) 3분경과 : 3*60*1000

		long startTime = System.currentTimeMillis();

		th.start();
		
		try {
			th.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}

		long endTime = System.currentTimeMillis(); // run()의 실행시간이 아닌 start() 실행 준비 끝시간을 잼..

		System.out.println("경과시간 : " + (endTime - startTime));
	}

}

class MyRunner implements Runnable {
	@Override
	public void run() {
		long sum = 0;
		for (long i = 1L; i <= 1_000_000_000L; i++) {
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
}