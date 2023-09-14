package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BaseballTestAnswer {

	private ArrayList<Integer> numList; // 난수가 저장될 리스트
	private ArrayList<Integer> userList; // 사용자가 입력한 값이 저장될 리스트

	private int strike, ball;

	private Scanner scan = new Scanner(System.in);

	// 1~9 사이의 서로 다른 난수 3개를 만들어서 리스트에 저장하는 메서드 : Set 이용
	public void createNum() {
		Set<Integer> numSet = new HashSet<Integer>();

		// 난수 3개 만들기
		while (numSet.size() < 3) {
			numSet.add((int) (Math.random() * 9 )+ 1);
		}

		// 만들어진 난수를 List에 저장한다
		numList = new ArrayList<Integer>(numSet);

		// List의 데이터를 섞어준다
		Collections.shuffle(numList);
	}

	// 사용자로부터 3개의 정수를 입력받아 리스트에 저장하는 메서드
	// (입력한 값들은 중복되지 않게 한다)
	public void inputNum() {
		int n1, n2, n3; // 입력한 정수가 저장될 변수 선언

		do {
			System.out.println("숫자입력 >>");
			n1 = scan.nextInt();
			n2 = scan.nextInt();
			n3 = scan.nextInt();

			// 입력한 정수의 중복을 잡아줌 - 안내문
			if (n1 == n2 || n1 == n3 || n2 == n3) {
				System.out.println("중복되는 숫자는 입력할 수 없습니다. 다시 입력하세요.");
			}

		} while (n1 == n2 || n1 == n3 || n2 == n3); // 중복되면 다시 시작

		// 입력한 데이터를 List에 저장한다
		userList = new ArrayList<Integer>();
		userList.add(n1);
		userList.add(n2);
		userList.add(n3);
	}

	// 스트라이크와 볼을 구분해 저장하는 메서드
	public void BallCount() {
		strike = 0;
		ball = 0;

		for (int i = 0; i < numList.size(); i++) {
			for (int j = 0; j < userList.size(); j++) {
				if (numList.get(i) == userList.get(j)) { // 값이 같은지 비교
					if (i == j) { // 위치가 같은지 비교
						strike++;
					} else {
						ball++;
					}
				}
			}
		}

		// 볼카운트 결과를 출력한다
		System.out.println(
				userList.get(0) + " " + userList.get(1) + " " + userList.get(2) + " => " + strike + "S, " + ball + "B");
	}

	// 게임을 진행하는 메서드
	public void startGame() {
		// 난수를 만드는 메서드 호출
		createNum();

		// 만들어진 난수값 확인하기
		// System.out.println("컴퓨터의 난수 : " + numList);

		int cnt = 0; // 몇번만에 맞췄는지 저장하는 변수

		do {
			cnt++;

			// 사용자 입력 메서드 호출
			inputNum();

			// 볼카운트 계산 메서드 호출
			BallCount();

		} while (strike != 3); // 3번의 스트라이크가 될 때까지 반복

		System.out.println();
		System.out.println("축하합니다~ *^^*");
		System.out.println("당신은 " + cnt + "번째 만에 맞췄습니다.");
	}

	public static void main(String[] args) {
		BaseballTestAnswer b = new BaseballTestAnswer(); // 클래스 인스턴스 생성
		b.startGame(); // 인스턴스를 통해 막혀있는 void 메소드 호출할 수 있다

	}

}
