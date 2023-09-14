package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest07Answer {

	public static boolean inputCheck = false;

	public static void main(String[] args) {
		GameCountDown gcd = new GameCountDown();

		// 난수를 이용하여 컴퓨터의 가위바위보 정하기
		String[] data = { "가위", "바위", "보" };
		int index = (int) (Math.random() * 3);
		String com = data[index];

		// 사용자로부터 가위바위보 입력받기
		gcd.start(); // 카운트다운 쓰레드 작동
		String man = null;
		do { // 일단 입력 값을 알아내기 전 입력을 받고 조건에 맞는 정확한 답을 얻기 위해 do while
			man = JOptionPane.showInputDialog("가위, 바위, 보를 중 하나를 내세요");
//		} while(!("가위".equals(man) || "바위".equals(man) || "보".equals(man))); 
		} while(!"가위".equals(man) && !"바위".equals(man) && !"보".equals(man)); 
		// while의 조건이 참이면 계속 반복됨
		inputCheck = true;

		// 결과 판정
		String result = "";

		if (man.equals(com)) {
			result = "당신은 비겼습니다.";
		} else if (man.equals("가위") && com.equals("보") || man.equals("바위") && com.equals("가위")
				|| man.equals("보") && com.equals("바위")) {
			result = "당신은 이겼습니다.";
		} else {
			result = "당신은 졌습니다.";
		}

		System.out.println("-- 결 과 --");
		System.out.println(" 컴퓨터 : " + com);
		System.out.println(" 당 신   : " + man);
		System.out.println(" 결 과   : " + result);
		return;
	}

}

class GameCountDown extends Thread {
	@Override
	public void run() {
		System.out.println("카운트 다운 시작 !");
		for (int i = 10; i >= 1; i--) {
			if (ThreadTest07Answer.inputCheck) {
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