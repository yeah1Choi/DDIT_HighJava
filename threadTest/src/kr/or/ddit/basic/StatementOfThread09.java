package kr.or.ddit.basic;

/*
 쓰레드의 상태를 확인하는 예제 
*/
public class StatementOfThread09 {

	public static void main(String[] args) {
		StatePrintThread th = new StatePrintThread(new TargetThread());

		th.start();
	}
}

// 쓰레드상태의 검사 대상이 되는 쓰레드 
class TargetThread extends Thread {
	@Override
	public void run() {
		for (long i = 1L; i <= 20_000_000_000L; i++) {
			long a = 10 + i;
		}
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		for (long i = 1L; i <= 20_000_000_000L; i++) {
			long a = 10 + i;
		}
	}
}

// 위의 TargetThread의 상태를 출력하는 쓰레드
class StatePrintThread extends Thread {
	private TargetThread target;

	// 생성자
	public StatePrintThread(TargetThread target) {
		this.target = target;
	}

	@Override
	public void run() {
		while (true) {
			// 쓰레드의 상태값 구하기 - 열거형값이 나옴
			Thread.State state = target.getState();
			System.out.println("TargetThread의 상태값 : " + state);

			if (state == Thread.State.NEW) { // 쓰레드의 상태 NEW일때(쓰레드가 생성되고 아직 start()호출안됨)
				target.start();
			}
			if (state == Thread.State.TERMINATED) { // 쓰레드의 상태 TERMINATED일때(쓰레드 작업 종료)
				break;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO: handle exception
			} // => 0.5초간 현 쓰레드를 잠깐 멈춰 번갈아 일을 실행하게함
		}
	}
}