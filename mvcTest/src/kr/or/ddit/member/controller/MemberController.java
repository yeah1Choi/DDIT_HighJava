package kr.or.ddit.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberController {

	private IMemberService service; // Service객체 변수 선언
	private Scanner scan = new Scanner(System.in);

	// 생성자
	public MemberController() {
		//service = new MemberServiceImpl();
		service = MemberServiceImpl.getInstance();
	}

	public static void main(String[] args) {
		new MemberController().startProgram();

	}

	// 전체출력 메서드
	private void allPrint() {
		List<MemberVO> memList = service.getAllMember();

		System.out.println("==========================================");
		System.out.println(" 아이디\t 비밀번호\t 이름\t  전화번호\t 주소");
		System.out.println("==========================================");

		if (memList == null || memList.size() == 0) {
			System.out.println("등록된 회원 데이터가 없습니다");
		} else {
			for (MemberVO memVo : memList) {
				System.out.println(memVo.getMem_id() + "\t" + memVo.getMem_pass() + "\t" + memVo.getMem_name() + "\t"
						+ memVo.mem_tel + "\t" + memVo.getMem_addr());
				System.out.println("------------------------------------------");
			}

		}
		System.out.println("출력 작업 끝");
	}

	// 부분 수정 메서드
	private void selectUpdate() {
		System.out.println("*데이터 선택 수정 메뉴입니다*");

		String idInput = "";
		System.out.print("수정할 회원ID 입력>> ");
		idInput = scan.next();

		if (service.getMemIdCount(idInput) == 0) {
			System.out.println("존재하지 않는 회원ID(MEM_ID)입니다.");
			System.out.println("데이터 삭제를 실패했습니다");
			return;
		}

		int num;
		String updateField = null;
		String updateTitle = null;

		do {
			System.out.println();
			System.out.println("수정할 항목을 선택하세요");
			System.out.println("1.비밀번호   2.회원이름   3.전화번호   4.회원주소");
			System.out.println("---------------------------------------");
			System.out.print("수정할 항목 선택 >>");
			num = scan.nextInt();

			switch (num) {
			case 1:
				updateField = "MEM_PASS";
				updateTitle = "비밀번호";
				break;
			case 2:
				updateField = "MEM_NAME";
				updateTitle = "회원이름";
				break;
			case 3:
				updateField = "MEM_TEL";
				updateTitle = "전화번호";
				break;
			case 4:
				updateField = "MEM_ADDR";
				updateTitle = "회원주소";
				break;
			default:
				System.out.println("수정할 항목을 잘못 선택했습니다");
				System.out.println("다시 선택하세요");
			}
		} while (num <= 1 || num >= 4);

		scan.nextLine();
		System.out.println();
		System.out.print("새로운 " + updateTitle + " : ");
		String updateData = scan.nextLine();

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("memid", idInput);
		paramMap.put("field", updateField);
		paramMap.put("data", updateData);

		int cnt = service.selectUpdate(paramMap);

		if (cnt > 0) {
			System.out.println("데이터가 성공적으로 수정되었습니다.");
		} else {
			System.out.println("데이터 수정을 실패했습니다.");
		}
	}

	// 전체 수정 메서드
	private void update() {
		System.out.println("*데이터 수정 메뉴입니다*");

		String idInput = "";
		System.out.print("수정할 회원ID 입력>> ");
		idInput = scan.next();

		if (service.getMemIdCount(idInput) == 0) {
			System.out.println("존재하지 않는 회원ID(MEM_ID)입니다.");
			System.out.println("데이터 삭제를 실패했습니다");
			return;
		}

		System.out.print("새로운 회원 비밀번호(MEM_PASS) 입력 >>");
		String pwInput = scan.next();

		System.out.print("새로운 회원 이름(MEM_NAME) 입력 >>");
		String nmInput = scan.next();

		System.out.print("새로운 회원 전화번호(MEM_TEL) 입력 >>");
		String telInput = scan.next();

		scan.nextLine();
		System.out.print("새로운 회원 주소(MEM_ADDR) 입력 >>");
		String addrInput = scan.nextLine();

		MemberVO memVO = new MemberVO();
		memVO.setMem_id(idInput);
		memVO.setMem_pass(pwInput);
		memVO.setMem_name(nmInput);
		memVO.setMem_tel(telInput);
		memVO.setMem_addr(addrInput);

		int cnt = service.insertMember(memVO);

		if (cnt > 0) {
			System.out.println("데이터가 성공적으로 수정되었습니다.");
		} else {
			System.out.println("데이터 수정을 실패했습니다.");
		}
	}

	// 삭제메서드
	private void delete() {
		System.out.println("*데이터 삭제 메뉴입니다*");

		System.out.print("삭제 회원ID 입력>> ");
		String idInput = scan.next();

		if (service.getMemIdCount(idInput) == 0) {
			System.out.println("존재하지 않는 회원ID(MEM_ID)입니다.");
			System.out.println("데이터 삭제를 실패했습니다");
			return;
		}

		int cnt = service.deleteMember(idInput);

		if (cnt > 0) {
			System.out.println("데이터가 성공적으로 추가되었습니다.");
		} else {
			System.out.println("데이터 추가를 실패했습니다.");
		}

	}

	// 추가메서드
	private void insert() {
		System.out.println("*테이블에 새롭게 등록할 정보를 입력하세요*");

		System.out.print("회원ID(MEM_ID) 입력>>");
		String idInput = scan.next();

		if (service.getMemIdCount(idInput) > 0) {
			System.out.println("이미 존재하는 회원ID(MEM_ID)입니다.");
			System.out.println("다시 입력하세요.");
			return;
		}

		System.out.print("회원 비밀번호(MEM_PASS) 입력 >>");
		String pwInput = scan.next();

		System.out.print("회원 이름(MEM_NAME) 입력 >>");
		String nmInput = scan.next();

		System.out.print("회원 전화번호(MEM_TEL) 입력 >>");
		String telInput = scan.next();

		scan.nextLine();
		System.out.print("회원 주소(MEM_ADDR) 입력 >>");
		String addrInput = scan.nextLine();

		// 입력 받은 데이터들을 VO객체에 저장한다
		MemberVO memVO = new MemberVO();
		memVO.setMem_id(idInput);
		memVO.setMem_pass(pwInput);
		memVO.setMem_name(nmInput);
		memVO.setMem_tel(telInput);
		memVO.setMem_addr(addrInput);

		int cnt = service.insertMember(memVO);

		if (cnt > 0) {
			System.out.println("데이터가 성공적으로 추가되었습니다.");
		} else {
			System.out.println("데이터 추가를 실패했습니다.");
		}
	}

	// 프로그램 시작하는 메서드
	public void startProgram() {
		System.out.println("*************************");
		System.out.println("  회 원 관 리 프 로 그 램 ");
		System.out.println("*************************");
		while (true) {
			int choice = displayMenu();

			switch (choice) {
			case 1: // 자료추가
				insert();
				break;
			case 2: // 삭제
				delete();
				break;
			case 3: // 전체 수정
				update();
				break;
			case 4: // 선택 수정
				selectUpdate();
				break;
			case 5: // 전체 자료 출력
				allPrint();
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

	// 메뉴 메소드 : 메뉴 출력하고 입력받은 선택번호를 반환하는 메서드
	private int displayMenu() {
		System.out.println("==========================================");
		System.out.println("        작업을 선택하세요.");
		System.out.println("      1. 자료 추가");
		System.out.println("      2. 자료 삭제");
		System.out.println("      3. 전체 자료 수정");
		System.out.println("      4. 자료 선택 수정");
		System.out.println("      5. 전체 자료 출력");
		System.out.println("      0. 프로그램 종료");
		System.out.println("==========================================");
		System.out.print("  메뉴 선택 >> ");
		return scan.nextInt();
	}
}
