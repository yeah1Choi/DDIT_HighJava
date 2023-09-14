package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Arrays;
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

*/
public class ThreadTest12Answer {

	public static void main(String[] args) {
		Horse01[] horses = new Horse01[] { new Horse01("01번말"), new Horse01("02번말"), new Horse01("03번말"),
				new Horse01("04번말"), new Horse01("05번말"), new Horse01("06번말"), new Horse01("07번말"), new Horse01("08번말"),
				new Horse01("09번말"), new Horse01("10번말") };

		GameBoard gb = new GameBoard(horses);

		// 경주시작 => 쓰레드시작
		for (Horse01 h : horses) {
			h.start();
		}

		gb.start(); // 말의 현재 위치 출력 쓰레드 실행

		// 모든 말의 경주가 끝날때까지 기다린다
		for (Horse01 h : horses) {
			try {
				h.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		gb.interrupt();

		try {
			gb.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}

		System.out.println();
		System.out.println("경기 끝");
		System.out.println();

		// 등수의 오름차순으로 정렬
		Arrays.sort(horses);

		for (Horse01 h : horses) {
			System.out.println(h);
		}
	}
}

class Horse01 extends Thread implements Comparable<Horse01> {
	// 등수를 구하기 위한 변수( 각 말들(쓰레드들)이 공통으로 사용됨 )
	public static int currentRank = 0;

	// 멤버변수
	private String horseName; // 말이름
	private int rank; // 등수
	private int location; // 현재위치

	// 생성자
	public Horse01(String horseName) {
		this.horseName = horseName;
	}

	// getter setter
	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "경주마 " + horseName + "은(는) " + rank + "등입니다.";
	}

	// 등수의 오름차순으로 정렬하는 정렬기준구하기
	@Override
	public int compareTo(Horse01 horse) {
		return Integer.compare(this.rank, horse.getRank());
	}

	@Override
	public void run() {
		// 경기구간은 1~50구간으로 되어있다
		for (int i = 1; i <= 50; i++) {
			this.location = i; // 말의 현재위치를 저장
			try {
				Thread.sleep((int) (Math.random() * 500)); // 0~499사이의 난수(낮을수록처리속도빠름)
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		// 현재 말의 경기가 끝 => 등수를 구해서 저장
		currentRank++;
		this.rank = currentRank;
	}
}

//경기 중 말들의 위치를 나타내게 하는 클래스
class GameBoard extends Thread {
	private Horse01[] horses;

	// 생성자
	public GameBoard(Horse01[] horses) {
		this.horses = horses;
	}

	@Override
	public void run() {
		while (true) {
			// 모든 말들의 경기 종료 여부 검사
/*			
 			if (Horse01.currentRank == horses.length) {
				break;
			}
*/
			//interrupt()를 이용한 종료 처리
			if(this.isInterrupted()) {
				break;
			}
			
			// 각 팀 사이의 빈줄 출력
			for (int i = 1; i <= 15; i++) {
				System.out.println();
			}

			for (Horse01 h : horses) {
				System.out.print(h.getHorseName() + " : ");

				for (int i = 1; i <= 50; i++) {
					if (h.getLocation() == i) {// 말의 현재위치 확인
						System.out.print(">");
					} else {
						System.out.print("-");
					}
				}

				System.out.println();// 줄바꿈
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				this.interrupt();
			}
		}
	}
}