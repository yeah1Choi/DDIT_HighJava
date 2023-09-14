package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 문제 : 사용자로부터 LPROD_ID값을 입력받아 입력값보다 큰 자료들을 출력하시오
  + Scanner 클래스로 입력 객체를 받아오고
  + sql 쿼리문을 조건에 맞게 적어준다
 */
public class JdbcTest02 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);

		System.out.print("LPROD_ID값을 입력 >>");
		int input = scan.nextInt();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PC_24_04", "java");

			String sql = "select * from lprod where lprod_id >" + input;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			System.out.println("** 쿼리문 실행 결과 **");
			while (rs.next()) {
				System.out.println("LRPOD_ID : " + rs.getInt("lprod_id"));
				System.out.println("LRPOD_GU : " + rs.getString("LPROD_GU"));
				System.out.println("LRPOD_NM : " + rs.getString("LPROD_NM"));
				System.out.println("---------------------------------------------");
			}
			System.out.println("출력 작업 끝");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			if (stmt != null)
				try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			if (conn != null) 
				try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}
}