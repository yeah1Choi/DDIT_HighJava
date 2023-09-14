package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class LottoScore {

	private Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		new LottoScore().startLottoScore();

	}

	// 시작메서드
	public void startLottoScore() {
		while (true) {
			int choice = displayMenu();

			switch (choice) {
			case 1: // 구입
				buyLotto();
				break;
			case 2:
				System.out.println();
				System.out.println("감사합니다");
				return;
			default:
				System.out.println("잘못된 번호 입니다");
				System.out.println("작업 번호는 1 또는 2를 입력하세요");
			}
		}
	}

	// 로또 구입을 처리하는 메서드
	private void buyLotto() {
		System.out.println();
		System.out.println("Lotto 구입 시작");
		System.out.println();
		System.out.println("1000원에 로또번호 하나입니다");
		System.out.print("금액 입력 : ");
		int money = scan.nextInt();

		System.out.println();
		if (money < 1000) { // 입력금액 1000 미만 검사
			System.out.println("입력금액이 모자랍니다. 구입 실패했습니다");
			return;
		}
		if (money > 101000) { // 입력금액 101000 이상 검사
			System.out.println("입력금액이 초과했습니다. 구입 실패했습니다");
			return;
		}

		// --------------------------------------------------
		// 금액에 맞는 로또 번호 만드는 메소드
		HashSet<Integer> lottoSet = new HashSet<Integer>();

		// 입력한 금액을 이용하여 구입할 로또의 매수를 구한다
		int count = money / 1000;

		System.out.println();
		System.out.println("행운의 로또번호는 아래와 같습니다");

		for (int i = 0; i <= count; i++) { // 로또의 매수만큼 반복
			// 1장분의 로또번호를 만든다 (1~45 사이의 중복되지 않는 번호 6개 만들기)
			while (lottoSet.size() < 6) {
				lottoSet.add((int) (Math.random() * 45) + 1);
			}
			// Set에 저장된 로또번호를 갖는 List객체 생성
			ArrayList<Integer> lottoList = new ArrayList<Integer>(lottoSet);

			Collections.sort(lottoList);

			System.out.println("로또번호 " + i + ":" + lottoList);

			// 작업에 사용한 Set객체를 비워준다
			lottoSet.clear();
		}
	}

	// 메뉴 출력 및 작업번호를 입력받아 반환하는 메서드
	private int displayMenu() {
		System.out.println("==========================");
		System.out.println("Lotto 프로그램");
		System.out.println("--------------------------");
		System.out.println("1. Lotto 구입");
		System.out.println("2. 프로그램 종료");
		System.out.println("==========================");
		System.out.print(" 메뉴선택 : ");
		return scan.nextInt();
	}
}
