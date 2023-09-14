package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest02 {

	public static void main(String[] args) {
		ArrayList<Member> memList = new ArrayList<Member>();

		memList.add(new Member(5, "홍길동", "010-1111-1111"));
		memList.add(new Member(3, "이순신", "010-2222-2222"));
		memList.add(new Member(2, "성춘향", "010-3333-3333"));
		memList.add(new Member(1, "강감찬", "010-4444-4444"));
		memList.add(new Member(4, "일지매", "010-5555-5555"));
		memList.add(new Member(6, "변학도", "010-6666-6666"));

		System.out.println("정렬 전 memList: " + memList);
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("-------------------------------------");

		// Member의 num값의 내림차순으로 정렬하는 외부 정렬 기준 클래스를 이용해 정렬하라
		// (클래스명 : SortNumDesc)

		Collections.sort(memList, new SortNumDesc());

		System.out.println("num의 내림차순 정렬 후..");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("-------------------------------");

		Collections.sort(memList);
		System.out.println("name의 오름차순 정렬 후..");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("-------------------------------");

		// Collections.sort(memList);
		// memList에는 Member객체가 들어가지 않으면 sort에 오류
		// -> 여기 객체들은 다양한 타입의 변수가 포함 어떤 것을 기준으로 정렬할지 모름
		//  내부정렬기준을 개발자가 직접 정해야함
	}

}

// Member의 회원이름(name)을 기준으로 오름차순이 되도록 내부 정렬 기준 추가하기
class Member implements Comparable<Member> {
	private int num;
	private String name;
	private String tel;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	// 자동으로 생성자 만들기 : alt+shift+S -> generate constructor ~~
	// 생성자
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	// 내부 정렬 기준을 정해주는 메서드 (name의 오름차순 정렬 기준 만들기)
	@Override
	public int compareTo(Member mem) {
		/*
		 * if (this.getName().compareTo(mem.getName())>0) { return 1; } else
		 * if(this.getName().compareTo(mem.getName())<0) { return -1; } else { return 0;
		 * }
		 */
		return this.getName().compareTo(mem.getName());
	}
}

// Member의 num값의 내림차순으로 정렬하는 외부 정렬 기준 클래스
class SortNumDesc implements Comparator<Member> {
	@Override
	public int compare(Member mem1, Member mem2) {
		// Member의 num값의 내림차순으로 정렬
		/*
		 * if (mem1.getNum() > mem2.getNum()) { return -1; } else if (mem1.getNum() <
		 * mem2.getNum()) { return 1; } else { return 0; }
		 */

		// Wrapper 클래스를 이용하는 1번째 방법
		// return new Integer(mem1.getNum()).compareTo(mem2.getNum())*-1; // * -1:
		// 오름차순이라서

		// Wrapper 클래스를 이용하는 2번째 방법
		return Integer.compare(mem1.getNum(), mem2.getNum()) * -1;
	}
}