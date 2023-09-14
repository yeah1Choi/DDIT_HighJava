package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;

/*
문제) 호텔 객실을 관리하는 프로그램을 작성하시오.

조건1)  호텔 객식을 나타내는 Room클래스는 방번호(int), 방종류, 투숙객이름 필드로 구성되어 있고 방번호와 방종류는 다음과 같다.
        - 201~209 : 싱글룸
        - 301~309 : 더블룸
        - 401~409 : 스위트룸

조건2) 전체 객실 관리는 Map을 이용한다.(Map의 key값은 방번호로 하고 value값은 Room의 인스턴스로 한다.) 
생성자에서는 방번호와 방종류를 초기화한다.
*/

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class HotelCheckSystem {

	private HashMap<Integer, Room> RoomMap = new HashMap<>();
	private Scanner scan = new Scanner(System.in);

	// 생성자 : 객실 초기화 작업
	public HotelCheckSystem() {
		for (int i = 201; i <= 209; i++) {
			RoomMap.put(i, new Room(i, "싱글룸", "-"));
		}
		for (int i = 301; i <= 309; i++) {
			RoomMap.put(i, new Room(i, "더블룸", "-"));
		}
		for (int i = 401; i <= 409; i++) {
			RoomMap.put(i, new Room(i, "스위트룸", "-"));
		}

	}

	public static void main(String[] args) {

		new HotelCheckSystem().systemStart();
	}

	// 시작 메소드
	public void systemStart() {

		System.out.println("*********************************************");
		System.out.println("       호텔문을 열었습니다. 어서오십시요.");
		System.out.println("*********************************************");
		System.out.println();

		while (true) {
			int choice = displayMenu();

			switch (choice) {
			case 1: // 체크인
				checkIn();
				break;
			case 2: // 체크아웃
				checkOut();
				break;
			case 3: // 객실조회
				statement();
				break;
			case 4: // 업무종료
				System.out.println();
				System.out.println("*********************************************");
				System.out.println("       호텔문을 닫았습니다.");
				System.out.println("*********************************************");
				return;
			default:
				System.out.println("잘못된 번호 입니다.");
				System.out.println("1에서 4사이의 번호로 입력해주세요.");
			}
		}
	}

	// 체크아웃 메소드
	private void checkOut() {

		System.out.println("----------------------------------------------");
		System.out.println("   체크아웃 작업");
		System.out.println("----------------------------------------------");
		System.out.println("체크아웃 할 방 번호를 입력하세요.");
		System.out.print("방번호 입력 >> ");
		int roomNo = scan.nextInt();

		if (RoomMap.containsKey(roomNo)) {
			Room r = RoomMap.get(roomNo);

			if (r.getGuest().equals("-")) {
				System.out.println(roomNo + "호 객실에는 체크인 한 사람이 없습니다.");
				return;
			} else {
				RoomMap.put(roomNo, new Room(roomNo, r.getRoomType(), "-"));
				System.out.println(roomNo + "호 객실의 체크아웃을 완료하였습니다.");
			}
		} else {
			System.out.println(roomNo + "호 객실은 존재하지 않습니다.");
			return;
		}
	}

	// 객실상태 조회 메소드
	private void statement() {
		System.out.println("----------------------------------------------");
		System.out.println("		현재 객실 상태");
		System.out.println("----------------------------------------------");
		System.out.println("방 번호	 방 종류	 투숙객 이름");
		System.out.println("----------------------------------------------");

		Set<Integer> roomSet = RoomMap.keySet();

		ArrayList<Integer> roomNumList = new ArrayList<Integer>(roomSet);
		Collections.sort(roomNumList);

		for (int roomNo : roomSet) {
			Room r = RoomMap.get(roomNo);
			System.out.print(r.getRoomNo() + "\t");
			System.out.print(r.getRoomType() + "\t");
			System.out.println(r.getGuest());
		}
		System.out.println("----------------------------------------------");
	}

	// 체크인메소드
	private void checkIn() {

		System.out.println("----------------------------------------------");
		System.out.println("   체크인 작업");
		System.out.println("----------------------------------------------");
		System.out.println(" * 201~209 : 싱글룸");
		System.out.println(" * 301~309 : 더블룸");
		System.out.println(" * 401~409 : 스위트룸");
		System.out.println("----------------------------------------------");

		System.out.print("방 번호 입력 >> ");
		int roomNo = scan.nextInt();

		if (RoomMap.containsKey(roomNo)) {

			System.out.println("누구를 체크인 하시겠습니까?");
			System.out.print("이름 입력 >> ");
			String guest = scan.next();

			Room r = RoomMap.get(roomNo);

			if (r.getGuest().equals("-")) {
				RoomMap.put(roomNo, new Room(roomNo, r.getRoomType(), guest));
				System.out.println("체크인이 완료되었습니다.");
			} else {
				System.out.println(roomNo + "호 객실은 이미 손님이 있습니다.");
				return;
			}
		} else {
			System.out.println(roomNo + "호 객실은 존재하지 않습니다.");
			return;
		}
	}

	// 메뉴 메소드
	private int displayMenu() {

		System.out.println("-----------------------------------------------------------");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료");
		System.out.println("-----------------------------------------------------------");
		System.out.print("메뉴 선택>> ");
		return scan.nextInt();
	}
}

class Room {
	private int roomNo;
	private String roomType;
	private String guest;

	public Room(int roomNo, String roomType, String guest) {
		super();
		this.roomNo = roomNo;
		this.roomType = roomType;
		this.guest = guest;
	}

	@Override
	public String toString() {
		return "Room [roomNo=" + roomNo + ", roomType=" + roomType + ", guest=" + guest + "]";
	}

	// getter & setter
	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getGuest() {
		return guest;
	}

	public void setGuest(String guest) {
		this.guest = guest;
	}

}