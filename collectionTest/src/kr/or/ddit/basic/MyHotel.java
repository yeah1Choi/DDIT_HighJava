package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class MyHotel {

	private HashMap<Integer, Hotel> hotelMap;
	private Scanner scan;

	public MyHotel() {
		hotelMap = new HashMap<Integer, Hotel>();
		scan = new Scanner(System.in);

		// 객실초기화
		for (int i = 2; i <= 4; i++) {
			// 방 종류 구하기
			String roomType = null;
			switch (i) {
			case 2:
				roomType = "싱글룸";
				break;
			case 3:
				roomType = "더블룸";
				break;
			case 4:
				roomType = "스위트룸";
				break;
			}
			// 각 객실(Hotel)을 생성해서 Map에 저장
			for (int j = 1; j < 9; j++) {
				int roomNum = i * 100 + j;
				Hotel hotel = new Hotel(roomNum, roomType);
				hotelMap.put(roomNum, hotel);
			}
		}
	} // 생성자 끝

	public static void main(String[] args) {
		new MyHotel().hotelStart();
	}

	// 시작 메서드
	public void hotelStart() {
		System.out.println();
		System.out.println("*********************************************");
		System.out.println("        <호 텔 영 업 관 리 프 로 그 램>");
		System.out.println("*********************************************");

		while (true) {
			int choice = displayMenu();

			switch (choice) {
			case 1: // 체크인
				checkIn();
				break;
			case 2: // 체크아웃
				checkOut();
				break;
			case 3: // 객실상태조회
				roomState();
				break;
			case 4: // 업무종료
				System.out.println();
				System.out.println("*********************************************");
				System.out.println("   <호 텔 영 업 관 리 프 로 그 램>을 종료합니다...");
				System.out.println("*********************************************");
				return;
			default:
				System.out.println("잘못된 번호 입니다.");
				System.out.println("1에서 4사이의 번호로 다시 입력해주세요.");
			}
		}
	}

	// 체크아웃 처리 메서드
	private void checkOut() {
		System.out.println("----------------------------------------------");
		System.out.println("   체크아웃 작업");
		System.out.println("----------------------------------------------");
		System.out.println("체크아웃 할 방 번호를 입력하세요.");
		System.out.print("방번호 입력 >> ");
		int roomNum = scan.nextInt();

		if (!hotelMap.containsKey(roomNum)) {
			System.out.println(roomNum + "호 객실은 존재하지 않습니다.");
			return;
		}

		if (hotelMap.get(roomNum).getName() == null) {
			System.out.println(roomNum + "호 객실에는 체크인 한 손님이 없습니다.");
			return;
		}

		// 해당 객실에 저장된 투숙객 이름정보 구하기
		String guestName = hotelMap.get(roomNum).getName();

		// 체크아웃 작업은 해당 객실의 투숙객 이름을 null 변경
		hotelMap.get(roomNum).setName(null);

		System.out.println(roomNum + "호 객실의 " + guestName + "님의 체크아웃을 완료");
	}

	// 객실 상태 출력 메서드
	private void roomState() {
		System.out.println("----------------------------------------------");
		System.out.println("		     현재 객실 상태");
		System.out.println("----------------------------------------------");
		System.out.println(" 방 번호	   방 종류	        투숙객 이름");
		System.out.println("----------------------------------------------");

		Set<Integer> roomNumSet = hotelMap.keySet();
		ArrayList<Integer> roomNumList = new ArrayList<Integer>(roomNumSet);
		Collections.sort(roomNumList); // 방번호를 오름차순으로 정렬

		for (int roomNum : roomNumSet) {
			Hotel h = hotelMap.get(roomNum);
			System.out.print(h.getNum() + "\t");
			System.out.print(h.getType() + "\t");
			/* System.out.println(h.getName()); */
			
			String name = h.getName() == null ? "-" : h.getName();
			System.out.println(name);
		}
		System.out.println("----------------------------------------------");
	}

	// 체크인 처리 메서드
	private void checkIn() {
		System.out.println("----------------------------------------------");
		System.out.println("   체크인 작업");
		System.out.println("----------------------------------------------");
		System.out.println(" * 201~209 : 싱글룸");
		System.out.println(" * 301~309 : 더블룸");
		System.out.println(" * 401~409 : 스위트룸");
		System.out.println("----------------------------------------------");

		System.out.print("방 번호 입력 >> ");
		int roomNum = scan.nextInt();

		// 입력한 값이 Map의 key값에 없으면, 존재하지 않는 방번호
		if (!hotelMap.containsKey(roomNum)) {
			System.out.println(roomNum + "호 객실은 존재하지 않습니다.");
			return;
		}

		// 해당 객실에 투숙객이 있는지 검사
		if (hotelMap.get(roomNum).getName() != null) {
			System.out.println(roomNum + "호 객실은 이미 손님이 있습니다.");
			return;
		}

		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 >> ");
		String guestName = scan.next();

		// 입력 받은 투숙객 이름을 해당 객실에 정보 저장
		hotelMap.get(roomNum).setName(guestName);

		System.out.println(guestName + "님의 " + roomNum + "호 객실 체크인이 완료");
	}

	// 메뉴를 출력하고 선택 번호를 입력받아 반환 메서드
	private int displayMenu() {
		System.out.println();
		System.out.println("-----------------------------------------------------------");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료");
		System.out.println("-----------------------------------------------------------");
		System.out.print("메뉴 선택>> ");
		return scan.nextInt();
	}
}

// Hotel클래스는 방번호, 방종류, 투숙객이름 필드로 구성
class Hotel {
	private int num;
	private String type;
	private String name;

	public Hotel(int num, String type) {
		super();
		this.num = num;
		this.type = type;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Hotel [num=" + num + ", type=" + type + ", name=" + name + "]";
	}
}