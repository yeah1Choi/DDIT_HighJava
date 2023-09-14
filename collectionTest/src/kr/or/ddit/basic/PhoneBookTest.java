package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/*
문제 ) 
이름, 주소, 전화번호를 멤버로 갖는 Phone 클래스를 만들고,
Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.

( Map의 구조는 key값으로 입력한 '이름'을 사용하고, 
  value값으로는 'Phone클래스의 인터페이스'로 한다.
    변수 선언 예시 : HashMap<String, Phone> 변수명;
 
   이 프로그램에는 다음과 같은 메뉴의 기능을 구현한다. )
	  -------------------
	      다음 메뉴를 선택하세요.
	   1. 전화번호 등록
	   2. 전화번호 수정
	   3. 전화번호 삭제
	   4. 전화번호 검색
	   5. 전화번호 전체 출력
	   0. 프로그램 종료
	  -------------------
	  
  - 검색, 삭제 기능은 '이름'을 입력 받아 처리한다.
  
 **실행예시)
 	  -------------------
	      다음 메뉴를 선택하세요.
	   1. 전화번호 등록
	   2. 전화번호 수정
	   3. 전화번호 삭제
	   4. 전화번호 검색
	   5. 전화번호 전체 출력
	   0. 프로그램 종료
	  -------------------
	      메뉴선택 >> 1
	      
	  *새롭게 등록할 전화번호 정보를 입력하세요*
	    이름 >> 홍길동
	    전화번호 >> 01012345678
	    주소 >> 대전시 중구 오류동
	    
	  '홍길동' 전화번호 등록 완료 !
	  
	  (전화번호 등록 시 이미 등록되면)
	  '홍길동'은 이미 등록된 사람입니다...

	  -------------------
	      메뉴선택 >> 1

	 ---------------------------------------
	   번호    이름         전화번호                주소
	 ---------------------------------------
	  1       홍길동      01012345678      대전 중구 오류동
	 --------------------------------------- 
	  출력 끝

	  -------------------
	      메뉴선택 >> 0
	  
	  프로그램을 종료합니다.....

*/
public class PhoneBookTest {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		Map<String, Phone> PhoneBookMap = new HashMap<>();

		while (true) {

			System.out.println("==========================================");
			System.out.println("        다음 메뉴를 선택하세요.");
			System.out.println("      1. 전화번호 등록");
			System.out.println("      2. 전화번호 수정");
			System.out.println("      3. 전화번호 삭제");
			System.out.println("      4. 전화번호 검색");
			System.out.println("      5. 전화번호 전체 출력");
			System.out.println("      0. 프로그램 종료");
			System.out.println("==========================================");
			System.out.print("  번호 선택 >> ");

			int choice = scan.nextInt();
			scan.nextLine();

			switch (choice) {
			case 1:
				System.out.println("\n *새롭게 등록할 전화번호 정보를 입력하세요*");

				System.out.print("이름 >> ");
				String newName = scan.nextLine();

				if (PhoneBookMap.containsKey(newName)) {
					System.out.println(newName + "은 이미 등록된 사람입니다...");

				} else {

					System.out.print("전화번호 >> ");
					String newTel = scan.nextLine();

					System.out.print("주소 >> ");
					String newAdd = scan.nextLine();

					Phone newPhone = new Phone(newName, newTel, newAdd);
					PhoneBookMap.put("name", newPhone);

					System.out.println();
					System.out.println(newName + "님 전화번호 등록 완료 !");
					System.out.println();
				}
				break;

			case 2:
				System.out.println("\n *수정할 이름을 입력해 정보를 수정합니다*");
				System.out.print(" 입력 >> ");
				String changeName = scan.nextLine();

				System.out.println(changeName + "님의 정보를 수정합니다.");
				System.out.println("-----------------------------------");
				for (Phone phone : PhoneBookMap.values()) {
					if (changeName.equals(phone.getName())) {
						System.out.print("새 전화번호 : ");
						String newTel = scan.nextLine();
						phone.setTel(newTel);

						System.out.print("새 주소 : ");
						String newAdd = scan.nextLine();
						phone.setAdd(newAdd);

						System.out.println();
						System.out.println(changeName + "님 전화번호 수정 완료 !");
						System.out.println();

					} else {
						System.out.println("입력하신 이름은 없는 정보입니다.");
						break;
					}
				}
				break;
			case 3:
				System.out.println("\n *삭제할 이름을 입력해 정보를 삭제합니다*");
				System.out.print(" 입력 >> ");
				String deleteName = scan.nextLine();
				
				if (PhoneBookMap.containsKey(deleteName)) {
					
					PhoneBookMap.remove(deleteName);

					System.out.println();
					System.out.println(deleteName + "님 전화번호 삭제 완료 !");
					System.out.println();
					
				} else {
					System.out.println("입력하신 이름은 없는 정보입니다.");
				}
				break;

			case 4:
				System.out.println("\n *이름을 입력해 검색합니다*");
				System.out.print(" 입력 >> ");
				String searchName = scan.nextLine();

				for (Phone phone : PhoneBookMap.values()) {
					if (searchName.equals(phone.getName())) {
						System.out.println("-----------------------------------");
						System.out.println(" 이름            전화번호          주소");
						System.out.println("-----------------------------------");
						System.out.println(phone.getName() + " " + phone.getTel() + " " + phone.getAdd());

						System.out.println("-----------------------------------");
					}
				}
				break;

			case 5:
				System.out.println("\n *전체 전화번호를 조회합니다*");
				System.out.println("-----------------------------------");
				System.out.println(" 번호     이름     전화번호          주소");
				System.out.println("-----------------------------------");

				int index = 1;

				for (Phone phone : PhoneBookMap.values()) {
					System.out
							.println(" " + index + " " + phone.getName() + " " + phone.getTel() + " " + phone.getAdd());
					index++;
				}
				System.out.println("-----------------------------------");
				System.out.println("출력 끝");
				break;

			case 0:
				System.out.println("\n프로그램을 종료합니다....");
				System.exit(0);
				break;

			default:
				System.out.println("잘못된 메뉴 선택입니다.");
				break;
			}
			System.out.print("** 메뉴로 돌아가기 : Enter **");
			scan.nextLine();
		}

	}

}

class Phone {
	// 변수
	private String name;
	private String add;
	private String tel;

	public Phone(String name, String tel, String add) {
		this.name = name;
		this.tel = tel;
		this.add = add;
	}

	public String getName() {
		return name;
	}

	public String getAdd() {
		return add;
	}

	public String getTel() {
		return tel;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Phone [name=" + name + ", add=" + add + ", tel=" + tel + "]";
	}

}
