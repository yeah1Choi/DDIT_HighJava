package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

// **로또를 구매하는 프로그램 작성하기**
// 사용자는 로또를 구매할 때 구매할 금액을 입력하고 입력한 금액에 맞게 로또번호를 출력한다.
// (단, 로또 한장의 금액은 1000원이며 최대 100장까지만 구입할 수 있고, 거스름돈도 계산하여 출력한다.)

public class LottoEx {

	Scanner scan = new Scanner(System.in);

	private ArrayList<Integer> lottoNumList; // 로또번호가 저장될 리스트

	private int money;

	public void buyLotto() {
		System.out.println();
		System.out.println(" **Lotto 구입 시작** ");
		System.out.println();
		System.out.println("(1000원에 로또번호 하나입니다.)");
		System.out.print("금액 입력 : ");
		money = scan.nextInt();

		if (money < 1000) {
			System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!!!");
		} else if (money > 100000) {
			System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!!!");
		} else {

			HashSet<Integer> lottoSet = new HashSet<Integer>();

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
	}

	public static void main(String[] args) {
		Scanner scan1 = new Scanner(System.in);
		LottoEx l = new LottoEx();

		while (true) {
			System.out.println("==========================");
			System.out.println("Lotto 프로그램");
			System.out.println("--------------------------");
			System.out.println("1. Lotto 구입");
			System.out.println("2. 프로그램 종료");
			System.out.println("==========================");
			System.out.print(" 메뉴선택 : ");

			int choice = scan1.nextInt();
			scan1.nextLine();

			switch (choice) {
			case 1:
				l.buyLotto();
				break;
			case 2:
				System.out.println("\n감사합니다");
				System.exit(0);
				break;
			default:
				System.out.println("잘못된 메뉴 선택입니다.");
				break;
			}
			System.out.print("** 메뉴로 돌아가기 : Enter **");
			scan1.nextLine();
		}
	}
}
