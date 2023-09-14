package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/*
 *  문제1 ) 5명의 별명을 입력받아 ArrayList에 추가한 후 이들 중
 *        별명의 길이가 제일 긴 별명을 출력하라
 *		  (단, 각 별명의 길이는 모두 다르게 입력한다)
 *  문제2 ) 문제1에서 입력할 때 각 별명의 길이가 같은 것이 있을 경우에 대하여 처리하라
 */

public class ArrayListTest03 {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		ArrayList<String> nickNMList = new ArrayList<String>();

		System.out.println("각 다른 길이의 별명을 5개 입력하세요");
		for (int i = 1; i <= 5; i++) {
			System.out.print(i + "번째 사람 별명 : ");
			String nickNM = scan.nextLine();
			nickNMList.add(nickNM);
		}
		System.out.println();

		// 제일 긴 별명이 저장될 변수 선언 : 이 변수는 List의 첫번째 데이터로 초기화한다.
		String maxnickNM = nickNMList.get(0);

		for (int i = 1; i < nickNMList.size(); i++) { // 위에 변수에서 0번째를 제일 길다고 가정하고 대입했기 때문에 1번째부터 시작한다
			if (maxnickNM.length() < nickNMList.get(i).length()) { 
				maxnickNM = nickNMList.get(i); // 하나씩 비교하면서 두개를 비교할때 크면 이 변수에 집어넣음, 모든 번째를 비교하면 제일 큰 데이터가 남음
			}
		}
		System.out.println("제일 긴 별명 : " + maxnickNM);
	}

}
