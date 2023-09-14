package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
 LPROD 테이블에 새로운 데이터를 추가한다
 조건: LPROD_GU값과 LPROD_NM값은 직접 입력받아 처리하고
 	 LPROD_ID값은 현재의 LPROD_ID값 중에 제일 큰 값보다 1 크게한다
 	  입력받은 LPROD_GU값이 이미 등록되어 있으면 다시 입력받아 처리한다
 */
public class JdbcTest05 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // ResultSet는 데이터가 담긴 그릇

		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PC_24_04", "java");

			conn = DBUtil.getConnection();

			// LPROD_ID 구하기 : 현재의 LPROD_ID값 중에 제일 큰 값보다 1 크게한다
			String sql1 = "SELECT NVL(MAX(LPROD_ID),0) MAXNUM FROM LPROD"; // alias를 따로 부여하지 않으면 'MAX(LPROD_ID)'으로 컬럼명이
																			// 지정됨
			pstmt = conn.prepareStatement(sql1);

			rs = pstmt.executeQuery();

			int maxnum = 1;

			if (rs.next()) { // next() 메서드 : ResultSet 안에 데이터를 가르키는 포인터를 다음순으로 넘어가는 메소드
				// maxnum = rs.getInt(1) + 1; // 컬럼 번호를 이용 (나올 컬럼이 하나밖에 없음)
				maxnum = rs.getInt("MAXNUM"); // alias명을 이용, rs는 ResultSet으로 그릇이기 때문에 데이터를 꺼내 연산해야한다
			}
			maxnum++;

			// LPROD_GU값과 LPROD_NM값은 직접 입력받아 처리
			System.out.println("** LPROD 테이블에 새로운 데이터를 추가 **");

			// 입력받은 LPROD_GU값이 이미 등록되어 있으면 다시 입력받아 처리
			String guInput = ""; // 상품 분류 코드가 저장될 변수 선언
			int count = 0; // 검색한 상품 분류 코드의 개수가 저장될 변수 선언

			String sql2 = "SELECT COUNT(*) CNT FROM LPROD WHERE LPROD_GU = ?";
			pstmt = conn.prepareStatement(sql2);

			do {
				System.out.print("상품 분류 코드(LPROD_GU) 입력 >>");
				guInput = scan.next();

				pstmt.setString(1, guInput);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					count = rs.getInt("CNT");
				}

				if (count > 0) {
					System.out.println("이미 존재하는 상품 분류 코드(LPROD_GU)입니다.");
					System.out.println("다시 입력하세요.");
					System.out.println();
				}

			} while (count > 0);

			System.out.print("상품 분류명(LPROD_NM) 입력 >>");
			String nmInput = scan.next();

			String sql3 = "INSERT INTO LPROD (LPROD_ID, LPROD_GU, LPROD_NM) VALUES (?, ?, ?)";
			pstmt = conn.prepareStatement(sql3);

			pstmt.setInt(1, maxnum);
			pstmt.setString(2, guInput);
			pstmt.setString(3, nmInput);

			int insertCount = pstmt.executeUpdate();

			if (insertCount > 0) {
				System.out.println("데이터가 성공적으로 추가되었습니다.");
			} else {
				System.out.println("데이터 추가를 실패했습니다.");
			}

			String sql4 = "SELECT * FROM LPROD";
			pstmt = conn.prepareStatement(sql4);
			rs = pstmt.executeQuery();

			System.out.println("** 쿼리문 실행 결과 **");
			while (rs.next()) {
				System.out.println("LPROD_ID : " + rs.getInt("LPROD_ID"));
				System.out.println("LPROD_GU : " + rs.getString("LPROD_GU"));
				System.out.println("LPROD_NM : " + rs.getString("LPROD_NM"));
				System.out.println("---------------------------------------------");
			}
			System.out.println("출력 작업 끝");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		/*
		 * catch (ClassNotFoundException e) { e.printStackTrace(); }
		 */

		finally {
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
}