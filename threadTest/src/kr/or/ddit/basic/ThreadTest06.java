package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest06 {

	public static void main(String[] args) {
		Thread th1 = new DateInput();
		Thread th2 = new CountDown();

		th1.start();
		th2.start();
	}
}

// 데이터를 입력하는 쓰레드 클래스
class DateInput extends Thread {
	// 입력 완료 여부를 확인하기 위한 변수 선언 (쓰레드에서 공통으로 사용할 변수)
	public static boolean inputCheck = false; // static은 메소드영역에 저장 - thread 애들끼리 공유가 가능
	
	@Override
	public void run() {
		// 사용자로부터 데이터 입력받기
		String str = JOptionPane.showInputDialog("아무거나 입력~");
		
		// 이곳부터 입력을 받을 때까지 기다림
		
		inputCheck = true; // 입력이 완료되면 inputCheck변수를 true로 변경
		System.out.println("입력한 값 : " + str);
	}
}

// 카운트 다운을 진행하는 쓰레드 클래스
class CountDown extends Thread {
	@Override
	public void run() {
		for (int i = 10; i >= 1; i--) {
			
			// 입력이 완료되었는지 여부 검사 => 입력완료 후 쓰레드 종료
			// run메소드가 종료되면 쓰레드도 소멸, 삭제됨
			if(DateInput.inputCheck ==true) {
				return; // return:메소드를 끝냄
			}
			
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println("10초가 지났습니다. 프로그램을 종료합니다.");
		System.exit(0);
	}
}