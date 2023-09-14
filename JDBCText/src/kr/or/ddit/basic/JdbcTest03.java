package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 lprod_id 값을 2개 입력받아 두 값 중 작은 값부터 큰 값 사이의 자료들을 출력하시오 
 (앞 뒤의 값을 포함된다)
 */
public class JdbcTest03 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("lprod_id 값을 2개 입력받아 두 값 중 작은 값부터 큰 값 사이의 자료들을 출력합니다");

		System.out.print("첫번째 LPROD_ID값을 입력 >>");
		int firInput = scan.nextInt();

		System.out.print("두번째 LPROD_ID값을 입력 >>");
		int scdInput = scan.nextInt();

		// 큰 값 찾기
		// 1
//		if(firInput > scdInput) {
//			int temp = firInput;
//			firInput = scdInput;
//			scdInput = temp; 	}
		// 2
//		int min = firInput > scdInput ? scdInput : firInput;
//		int max = firInput > scdInput ? firInput : scdInput;
		// 3
		int min = Math.min(firInput, scdInput);
		int max = Math.max(firInput, scdInput);

		Connection con = null;
		Statement stm = null;
		ResultSet rss = null;
		PreparedStatement pstmt = null;

		try {
			// 드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// DB연결
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PC_24_04", "java");

			// createStatement 객체
			/*
			 * // SQL문 // String sql = "SELECT * FROM LPROD WHERE LPROD_ID >=" + min +
			 * "AND LPROD_ID <=" + max; String sql =
			 * "SELECT * FROM LPROD WHERE LPROD_ID BETWEEN " + min + " AND " + max;
			 * 
			 * // Statement stm = con.createStatement();
			 * 
			 * // sql문을 서버에 보내기 rss = stm.executeQuery(sql);
			 */
			// PreparedStatement 객체
			String sql = "SELECT * FROM LPROD WHERE LPROD_ID >= ? AND LPROD_ID <= ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, min);
			pstmt.setInt(2, max);

			rss = pstmt.executeQuery();

			// 결과처리
			System.out.println("** 쿼리문 실행 결과 **");
			while (rss.next()) {
				System.out.println("LPROD_ID : " + rss.getInt("LPROD_ID"));
				System.out.println("LPROD_GU : " + rss.getString("LPROD_GU"));
				System.out.println("LPROD_NM : " + rss.getString("LPROD_NM"));
				System.out.println("---------------------------------------------");
			}
			System.out.println("출력 작업 끝");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 사용했던 자원반납
			if (rss != null)
				try {
					rss.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (stm != null)
				try {
					stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}