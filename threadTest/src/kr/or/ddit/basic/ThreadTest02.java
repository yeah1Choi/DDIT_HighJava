package kr.or.ddit.basic;

/*
Thread를 사용하는 방법

1. 
  1) Thread 클래스를 상속한 class를 작성한 후 이 class의 인스턴스를 생성
  2) 생성된 인스턴스의 start() 메서드를 호출해 실행
2. 
  1) Runnable 인터페이스를 구현한 class를 작성한 후 이 class의 인스턴스를 생성
  2) Thread 클래스의 인스턴스를 생성할 때 Runnable 인터페이스를 구현한 class의
          인스턴스를 생성자의 인수값으로 넣어준다
  3) 생성된 Thread 클래스의 인스턴스의 start() 메서드를 호출해 실행
3.(방법2의 번외편) 익명 구현체를 이용하는 방법

'*'문자를 200개 출력하는 기능과 '$'문자를 200개를 출력하는 기능 구현하기
 */
public class ThreadTest02 {

	public static void main(String[] args) {
		// 멀티쓰레드 프로그램

		// 1-4. class의 인스턴스를 생성
		MyThread1 th1 = new MyThread1();
		// 1-5. 쓰레드 실행
		th1.start();

		// 2-4. Runnable 인터페이스를 구현한 클래스의 인스턴스 생성
		MyRunner2 r2 = new MyRunner2();
		// 2-5. Thread의 인스턴스를 생성할 때 Runnable 구현체의 인스턴스를 주입한다
		Thread th2 = new Thread(r2);
		// 2-6. 쓰레드 실행
		th2.start();

//		MyRunner2 rr = new MyRunner2(); // ok: 인스턴스 재사용이 가능

		// 방법 3 - 재사용할 수 없음
		Runnable r3 = new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 200; i++) {
					System.out.println("@");

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
				}
			}
		};
	}
}

// 방법 1
// 1-1. Thread 클래스를 상속한 class를 작성한다
class MyThread1 extends Thread {
	// 1-2. run() 메서드를 재정의한다
	@Override
	public void run() {
		// 1-3. 이 run()메서드 안에는 쓰레드에서 처리할 내용을 작성한다
		for (int i = 1; i <= 200; i++) {
			System.out.println("*");

			try {
				// * 주어진 (시간)동안 작업을 잠시 멈춘다.
				// 밀리세컨드 단위 => 1000는 1초
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}
}

// 방법 2
// 2-1. Runnable 인터페이스를 구현한 class를 작성한다
class MyRunner2 implements Runnable {
	// 2-2. run() 메서드를 재정의한다
	@Override
	public void run() {
		// 2-3. 이 run()메서드 안에는 쓰레드에서 처리할 내용을 작성한다
		for (int i = 1; i <= 200; i++) {
			System.out.println("$");

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}
}