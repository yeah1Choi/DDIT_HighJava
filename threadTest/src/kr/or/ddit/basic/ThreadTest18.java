package kr.or.ddit.basic;
/*
wait(), notify()를 이용한 두 쓰레드에서 번갈아 한번씩 실행하는 예제
( wait(), notify(), notifyAll()은 동기화 영역에서만 사용가능) 
 */
public class ThreadTest18 {

	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();
		
		ThreadA thA = new ThreadA(workObj);
		ThreadB thB = new ThreadB(workObj);
		
		thA.start();
		thB.start();
	}
}

// WorkObject의 methodA()메서드만 호출하는 쓰레드
class ThreadA extends Thread {
	private WorkObject workObj;

	//생성자
	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}
	@Override
	public void run() {
		for(int i=1;i<=10;i++) {
			workObj.methodA();
		}
		// 이 식의 프로그램이 종료가 되지 않는 이유: 전단계 반복에서 wait()으로 B메서드가 깨지 못한 상황 =>
		synchronized (workObj) {
			workObj.notify(); // 반복문을 돌고 실행에서 waiting room에서 빠져나갈 통지를 줌
		} // 동기화 영역에서만 쓸 수 있기 때문에 동기화 블록안에 명령문을 작성
	}
}

// WorkObject의 methodB()메서드만 호출하는 쓰레드
class ThreadB extends Thread {
	private WorkObject workObj;
	
	//생성자
	public ThreadB(WorkObject workObj) {
		this.workObj = workObj;
	}
	@Override
	public void run() {
		for(int i=1;i<=10;i++) {
			workObj.methodB();
		}
		synchronized (workObj) {
			workObj.notify();
		}
	}
}

// 공통으로 사용할 클래스
class WorkObject {
	public synchronized void methodA() {
		System.out.println("methodA()메서드 실행 중..");
		notify(); //
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
	
	public synchronized void methodB() {
		System.out.println("methodB()메서드에서 작업 중..");
		notify();
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
}