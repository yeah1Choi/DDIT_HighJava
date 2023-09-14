package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class jDBCtEST04 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PC_24_04", "java");

			System.out.println("** 계좌 정보 추가하기 **");
			System.out.print("계좌번호 >>");
			String bankNo = scan.next();
			System.out.print("은행명 >>");
			String bankName = scan.next();
			System.out.print("예금주명 >>");
			String userName = scan.next();

			// Statement객체 이용해서 추가하기
			/*
			 * String sql =
			 * "INSERT INTO BANKINFO (BANK_NO, BANK_NAME, BANK_USER_NAME, BANK_DATE) " +
			 * "VALUES ('" + bankNo + "','" + bankName + "','" + userName + "',SYSDATE);";
			 * 
			 * System.out.println("sql문 확인용 : " + sql);
			 * 
			 * stmt = conn.createStatement();
			 * 
			 * // select문 실행 때, executeQuery()메서드 사용하고, // select문 아닐 때(insert,update,delete
			 * 등), executeUpdate()메서드 사용 // executeUpdate() 메서드 반환값 : 정수값 (작업에 성공한 레코드 수)
			 * 
			 * int cnt = stmt.executeUpdate(sql);
			 */

			// PreparedStatement 객체를 이용해서 추가
			// SQL문을 작성할 때 SQL문에 데이터가 들어갈 자리는 물음표로 표시해서 작성한다, 따옴표없이!(문자열로 인식)
			String sql = "INSERT INTO BANKINFO (BANK_NO, BANK_NAME, BANK_USER_NAME, BANK_DATE) "
					+ "VALUES (?,?,?,SYSDATE);";

			// PreparedStatement 객체 생성 : 객체 생성시 SQL문을 인수값으로 넣어줌
			pstmt = conn.prepareStatement(sql);

			// SQL문의 물음표에 들어갈 데이터 셋팅
			// 형식 : PreparedStatement객체.set자료형이름(물음표번호,셋팅할 데이터)
			// * 물음표번호는 1번부터 시작
			pstmt.setString(1, bankNo);
			pstmt.setString(2, bankName);
			pstmt.setString(3, userName);
			
			// 데이터 셋팅이 완료되면 SQL문을 실행
			int cnt = pstmt.executeUpdate();

			System.out.println("반환값 : " + cnt);

		}catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			if (stmt != null)
				try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			if (conn != null) 
				try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}
}