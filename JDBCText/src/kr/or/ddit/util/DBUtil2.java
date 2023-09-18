package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
JDBC 드라이버를 로딩하고 Connection 객체를 생성해서 반환하는 메서드로 구성된 Class

(dbinfo.properties 파일의 내용을 이용하여 설정하기)
*/

public class DBUtil2 {
	static Properties prop; // Properties 변수 선언

	static {
		prop = new Properties(); // Properties 객체 생성
		File f = new File("res/kr/or/ddit/jdbc/config/dbinfo.properties");
		FileInputStream fin = null;

		try {
			fin = new FileInputStream(f);
			prop.load(fin);

//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(prop.getProperty("driver"));

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fin != null)
				try {
					fin.close();
				} catch (IOException e) {
				}
		}
	}

	public static Connection getConnection() {
		Connection conn = null;

		try {
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PC_24_04", "java");
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"),
					prop.getProperty("pass"));
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		return conn;
	}
}
