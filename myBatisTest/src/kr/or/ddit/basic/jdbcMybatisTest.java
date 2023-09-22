package kr.or.ddit.basic;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.LprodVO;

/*
 LPROD 테이블에 새로운 데이터를 추가한다
 조건: LPROD_GU값과 LPROD_NM값은 직접 입력받아 처리하고
 	 LPROD_ID값은 현재의 LPROD_ID값 중에 제일 큰 값보다 1 크게한다
 	  입력받은 LPROD_GU값이 이미 등록되어 있으면 다시 입력받아 처리한다
 */
public class jdbcMybatisTest {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		/*
		 * InputStream in = null; SqlSessionFactory sqlSessionFactory = null;
		 * 
		 * try { in =
		 * Resources.getResourceAsStream("kr/or/ddit/mybatis/config/mybatis-config.xml")
		 * ; sqlSessionFactory = new SqlSessionFactoryBuilder().build(in); } catch
		 * (Exception e) { System.out.println("MyBatis 초기화 실패"); e.printStackTrace(); }
		 * finally { if (in != null) try { in.close(); } catch (IOException e) { } }
		 */
		
			SqlSession session = null;
			
		try {
			// LPROD_GU값과 LPROD_NM값은 직접 입력
			//session = sqlSessionFactory.openSession();
			session = MybatisUtil.getSqlSession();
			
			int maxnum = session.selectOne("jdbc.getMaxId");
			maxnum++; // 제일 큰 값 증가
			
			///////////////////////////////////////////////

			// LPROD_GU값과 LPROD_NM값은 직접 입력받아 처리
			
			String guInput = ""; // 상품 분류 코드가 저장될 변수 선언
			int count = 0; // 검색한 상품 분류 코드의 개수가 저장될 변수 선언

			do {
				System.out.print("상품 분류 코드(LPROD_GU) 입력 >>");
				guInput = scan.next();
				
				count = session.selectOne("jdbc.getLprodGuCnt", guInput);

				if (count > 0) {
					System.out.println("이미 존재하는 상품 분류 코드(LPROD_GU)입니다.");
					System.out.println("다시 입력하세요.");
					System.out.println();
				}

			} while (count > 0);

			//////////////////////////////////////////////////////////////////
			
			System.out.print("상품 분류명(LPROD_NM) 입력 >>");
			String nmInput = scan.next();

			///////////////////////////////////////////////////////////////////
			
			LprodVO lvo = new LprodVO();
			
			lvo.setLprod_id(maxnum);
			lvo.setLprod_gu(guInput);
			lvo.setLprod_nm(nmInput);

			int insertCount = session.insert("jdbc.insertLprod",lvo);
			

			if (insertCount > 0) {
				System.out.println("데이터가 성공적으로 추가되었습니다.");
				session.commit();
			} else {
				System.out.println("데이터 추가를 실패했습니다.");
			}
			
			//////////////////////////////////////////////////////////////////
/*
			List<LprodVO> lprodList = session.selectList("jdbc.getAllLprod");
			
			System.out.println("** 쿼리문 실행 결과 **");
			for (LprodVO lvo1 : lprodList) {
				System.out.println("LPROD_ID : " + lvo1.getLprod_id());
				System.out.println("LPROD_GU : " + lvo1.getLprod_gu());
				System.out.println("LPROD_NM : " + lvo1.getLprod_nm());
				System.out.println("---------------------------------------------");
			}
			System.out.println("출력 작업 끝");
*/
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}
}