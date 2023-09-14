package kr.or.ddit.basic;

// 은행의 입출금을 쓰레드로 처리하는 예제
public class SynchronizedBank16 { // 공통객체

	private int balance; // 잔액이 저장될 변수

	// getter and setter
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	// 입금을 처리하는 메서드
	public void deposit(int money) {
		balance += money;
	}

	// 출금을 처리하는 메서드(반환값: 출금성공시 참, 실패시 거짓)
	public synchronized boolean withdraw(int money) {
		if (balance >= money) {
			for (int i = 1; i <= 1000000; i++) { // 시간 지연용
				int a = i + 3;
			}

			balance -= money;
			System.out.println("메서드 안에서 balance : " + balance);
			return true; // return으로 lock이 풀리면서 메소드를 나감
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		SynchronizedBank16 acount = new SynchronizedBank16();

		acount.setBalance(10000); // 잔액을 10000원으로 셋팅

		// 익명 구현체로 쓰레드 구현
		Runnable test = new Runnable() {
			@Override
			public void run() {
				boolean result = acount.withdraw(6000); // 6000원을 출금하기
				System.out.println("쓰레드 안에서 result = " + result + ", 잔액 = " + acount.getBalance());
			}
		};
		// 익명 구현체를 이용한 Thread 객체 생성
		Thread th1 = new Thread(test);
		Thread th2 = new Thread(test);
		th1.start();
		th2.start();
	}
}