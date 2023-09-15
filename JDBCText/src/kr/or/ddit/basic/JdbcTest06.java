package kr.or.ddit.basic;
/*
< 회원 관리 프로그램 > 
(DB의 MYMEMBER 테이블 이용)

아래의 메뉴를 구성하고 각 메뉴의 기능을 구현하시오 (CRUD 기능구현) =>
 == 작업 선택 ==
 1. 자료 추가                 // C (INSERT문)
 2. 자료 삭제                 // D (DELETE문)
 3. 자료 수정                 // U (UPDATE문)
 4. 전체 자료 출력          // R (SELECT문)
 0. 프로그램 종료
 
 ** 조건
 1) 자료 추가에서 '회원ID'는 중복되지 않는다. 중복 시 다시 입력받는다.
 2) 자료 삭제는  '회원ID'를 입력받아서 처리한다.
 3) 자료 수정에서 '회원ID'는 변경되지 않는다.
 
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

// import kr.or.ddit.util.DBUtil;
//import kr.or.ddit.util.DBUtil2;
import kr.or.ddit.util.DBUtil3;

public class JdbcTest06 {

	Scanner scan = new Scanner(System.in);

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public static void main(String[] args) {
		new JdbcTest06().startProgram();
	}

	// 전체 자료 출력 메서드
	public void allPrint() {

		conn = DBUtil3.getConnection();

		try {
			String sql = "SELECT * FROM MYMEMBER";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			int cnt = 0; // 출력한 데이터 개수가 저장될 변수

			System.out.println("** MYMEMBER 테이블 전체 데이터 출력 **");

			/*
			 * while (rs.next()) { System.out.println("MEM_ID : " + rs.getString("MEM_ID"));
			 * System.out.println("MEM_PASS : " + rs.getString("MEM_PASS"));
			 * System.out.println("MEM_NAME : " + rs.getString("MEM_NAME"));
			 * System.out.println("MEM_TEL : " + rs.getString("MEM_TEL"));
			 * System.out.println("MEM_ADDR : " + rs.getString("MEM_ADDR"));
			 * System.out.println("------------------------------------------"); }
			 */

			System.out.println("==========================================");
			System.out.println("NO.\t 아이디\t 비밀번호\t 이름\t  전화번호\t 주소");
			System.out.println("==========================================");
			while (rs.next()) {
				cnt++;
				System.out.println(cnt + "\t" + rs.getString("MEM_ID") + "\t" + rs.getString("MEM_PASS") + "\t"
						+ rs.getString("MEM_NAME") + "\t" + rs.getString("MEM_TEL") + "\t" + rs.getString("MEM_ADDR"));
				System.out.println("------------------------------------------");
			}
			if (cnt == 0) {
				System.out.println("등록된 회원 데이터가 없습니다");
			}
			System.out.println("출력 작업 끝");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 선택 자료 수정 메서드
	public void selectUpdate() {
		System.out.println("*데이터 선택 수정 메뉴입니다*");

		String idInput = "";

		do {
			System.out.print("수정할 회원ID 입력>> ");
			idInput = scan.next();

			if (getMemIDConut(idInput) == 0) {
				System.out.println("존재하지 않는 회원ID(MEM_ID)입니다.");
				System.out.println("데이터 수정를 실패했습니다");
				System.out.println();
			}

		} while (getMemIDConut(idInput) == 0);
		
		int num; // 수정 항목 선택 번호 저장 변수
		String updateField = null;// 수정할 컬럼명이 저장될 변수
		String updateTitle = null;// 수정할 데이터를 입력 받을 때 출력 메시지
		
		do {
			System.out.println();
			System.out.println("수정할 항목을 선택하세요");
			System.out.println("1.비밀번호   2.회원이름   3.전화번호   4.회원주소");
			System.out.println("---------------------------------------");
			System.out.print("수정할 항목 선택 >>");
			num = scan.nextInt();
			
			switch(num) {
			case 1 : 
				updateField = "MEM_PASS"; 
				updateTitle="비밀번호";
				break;
			case 2 :
				updateField = "MEM_NAME"; 
				updateTitle="회원이름";
				break;
			case 3 :
				updateField = "MEM_TEL"; 
				updateTitle="전화번호";
				break;
			case 4 :
				updateField = "MEM_ADDR"; 
				updateTitle="회원주소";
				break;
			default :
				System.out.println("수정할 항목을 잘못 선택했습니다");
				System.out.println("다시 선택하세요");
			}
		} while(num <= 1 || num >= 4);
		
		scan.nextLine();
		System.out.println();
		System.out.print("새로운 "+updateTitle+" : ");
		String updateData = scan.nextLine();
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "UPDATE MYMEMBER SET " + updateField
			+ " = ? WHERE MEM_ID = ?";
			// set메소드로 집어넣으면 문자열로 인식에 따옴표 안에 넣어준다 그래서 쿼리에 직접 쓰기

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, updateData);
			pstmt.setString(2, idInput);

			int insertCount = pstmt.executeUpdate();

			if (insertCount > 0) {
				System.out.println("데이터가 성공적으로 수정되었습니다.");
			} else {
				System.out.println("데이터 수정을 실패했습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 전체 자료 수정 메서드
	public void update() {
		System.out.println("*데이터 수정 메뉴입니다*");

		String idInput = "";
		int count = 0;

		do {
			System.out.print("수정할 회원ID 입력>> ");
			idInput = scan.next();

			count = getMemIDConut(idInput);

			if (count == 0) {
				System.out.println("존재하지 않는 회원ID(MEM_ID)입니다.");
				System.out.println("데이터 수정를 실패했습니다");
				System.out.println("다시 입력하세요.");
				System.out.println();
			}

		} while (count == 0);

		System.out.print("새로운 회원 비밀번호(MEM_PASS) 입력 >>");
		String pwInput = scan.next();

		System.out.print("새로운 회원 이름(MEM_NAME) 입력 >>");
		String nmInput = scan.next();

		System.out.print("새로운 회원 전화번호(MEM_TEL) 입력 >>");
		String telInput = scan.next();

		scan.nextLine();
		System.out.print("새로운 회원 주소(MEM_ADDR) 입력 >>");
		String addrInput = scan.nextLine();

		try {
			conn = DBUtil3.getConnection();
			String sql = "UPDATE MYMEMBER SET " + "MEM_PASS = ?, MEM_NAME = ?, MEM_TEL = ?, MEM_ADDR = ? "
					+ "WHERE MEM_ID = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, pwInput);
			pstmt.setString(2, nmInput);
			pstmt.setString(3, telInput);
			pstmt.setString(4, addrInput);
			pstmt.setString(5, idInput);

			int insertCount = pstmt.executeUpdate();

			if (insertCount > 0) {
				System.out.println("데이터가 성공적으로 수정되었습니다.");
			} else {
				System.out.println("데이터 수정을 실패했습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 자료 삭제 메서드
	public void delete() {

		System.out.println("*데이터 삭제 메뉴입니다*");

		String idInput = "";
		int count = 0;

		do {
			System.out.print("삭제 회원ID 입력>> ");
			idInput = scan.next();

			count = getMemIDConut(idInput);

			if (count == 0) {
				System.out.println("존재하지 않는 회원ID(MEM_ID)입니다.");
				System.out.println("데이터 삭제를 실패했습니다");
				System.out.println("다시 입력하세요.");
				System.out.println();
			}

		} while (count == 0);

		try {
			conn = DBUtil3.getConnection();

			String sql = "DELETE FROM MYMEMBER WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, idInput);

			int insertCount = pstmt.executeUpdate();

			if (insertCount > 0) {
				System.out.println("데이터가 성공적으로 삭제되었습니다.");
			} else {
				System.out.println("데이터  삭제를 실패했습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 자료 추가 메서드
	public void insert() {

		System.out.println("*테이블에 새롭게 등록할 정보를 입력하세요*");

		String idInput = ""; // 입력한 회원ID가 저장될 변수
		int count = 0;

		do {
			System.out.print("회원ID(MEM_ID) 입력>>");
			idInput = scan.next();

			count = getMemIDConut(idInput);

			if (count > 0) {
				System.out.println("이미 존재하는 회원ID(MEM_ID)입니다.");
				System.out.println("다시 입력하세요.");
				System.out.println();
			}

		} while (count > 0);

		System.out.print("회원 비밀번호(MEM_PASS) 입력 >>");
		String pwInput = scan.next();

		System.out.print("회원 이름(MEM_NAME) 입력 >>");
		String nmInput = scan.next();

		System.out.print("회원 전화번호(MEM_TEL) 입력 >>");
		String telInput = scan.next();

		scan.nextLine(); // 버퍼 비우기
		System.out.print("회원 주소(MEM_ADDR) 입력 >>");
		String addrInput = scan.nextLine();
		// 띄어쓰기가 들어가는 주소 입력시 nextLine을 사용하는데 그럴 땐 앞서 입력들에게 scan.nextLine();를 덧붙여야한다 

		try {
			conn = DBUtil3.getConnection();

			String sql = "INSERT INTO MYMEMBER (MEM_ID, MEM_PASS, MEM_NAME, MEM_TEL, MEM_ADDR) VALUES (?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, idInput);
			pstmt.setString(2, pwInput);
			pstmt.setString(3, nmInput);
			pstmt.setString(4, telInput);
			pstmt.setString(5, addrInput);

			int insertCount = pstmt.executeUpdate();

			if (insertCount > 0) {
				System.out.println("데이터가 성공적으로 추가되었습니다.");
			} else {
				System.out.println("데이터 추가를 실패했습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	// 회원 ID 중복 여부 검사 메소드 : ID를 매개변수로 해당 ID갯수를 반환
	private int getMemIDConut(String memId) {

		int cnt = 0; // 검색 회원ID의 갯수가 저장될 변수

		try {
			conn = DBUtil3.getConnection();
			String sql = "SELECT COUNT(*) CNT FROM MYMEMBER WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				cnt = rs.getInt("CNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
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
