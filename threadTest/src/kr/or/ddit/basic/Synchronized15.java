package kr.or.ddit.basic;

public class Synchronized15 {

	public static void main(String[] args) {
		ShareObject sObj = new ShareObject(); // 공통객체생성

		TestThread th1 = new TestThread("test1", sObj);
		TestThread th2 = new TestThread("test2", sObj);

		th1.start();
		th2.start();
		
		// 10 안나옴, 10까지 계산하고 출력 전 다른 쓰레드로 넘어가져서 저장된 값 10에서 식 진행으로 20부터 출력이됨
		// => 쓰레드의 동기화: synchronization : 한 쓰레드가 특정 작업을 모두 마치기전까지 방해받지 못하게 함
	}
}

class TestThread extends Thread {
	private ShareObject sObj;

	// 
	public TestThread(String name, ShareObject sObj) {
		super(name); // 쓰레드의 name설정
		this.sObj = sObj;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			sObj.add();
		}
	}
}

// 공통으로 사용할 객체
class ShareObject {
	private int sum = 0;
/*
	// 방법1 : 메서드에 동기화 설정 : 메소드 앞에 'synchronized' : 메소드 전체가 lock걸림
	public synchronized void add() {
		int n = sum;
		n += 10; // 10 증가 - 쓰레드 실행이 되면서 다른 쓰레드로 넘어갈 시점을 많이 만들기 위해 풀어씀
		sum = n;
		System.out.println(Thread.currentThread().getName() + "합계 : " + sum);
	} 
	// 쓰레드가 여기 메소드 영역에 들어오면 다른 쓰레드가 호출이 되어 이 작업을 하려고 와도 막혀서 방해를 막고 
	// 한 쓰레드가 작업을 끝내면 그때 lock 반환되면서 일처리를 다 마칠 수 있음
*/	
	// 방법2 : 동기화 블록(참조변수)을 이용해 동기화 설정 : 블록 안에 들어가야 동기화
	public void add() {
		synchronized (this) { // 참조변수는 락을 걸고 싶은 객체를 참조
			int n = sum;
			n += 10; // 10 증가 - 쓰레드 실행이 되면서 다른 쓰레드로 넘어갈 시점을 많이 만들기 위해 풀어씀
			sum = n;
			System.out.println(Thread.currentThread().getName() + "합계 : " + sum);
		}
	} 
}