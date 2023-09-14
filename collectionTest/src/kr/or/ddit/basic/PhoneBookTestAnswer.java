package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class PhoneBookTestAnswer {

	private HashMap<String, PhoneAS> phoneBookMap;
	private Scanner scan;

	public void PhoneBookTest() {
		phoneBookMap = new HashMap<>();
		scan = new Scanner(System.in);
	}

	public static void main(String[] args) {
		new PhoneBookTestAnswer().startPhoneBook();
	}

	// 프로그램을 시작하는 메서드
	public void startPhoneBook() {
		System.out.println("*************************");
		System.out.println(" 전 화 번 호 관 리 프 로 그 램 ");
		System.out.println("*************************");
		while (true) {
			int choice = displayMenu();

			switch (choice) {
			case 1: // 전화번호 등록
				insert();
				break;
			case 2: // 수정
				update();
				break;
			case 3: // 삭제
				delete();
				break;
			case 4: // 검색
				search();
				break;
			case 5: // 전체 자료 출력
				displayAll();
				break;
			case 0: // 프로그램 종료
				System.out.println("프로그램을 종료합니다...");
				return;
			default:
				System.out.println("작업 번호를 잘못 입력하였습니다");
				System.out.println("다시 입력하세요");
			}
		}
	}

	// 전화번호 정보를 검색하는 메소드
	private void search() {
		System.out.println();
		System.out.println("검색할 전화번호 정보를 입력하세요");
		System.out.println("  이름 >> ");
		String name = scan.next();

		// 입력한 이름을 key값으로 PhoneAS 객체 가져오기
		PhoneAS p = phoneBookMap.get(name);

		if (!(p==null)) {// 해당 key값의 데이터가 없으면
			System.out.println(name + "씨의 전화번호 정보가 없습니다");
		} else {
			System.out.println();
			System.out.println(name + "씨의 전화본호 정보");
			System.out.println("-------------------------------");
			System.out.println("이름 : " + p.getName());
			System.out.println("전화번호 : " + p.getTel());
			System.out.println("주소 : " + p.getAddr());
			System.out.println("-------------------------------");
		}
	}

	// 전화번호 정보 삭제 메소드
	private void delete() {
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요");
		System.out.println("  이름 >> ");
		String name = scan.next();

		// 해당 이름이 없으면 삭제작업을 중단한다
		if (!phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨의 전화보호는 등록되지 않았습니다");
			System.out.println();
		}

		phoneBookMap.remove(name);
		System.out.println();
		System.out.println(name + "님 전화번호 삭제 완료 !");
		System.out.println();
	}

	// 전체 전화번호 정보를 출력하는 메소드
	private void displayAll() {
		System.out.println();
		System.out.println("-----------------------------------");
		System.out.println(" 번호     이름     전화번호          주소");
		System.out.println("-----------------------------------");

		Set<String> phoneBookSet = phoneBookMap.keySet();
		
		if (phoneBookSet.size() == 0) {
			System.out.println("등록된 전화번호 정보가 아직 없습니다");
		} else {
			int cnt = 0; // 번호 출력용 변수

			Iterator<String> it = phoneBookSet.iterator();
			while (it.hasNext()) {
				cnt++;
				String key = it.next(); // 키값(등록된 사람 이름)
				PhoneAS p = phoneBookMap.get(key); // Value값(PhoneAS객체)

				System.out.println(" " + cnt + "\t" + p.getName() + "\t" + p.getTel() + "\t" + p.getAddr());
			}
		}
		System.out.println("-----------------------------------");
		System.out.println("출력 끝...");
	}

	// 전화번호 정보를 수정하는 메서드
	private void update() {
		System.out.println();
		System.out.println("*수정할 이름을 입력해 정보를 수정합니다*");
		System.out.print(" 입력 >> ");
		String name = scan.next();

		// 해당 이름이 없으면 수정 작업을 중단한다
		if (!phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨의 전화번호 정보는 등록되지 않았습니다");
			System.out.println("수정 작업을 마칩니다");
			return;
		}
		
		System.out.print("새로운 전화번호 >> ");
		String newTel = scan.next();
		
		System.out.print("새로운 주소 >> ");
		String newAddr = scan.nextLine();
		
		// 같은 key값에새로운 데이터를셋팅한 phoneAS 객체를 저장하면 된다
		phoneBookMap.put(name, new PhoneAS(name, newTel, newAddr));

		System.out.println();
	}

	// 새로운 번호 정보를 등록하는 메소드 (이미 등록된 사람은 등록되지 않는다)
	private void insert() {
		System.out.println();
		System.out.println("*새롭게 등록할 전화번호 정보를 입력하세요*");
		System.out.print("이름 >> ");
		String name = scan.next();

		// 입력받은 이름이 이미 등록된 이름인지 검사
		if (phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨는 이미 등록되어있습니다");
			System.out.println("등록작업을 마칩니다");
			return;
		}
		System.out.print("전화번호 >> ");
		String tel = scan.next();
		System.out.print("주소 >> ");
		String addr = scan.next();

		// 입력받은 정보 이용해 PhoneAS 객체 생성
//		PhoneAS p = new PhoneAS(name, tel, addr);
//		phoneBookMap.put(name,p);
		phoneBookMap.put(name, new PhoneAS(name, tel, addr));

		System.out.println(name + "씨의 정보가 성공적으로 등록되었습니다");
	}

	// 메뉴를 출력하고 작업 번호를 입력받아 반환하는 메서드
	private int displayMenu() {
		System.out.println("==========================================");
		System.out.println("        다음 메뉴를 선택하세요.");
		System.out.println("      1. 전화번호 등록");
		System.out.println("      2. 전화번호 수정");
		System.out.println("      3. 전화번호 삭제");
		System.out.println("      4. 전화번호 검색");
		System.out.println("      5. 전화번호 전체 출력");
		System.out.println("      0. 프로그램 종료");
		System.out.println("==========================================");
		System.out.print("  메뉴 선택 >> ");
		return scan.nextInt();
	}
}

// 이름, 주소, 번호를 멤버로 갖는 Phone 클래스
class PhoneAS {
	private String name;
	private String tel;
	private String addr;

	// 생성자
	public PhoneAS(String name, String tel, String addr) {
		super();
		this.name = name;
		this.tel = tel;
		this.addr = addr;
	}

	// getter & setter
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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
}