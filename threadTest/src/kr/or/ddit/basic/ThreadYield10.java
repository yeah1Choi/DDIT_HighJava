package kr.or.ddit.basic;

//yield() 메서드 연습
public class ThreadYield10 {

	public static void main(String[] args) {
		YieldRestThread th1 = new YieldRestThread("1번쓰레드");
		YieldRestThread th2 = new YieldRestThread("2번쓰레드");

		th1.start();
		th2.start();

		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("************************** 11111111111111111111");

		th1.work = false;

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("************************** 22222222222222222222222");
		
		th1.work = true;
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("************************** 333333333333333333333333");
		
		th1.stop = true;
		th2.stop = true;
	}
}

// yield() 메서드 연습용 쓰레드
class YieldRestThread extends Thread {
	public boolean stop = false;
	public boolean work = true;

	// 생성자
	public YieldRestThread(String name) {
		super(name); // Thread의 이름을 설정한다
	}

	@Override
	public void run() {
		while (!stop) {
			if (work) {
				System.out.println(getName() + "작업 중..");
			} else {
				System.out.println(getName() + "양보..");
				Thread.yield();
			}
		}
	}
}