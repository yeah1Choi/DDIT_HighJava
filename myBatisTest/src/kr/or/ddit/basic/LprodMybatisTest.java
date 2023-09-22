package kr.or.ddit.basic;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.vo.LprodVO;

/*
	MyBatis를 이용하여 DB자료를 처리하는 순서 및 방법
*/
public class LprodMybatisTest {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// 1) MyBatis의 환경 설정 파일(mybatis-config.xml)을 읽어와서 처리하여
		// SplSessionFactory 객체 생성한다

		InputStream in = null;
		SqlSessionFactory sqlSessionFactory = null;
		try {
			// 1-1) 환경 설정 파일을 읽어올 스트림 객체 생성
			in = Resources.getResourceAsStream("kr/or/ddit/mybatis/config/mybatis-config.xml");
			// 1-2) 환경 설정 파일을 읽어와 환경 설정을 완성한 후 SqlSessionFactory객체를 생성한다.
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
		} catch (Exception e) {
			System.out.println("MyBatis 초기화 실패");
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
				}
		}

		/////////////////////////////////////////////////////////////////////////////

		// 2) Mapper에 등록된 SQL문들 중에서 실행할 SQL문을 호출해서 원하는 작업을 수행한다.
		/*
		 * // 2-1) insert문 연습 System.out.println("insert 작업 시작");
		 * 
		 * System.out.print("Lprod_id 입력 >> "); int lprodId = scan.nextInt();
		 * 
		 * System.out.print("Lprod_gu 입력 >> "); String lprodGu = scan.next();
		 * 
		 * System.out.print("Lprod_nm 입력 >> "); String lprodNm = scan.next();
		 * 
		 * // 1) 저장할 데이터들을 VO객체에 저장한다 LprodVO lprodVo = new LprodVO();
		 * lprodVo.setLprod_id(lprodId); lprodVo.setLprod_gu(lprodGu);
		 * lprodVo.setLprod_nm(lprodNm);
		 * 
		 * SqlSession session = null;
		 * 
		 * try { // 2) SqlSessionFactory 객체의 openSession() 메서드를 이용하여 // SQL문을 호출해서 실행할 수
		 * 있는 SqlSession 객체를 생성 // 형식) SqlSessionFactory 객체.openSession(논리값) // * '논리값'이
		 * 참이면 AutoCommit이 활성화된 상태이고, 거짓이거나 생략되면 AutoCommit이 비활성화된 상태 session =
		 * sqlSessionFactory.openSession();
		 * 
		 * // 3) SqlSession 객체를 이용하여 처리할 SQL문을 호출하여 실행한다 // 형식)
		 * SqlSession객체.insert("namespace속성값.실행할태그의id속성값", 파라미터클래스) // 반환값 : 작업에 성공한 에코드
		 * 수 int insertCnt = session.insert("lprod.insertLprod", lprodVo);
		 * 
		 * if(insertCnt > 0) { // 4) SqlSession객체를 생성할 때 Autocommit이 비활성화된 상태에는 //
		 * commit을 직접 실행해야 한다. session.commit(); System.out.println("insert 작업 성공"); }
		 * else { System.out.println("insert 작업 실패"); } } catch (Exception e) {
		 * e.printStackTrace(); } finally { // 5) 작업이 끝나면 SqlSession객체를 닫아준다
		 * if(session!=null) session.close(); } }
		 */

		// 2-2) update 연습
		/*
		 * System.out.println("update 연습 시작");
		 * 
		 * System.out.print("수정할 Lprod_gu 입력 >> "); String lprodGu2 = scan.next();
		 * 
		 * System.out.print("새로운 Lprod_id 입력 >> "); int newLprodId = scan.nextInt();
		 * 
		 * System.out.print("새로운 Lprod_nm 입력 >> "); String newLprodNm = scan.next();
		 * 
		 * // 수정할 데이터를 VO객체에 저장한다 LprodVO lprodVo2 = new LprodVO();
		 * lprodVo2.setLprod_gu(lprodGu2); lprodVo2.setLprod_id(newLprodId);
		 * lprodVo2.setLprod_nm(newLprodNm);
		 * 
		 * SqlSession session = null; try { // sqlSession 객체 생성 session =
		 * sqlSessionFactory.openSession();
		 * 
		 * // SQL문 호출해서 실행하기 // 형식)
		 * SqlSession객체.update("namespace속성값.실행할태그의id속성값",파라미터클래스) // 반환값 : 실행에 성공한 레코드
		 * 수 int updateCnt = session.update("lprod.updateLprod",lprodVo2);
		 * 
		 * if(updateCnt > 0) { session.commit(); System.out.println("update 작업 성공"); }
		 * else { System.out.println("update 작업 실패"); } } catch (Exception e) {
		 * e.printStackTrace(); } finally { if(session!=null) session.close(); }
		 */

		// 2-3) delete 연습
		/*
		 * System.out.println("delete 연습 시작");
		 * 
		 * System.out.print("삭제할 lprod_gu 입력 >> "); String lprodGu3 = scan.next();
		 * 
		 * SqlSession session = null;
		 * 
		 * try { // sqlSession 객체 생성 session = sqlSessionFactory.openSession();
		 * 
		 * // SQL문을 호출하여 실행 // 형식)
		 * SqlSession객체.delete("namespace속성값.실행할태그의id속성값",파라미터클래스) // 반환값 : 실행에 성공한 레코드
		 * 수 int deleteCnt = session.delete("lprod.deleteLprod", lprodGu3);
		 * 
		 * if (deleteCnt > 0) { session.commit(); System.out.println("delete 작업 성공"); }
		 * else { System.out.println("delete 작업 실패"); } } catch (Exception e) {
		 * e.printStackTrace(); } finally { if (session != null) session.close(); }
		 */

		// 2-4) select 연습
		// 1) select한 결과가 여러개의 레코드일 경우
		/*
		 * System.out.println("select 작업 시작 -- 결과가 여러개의 레코드일 경우");
		 * 
		 * SqlSession session = null;
		 * 
		 * try { // SqlSession 객체 생성 session = sqlSessionFactory.openSession();
		 * 
		 * // SQL문을 호출하여 실행 // 실행할 select문의 검색 결과가 여러개일 경우에는 selectList()메서드를 사용하는데 // 이
		 * 메서드는 검색된 레코드 각각을 VO객체에 저장한 후 이 VO객체들을 List에 추가해주는 // 작업을 자동으로 수행한다
		 * 
		 * // 형식) SqlSession객체.selectList("namespace속성값.실행할태그의id속성값",파라미터클래스) // 반환값)
		 * 검색된 데이터들이 저장된 List객체 List<LprodVO> lprodList =
		 * session.selectList("lprod.getAllLprod");
		 * 
		 * System.out.println("== 검색 결과 출력 =="); for (LprodVO lvo : lprodList) {
		 * System.out.println("Lprod_Id : " + lvo.getLprod_id());
		 * System.out.println("Lprod_gu : " + lvo.getLprod_gu());
		 * System.out.println("Lprod_nm : " + lvo.getLprod_nm());
		 * System.out.println("---------------------------------"); }
		 * System.out.println("출력 끝"); } catch (Exception e) { e.printStackTrace(); }
		 * finally { if (session != null) session.close(); }
		 */

		// 2) select한 결과가 한 개의 레코드일 경우
		System.out.println("select 작업 시작 - 결과 한 개의 경우");

		System.out.print("검색할 Lprod_gu 입력 >> ");
		String lpordGu4 = scan.next();

		SqlSession session = null;

		try {
			// SqlSession 객체 생성
			session = sqlSessionFactory.openSession();

			// SQL문을 호출해서 실행
			// select문한 검색 결과가 한 개의 레코드인 경우 'selectOne()' 메서드 사용
			// 'selectOne()' 메서드는 검색 결과가 없으면 null값을 반환
			// 형식) SqlSession객체.selectOne("namespace속성값.실행할태그의id속성값",파라미터클래스)

			LprodVO lprodVo3 = session.selectOne("lprod.getLprod", lpordGu4);

			if (lprodVo3 == null) {
				System.out.println("검색된 결과가 하나도 없습니다");
				return;
			}
			System.out.println();
			System.out.println("	== 검색 결과 출력 ==");
			System.out.println("Lprod_Id : " + lprodVo3.getLprod_id());
			System.out.println("Lprod_gu : " + lprodVo3.getLprod_gu());
			System.out.println("Lprod_nm : " + lprodVo3.getLprod_nm());
			System.out.println("---------------------------------");
			System.out.println("출력 끝");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}

}