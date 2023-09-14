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

import kr.or.ddit.util.DBUtil;

public class JdbcTest06 {

	Scanner scan = new Scanner(System.in);

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public static void main(String[] args) {
		new JdbcTest06().startProgram();
	}

	// 전체 자료 출력 메서드
	public void allSelect() {

		conn = DBUtil.getConnection();

		try {
			String sql9 = "SELECT * FROM MYMEMBER";
			pstmt = conn.prepareStatement(sql9);
			rs = pstmt.executeQuery();

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
			System.out.println("  아이디     비밀번호        이름       전화번호           주소");
			System.out.println("==========================================");
			while (rs.next()) {
				System.out.println(rs.getString("MEM_ID") + "\t" + rs.getString("MEM_PASS") + "\t"
						+ rs.getString("MEM_NAME") + "\t" + rs.getString("MEM_TEL") + "\t" + rs.getString("MEM_ADDR"));
				System.out.println("------------------------------------------");
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

	// 자료 수정 메서드
	public void update() {

		conn = DBUtil.getConnection();

		try {
			System.out.println("*데이터 수정 메뉴입니다*");
			System.out.println("수정할 회원ID를 입력해주세요");

			String uidInput = "";
			int count = 0;

			String sql1 = "SELECT COUNT(*) CNT FROM MYMEMBER WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(sql1);

			do {
				System.out.print("수정할 회원ID 입력>> ");
				uidInput = scan.next();

				pstmt.setString(1, uidInput);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					count = rs.getInt("CNT");
				}

				if (count == 0) {
					System.out.println("존재하지 않는 회원ID(MEM_ID)입니다.");
					System.out.println("데이터 수정를 실패했습니다");
					System.out.println("다시 입력하세요.");
					System.out.println();
				}

			} while (count == 0);

			System.out.print("회원 비밀번호(MEM_PASS) 입력 >>");
			String pwInput = scan.next();
			scan.nextLine();

			System.out.print("회원 이름(MEM_NAME) 입력 >>");
			String nmInput = scan.next();
			scan.nextLine();

			System.out.print("회원 전화번호(MEM_TEL) 입력 >>");
			String telInput = scan.next();
			scan.nextLine();

			System.out.print("회원 주소(MEM_ADDR) 입력 >>");
			String addrInput = scan.nextLine();

			String sql2 = "UPDATE MYMEMBER SET " + "MEM_PASS = ?, MEM_NAME = ?, MEM_TEL = ?, MEM_ADDR = ? "
					+ "WHERE MEM_ID = ?";

			pstmt = conn.prepareStatement(sql2);

			pstmt.setString(1, pwInput);
			pstmt.setString(2, nmInput);
			pstmt.setString(3, telInput);
			pstmt.setString(4, addrInput);
			pstmt.setString(5, uidInput);

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

		conn = DBUtil.getConnection();

		try {
			System.out.println("*데이터 삭제 메뉴입니다*");
			System.out.println("삭제할 회원ID를 입력해주세요");

			String didInput = "";
			int count = 0;

			String sql1 = "SELECT COUNT(*) CNT FROM MYMEMBER WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(sql1);

			do {
				System.out.print("삭제 회원ID 입력>> ");
				didInput = scan.next();

				pstmt.setString(1, didInput);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					count = rs.getInt("CNT");
				}

				if (count == 0) {
					System.out.println("존재하지 않는 회원ID(MEM_ID)입니다.");
					System.out.println("데이터 삭제를 실패했습니다");
					System.out.println("다시 입력하세요.");
					System.out.println();
				}

			} while (count == 0);

			String sql2 = "DELETE FROM MYMEMBER WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(sql2);

			pstmt.setString(1, didInput);

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

		conn = DBUtil.getConnection();

		try {
			System.out.println("*테이블에 새롭게 등록할 정보를 입력하세요*");

			String idInput = "";
			int count = 0;

			String sql1 = "SELECT COUNT(*) CNT FROM MYMEMBER WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(sql1);

			do {
				System.out.print("회원ID(MEM_ID) 입력>>");
				idInput = scan.next();

				pstmt.setString(1, idInput);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					count = rs.getInt("CNT");
				}

				if (count > 0) {
					System.out.println("이미 존재하는 회원ID(MEM_ID)입니다.");
					System.out.println("다시 입력하세요.");
					System.out.println();
				}

			} while (count > 0);

			System.out.print("회원 비밀번호(MEM_PASS) 입력 >>");
			String pwInput = scan.next();
			scan.nextLine();

			System.out.print("회원 이름(MEM_NAME) 입력 >>");
			String nmInput = scan.next();
			scan.nextLine();

			System.out.print("회원 전화번호(MEM_TEL) 입력 >>");
			String telInput = scan.next();
			scan.nextLine();

			System.out.print("회원 주소(MEM_ADDR) 입력 >>");
			String addrInput = scan.nextLine();

			String sql2 = "INSERT INTO MYMEMBER (MEM_ID, MEM_PASS, MEM_NAME, MEM_TEL, MEM_ADDR) VALUES (?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql2);

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
			case 3: // 수정
				update();
				break;
			case 4: // 전체 자료 출력
				allSelect();
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

	// 메뉴 메소드
	private int displayMenu() {
		System.out.println("==========================================");
		System.out.println("        다음 메뉴를 선택하세요.");
		System.out.println("      1. 자료 추가");
		System.out.println("      2. 자료 삭제");
		System.out.println("      3. 자료 수정");
		System.out.println("      4. 전체 자료 출력");
		System.out.println("      0. 프로그램 종료");
		System.out.println("==========================================");
		System.out.print("  메뉴 선택 >> ");
		return scan.nextInt();
	}
}
