package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
 *    *  문제2 ) 5명의 별명을 입력받아 ArrayList에 추가한 후 이들 중
 *       		별명의 길이가 제일 긴 별명을 출력하라
 *		  		(각 별명의 길이가 같을 수 있다)
 */

public class ArrayListText03_1 {

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

		// 제일 긴 별명의 길이가 저장될 변수 선언 : 이변수는 첫번째 데이터의 길이로 초기화한다
		int maxLength = nickNMList.get(0).length(); 
		// 데이터 값 자체로 초기화하면 정확히 하나의 데이터만 저장이 되서 길이로 비교 같은 길이가 있을 수 있으니까

		for (int i = 1; i < nickNMList.size(); i++) { // 길이로만 일단 큰 값을 구하는 for 구문
			if (maxLength < nickNMList.get(i).length()) {
				maxLength = nickNMList.get(i).length(); // 둘을 비교해 제일 큰 길이가 있으면 변수에 넣어줌
			}
		}

		System.out.println("제일 긴 별명들");
		// 앞에 식에서 배열에서 길이가 제일 긴 데이터가 maxLength변수에 저장된 상태
		
		for (int i = 0; i < nickNMList.size(); i++) { 
			if (maxLength == nickNMList.get(i).length()) { // 제일 긴 데이터와 같은 길이의 데이터가 있다면
				System.out.println(nickNMList.get(i)); // 그 데이터들을 출력
			}
		}
	}

}
