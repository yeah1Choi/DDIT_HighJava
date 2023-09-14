package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
10마리의 말들이 경주하는 프로그램 작성

말은 Horse라는 이름의 쓰레드 클래스로 작성하는데 
이 클래스는 말이름(String), 등수(int), 현재위치(int)를 멤버변수로 가진다.
등수를 오름차순으로 처리할 수 있는 내부 정렬 기능이 있다 (Comparable 인터페이스 구현)

1. 경기 구간은 1~50구간으로 되어있다
2. 경기가 끝나면 등수 순으로 출력한다
3. 경기 중 중간에 아래와 같이 각 말들의 위치를 나타내시오

예시)
 1번말 : -------->--------------------------------
 2번말 : ----->-----------------------------------
...
10번말 : ------------>----------------------------
*/
public class ThreadTest12 {

	public static void main(String[] args) {

		List<Horse> horselist = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			horselist.add(new Horse(i + "번말"));
		}

		for (Horse h : horselist) {
			h.start();
		}

		for (Horse h : horselist) {
			try {
				h.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		Collections.sort(horselist);
		System.out.println("경기 종료! 등수 순으로 출력:");
		System.out.println(horselist.toString());
	}
}

class Horse extends Thread implements Comparable<Horse> {

	public static int nextRank = 1;

	private String name;
	private int rank;
	private int location;

	// 생성자
	public Horse(String name) {
		this.name = name;
		this.rank = 0;
		this.location = 0;
	}
	
	@Override
	public String toString() {
		return "[ " + name + " : rank => " + rank + " ]" + "\n";
	}

	@Override
	public void run() {
		for (int i = 1; i <= 50; i++) {
			location = i;
			raceStatus();
			try {
				Thread.sleep((long) (Math.random() * 500));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		rank = nextRank++;
	}

	// 경주 진행상황 출력 메소드
	private void raceStatus() {
		StringBuilder progress = new StringBuilder();
		for (int i = 1; i < 50; i++) {
			if (i == location) {
				progress.append(">");
			} else {
				progress.append("-");
			}
		}
		System.out.println(name + " : " + progress);
	}

	// 내부 정렬 기준 (rank 오름차순 정렬 기준)
	@Override
	public int compareTo(Horse o) {
		return Integer.compare(rank, o.rank);
	}
}