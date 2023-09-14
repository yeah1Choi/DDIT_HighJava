package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
 *  문제 ) 5명의 사람이름을 입력받아 ArraryList에 추가한 후 
 *  	  ArrayList에 저장된 이름들 중 김씨 성을 가진 이름을 출력하라
 *  	  (단, 입력은 Scanner 객체를 이용한다)
 */
public class ArrayListTest02 {

	public static void main(String[] args) {

		// 1)
//		Scanner sc = new Scanner(System.in);
//		ArrayList<String> names = new ArrayList<>();
//
//		for (int i = 1; i <= 5; i++) {
//			System.out.print("이름을 입력 : ");
//			String name = sc.nextLine();
//			names.add(name);
//		}
//
//		System.out.println("김씨 성을 가진 이름: ");
//		for (String name : names) {
//			if (name.startsWith("김")) {
//				System.out.println(name);
//			}
//		}

		Scanner scan = new Scanner(System.in);
		ArrayList<String> nameList = new ArrayList<String>();

		System.out.println("5명의 이름을 입력하세요");
		for (int i = 1; i <= 5; i++) {
			System.out.print(i + "번째 사람 이름 : ");
			String name = scan.nextLine();
			nameList.add(name);
		}

		System.out.println();
		System.out.println("김씨 성을 가진 사람들");
		for (int i = 0; i < nameList.size(); i++) {
			
		// 2) substring()
//			if (nameList.get(i).substring(0, 1).equals("김")) {
//				System.out.println(nameList.get(i));
//			}
//		}

		// 3) charAt()
//			if(nameList.get(i).charAt(0) == '김') {
//				System.out.println(nameList.get(i));
//			}
//		}

		// 4) startsWith()
//			if (nameList.get(i).startsWith("김")) {
//				System.out.println(nameList.get(i));
//			}
//		}
		
		// 5) indexOf()
			if (nameList.get(i).indexOf("김") == 0) {
				System.out.println(nameList.get(i));
			}
		}
	}
}