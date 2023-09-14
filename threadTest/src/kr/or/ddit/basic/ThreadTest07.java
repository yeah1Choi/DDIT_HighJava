package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
컴퓨터와 가위바위보 게임을 하는 프로그램을 작성

컴퓨터의 가위바위보는 난수를 이용해 정하고
사용자의 가위바위보는 showInputDialog()메서드를 이용하여 입력

입력 시간은 5초로 제한해 카운트 다운을 진행
5초 안에 입력이 없으면 게임에 진것으로 처리해 프로그램을 종료

5초 안에 입력이 있으면 승패를 구해서 결과를 출력

결과예시)
1. 5초 안에 입력을 못한 경우
	-- 결 과 --
 시간 초과로 당신은 졌습니다
 
2. 5초 안에 입력을 한 경우
	-- 결 과 --
        컴퓨터 : 가위
         당 신  : 바위
         결 과  : 당신이 이겼습니다
*/
public class ThreadTest07 {

	public static void main(String[] args) {
		Thread th1 = new Gamer();
		Thread th2 = new CountDown01();

		System.out.println("   **가위 바위 보 게임**");

		th1.start();
		th2.start();
		
		try {
			th1.join();
			th2.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		// 컴퓨터의 난수를 발생
		String[] choices = { "가위", "바위", "보" };
		int computerIndex = (int) (Math.random() * 3);
		String comInput = choices[computerIndex];

		if (Gamer.inputCheck) {

			String g = Gamer.gamerInput;
			String r = "";

			if (g.equals(comInput)) {
				r = "당신은 비겼습니다.";
			} else if (g.equals("가위") && comInput.equals("보")) {
				r = "당신은 이겼습니다.";
			} else if (g.equals("바위") && comInput.equals("가위")) {
				r = "당신은 이겼습니다.";
			} else if (g.equals("보") && comInput.equals("바위")) {
				r = "당신은 이겼습니다.";
			} else {
				r = "당신은 졌습니다.";
			}

			System.out.println("-- 결 과 --");
			System.out.println(" 컴퓨터 : " + comInput);
			System.out.println(" 당 신   : " + g);
			System.out.println(" 결 과   : " + r);
			return;
		}
	}
}

// 사용자가 입력하는 클래스
class Gamer extends Thread {
	public static boolean inputCheck = false;
	public static String gamerInput = "";

	@Override
	public void run() {
		gamerInput = JOptionPane.showInputDialog("가위, 바위, 보를 중 하나를 내세요");
		inputCheck = true;
//		if(inputCheck) { return; } // 오류 : 메서드를 종료하면 값을 꺼내쓸 수 없음
		if(inputCheck) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
}

// 카운트 다운을 하는 클래스
class CountDown01 extends Thread {
	@Override
	public void run() {
		for (int i = 5; i >= 1; i--) {
			if (Gamer.inputCheck) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println("    -- 결 과 --");
		System.out.println("시간 초과로 당신은 졌습니다");
		System.exit(0);
	}
}