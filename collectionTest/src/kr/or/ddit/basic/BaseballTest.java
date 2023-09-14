package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

// 문제 ) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오
// 1. 임의의 숫자 3개를 지정해 답으로 둔다
// 2. 숫자만 맞추면 ball, 자리 수까지 맞추면 strike으로 갯수를 알려주고 이 힌트로 답을 맞추는 게임

// 컴퓨터가 만든 난수 1~9 사이의  3개 답으로 지정
// 숫자를 입력받아 숫자 맞추면 1B, 숫자와 자리 맞추면 1S로 알려준다
// 3S가 되면 다 맞춘 것으로 축하안내와 맞춘횟수 알려주기

public class BaseballTest {

	public static void main(String[] args) {
		// 중복이 없는 난수만들기
		HashSet<Integer> rdNum = new HashSet<Integer>();
		while (rdNum.size() < 3) {
			rdNum.add((int) (Math.random() * 9) + 1);
		}

		// 만든 난수를 리스트에 저장
		ArrayList<Integer> rdNumList = new ArrayList<Integer>(rdNum);
		 System.out.println("rdNumList" + rdNumList);

		int count = 0;
		System.out.println("정답은 중복이 없고, 1 ~ 9 사이의 숫자 3개로 이루어져있습니다! ");
		System.out.println("정답을 맞춰보세요 *^^*");
		while (true) {

			count++;
			// * 답변을 넣을 리스트
			int answer = 0;

			// 답을 받는 동시에 비교해서 볼과 스트라이크 횟수를 센다
			// * 볼과 스트라이크 횟수 변수
			int ball = 0;
			int strike = 0;

			ArrayList<Integer> asList = new ArrayList<Integer>(answer);

			Scanner sc = new Scanner(System.in);

			System.out.println();

			// 세개의 답을 받는다
			for (int i = 1; i <= 3; i++) {
				System.out.print(i + "번째 숫자입력 >> ");

				asList.add(sc.nextInt());
			}
			// System.out.println("asList" + asList);

			for (int i = 0; i <= 2; i++) {
				if (rdNumList.get(i).equals(asList.get(i))) {
					strike++;
				} else if (asList.contains(rdNumList.get(i))) {
					ball++;
				}
			}

			System.out.println("입력한 숫자의 결과 => ball : " + ball + "개, strike : " + strike + "개 입니다!");
			System.out.println();
			if (strike == 3) {
				System.out.println("3 strike !! 당신이 이겼습니다. 축하합니다.");
				System.out.println("맞춘 횟수는 총 " + count + "번 입니다.");
				break;
			}
		}

	}
}
