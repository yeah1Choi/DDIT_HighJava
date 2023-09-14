package kr.or.ddit.basic;

/*
Thread의stop()메서드를 호출하면 쓰레드가 바로 멈춘다
이때 사용하던 자원을 정리하지 못하고 쓰레드가 종료되어
다른 쓰레드의 실행에 영향을 줄 수 있어 쓰는 것을 꺼린다
*/
public class ThreadTest13 {

	public static void main(String[] args) {
		ThreadStopTest01 th1 = new ThreadStopTest01();
//		th1.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}

		// = th1.stop();
		th1.setStop(true);

		///////////////////////////////////////////////////
		
		ThreadStopTest02 th2 = new ThreadStopTest02();
		th2.start();
	}

}

// 쓰레드를 멈추게하는 연습용 쓰레드
class ThreadStopTest01 extends Thread {
	private boolean stop;

	// setter - 값을 true로 바꿔 멈추게 해보자
	public void setStop(boolean stop) {
		this.stop = stop;
	}

	@Override
	public void run() {
		while (!stop) {
			System.out.println("쓰레드의 처리내용 실행 중..");
		}
		System.out.println("사용하던 자원을 정리한다..");
		System.out.println("쓰레드 종료");
	}
}

// interrupt()메서드를 이용하여 쓰레드를 멈추게하는 연습용 쓰레드
class ThreadStopTest02 extends Thread {
	@Override
	public void run() {
		// 방법 1 => interrupt()메서드와 sleep()메서드를 이용하는 방법
/*		try {
			while (true) {
				System.out.println("쓰레드의 처리내용 실행 중..");
				Thread.sleep(1); // 여기서 InterruptedException 예외가 발생하면 무한루프빠짐
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("사용하던 자원을 정리한다..");
		System.out.println("쓰레드 종료");
*/		
		// 방법 2 
		while(true) {
			System.out.println("쓰레드의 처리내용 실행 중..");
			// interrupt()메서드 호출되었는지 여부 검사
			// 검사방법1 : 쓰레드의 인스턴스 메서드 중 isInterrupted()메서드 이용
			//  *isInterrupted() : interrupt()메서드가 호출되면 참을 반환
/*			if(this.isInterrupted()) {
				break;
			}
		}
*/
			// 검사방법2 : 쓰레드의 정적(static)메서드인 interrupted()메서드를 이룔
			//  *interrupted() : interrupt()메서드가 호출되면 참을 반환
			if(Thread.interrupted()) {
				break;
			}
		}
		System.out.println("사용하던 자원을 정리한다..");
		System.out.println("쓰레드 종료");
	}
}