package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/*
JDBC 드라이버를 로딩하고 Connection 객체를 생성해서 반환하는 메서드로 구성된 Class

(dbinfo.properties 파일의 내용을 이용하여 설정하기)
=> ResourceBundle 객체 이용하기
*/

public class DBUtil3 {
	
	static final Logger logger = Logger.getLogger(DBUtil3.class);
	
	static ResourceBundle bundle;

	static {
		bundle = ResourceBundle.getBundle("kr.or.ddit.jdbc.config.dbinfo");
		logger.info("ResourceBundle객체 생성 - dbinfo.properties파일 이용");

		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(bundle.getString("driver"));
			logger.debug("DB 드라이버 로딩 성공 ~~");

		} catch (ClassNotFoundException e) {
//			System.out.println("드라이버 로딩 실패 ~~");
			logger.error("드라이버 로딩 실패 ~~",e);
			e.printStackTrace();
		} 
	}

	public static Connection getConnection() {
		Connection conn = null;

		try {
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PC_24_04", "java");
			conn = DriverManager.getConnection(
					bundle.getString("url"), 
					bundle.getString("user"),
					bundle.getString("pass"));
			logger.debug("DB 연결 성공 ~~");
		} catch (SQLException e) {
//			System.out.println("DB 연결 실패");
			logger.error("DB 연결 실패 ~~",e);
			e.printStackTrace();
		}
		return conn;
	}
}
