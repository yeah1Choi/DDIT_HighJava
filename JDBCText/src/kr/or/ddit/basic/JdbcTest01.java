package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
JDBC 라이브러리를 이용한 DB자료 처리하기
Java DataBase Connectivity
사용될 라이브러리에 따라 호환되는 jar파일을 가져야한다


	<JDBC를 이용한데이터 베이스 처리 순서>
	
1. 드라이버 로딩 : 라이브러리를 사용할 수 있도록 메모리로 읽어들이는 작업
	Class.forName("oracle.jdbc.driver.OracleDriver"); 을 필수로 해야했다.
	현재 jdbc버전 4.0 이상에서, DriverManager객체의 getConnection()메서드에서 자동으로 로딩해주기 때문에 생략가능하다.
	그렇지만 수업에는 이것을 생략하지 않고 사용할 예정

2. DB시스템에 접속하기 : 접속이 완료되면 Connection객체가 반환된다 (Socket과 비슷한 역할을 하는 Connection객체)
	DriverManager.getConnectioin()메서드를 이용한다

3. 질의 : SQL문장을 DB서버로 보내서 결과를 얻어온다.
	(Statement객체 또는 PreparedStatement객체를 이용해 작업한다)

4. 결과처리 : 질의 결과를 받아서 원하는 작업을 수행한다
	1) SQL문이 select문일 경우, select한 결과가 ResultSet객체에 저장되어 반환된다
	2) SQL문이 select문이 아닌 경우(주로 insert문, update문, delete문), 정수 값이 반환된다.
	 *이 정수값은 보통 실행에 성공한 레코드 수를 의미한다

5. 사용한 자원 반납하기 : 각 객체의 close()메서드 이용
 */
public class JdbcTest01 {

	public static void main(String[] args) {
		// DB 작업에 필요한 객체 변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 1.드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.DB시스템 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PC_24_04", "java");

			// 3.질의
			// 3-1.SQL문작성 : 문자열로 작성
			String sql = "select * from lprod";
			// 3-2.Statement 객체 생성 : 질의를 수행하는 객체 생성 : Connection객체를 이용해 생성
			stmt = conn.createStatement();
			// 3-3.SQL문을 DB서버로 보내서 결과를 얻어온다 : Statement나 PreparedStatement객체를 이용
			// * 지금은 실행할 SQL문이 select문이라서 결과가 ResultSet객체에 저장되어 반환한다
			rs = stmt.executeQuery(sql);

			// 4.결과 처리하기 : 가져온 결과를 화면에 한 레코드씩 출력해보기

			// ResultSet객체에 저장된 데이터를 차례로 꺼내오려면 반복문과 ResultSet객체에 next()메서드를 이용한다
			System.out.println("** 쿼리문 실행 결과 **");
			// ResultSet객체의 next() :
			// ResultSet객체에 저장된 데이터를 가리키는 포인터를 다음번째의 레코드 위치로 이동시키고 그 곳에 데이터가 있으면 참, 없으면 거짓을
			// 반환
			while (rs.next()) {
				// ResultSet객체의 포인터가 가리키는 곳의 데이터를 가져오는 방법
				// 형식 1) ResultSet객체.get자료형이름(컬러명 또는 alias명)
				// 형식 2) ResultSet객체.get자료형이름(컬럼번호) *컬럼번호는 1번부터 시작
				System.out.println("LRPOD_ID : " + rs.getInt("lprod_id"));
				System.out.println("LRPOD_GU : " + rs.getString(2)); // 컬럼번호(순서)
				System.out.println("LRPOD_NM : " + rs.getString("LPROD_NM"));
				System.out.println("---------------------------------------------");
			}
			System.out.println("출력 작업 끝");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 5.사용했던 자원반납 : 객체가 생성된 순서의 역순으로 닫기
			if(rs!=null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(stmt!=null) try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(conn!=null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}

	}
}