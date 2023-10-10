package kr.or.ddit.mymem.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.MymemberVO;

public class MymemberDaoImpl implements IMymemberDao {

	// 싱글톤 1)
	private static MymemberDaoImpl dao;
	
	// 싱글톤 2)
	private MymemberDaoImpl() {}
	
	// 싱글톤 3)
	public static MymemberDaoImpl getInstance() {
		if(dao == null) dao = new MymemberDaoImpl();
		return dao;
	}
	
	@Override
	public int insertMember(MymemberVO memVo) {
		
		SqlSession session = null;
		
		int cnt = 0; // 반환값이 저장될 변수

		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.insert("mymember.insertMember", memVo);
		
			if (cnt > 0) {
				System.out.println("데이터가 성공적으로 추가되었습니다.");
				// insert, update, delete 작업이 성공하며 commit() 처리
				session.commit();
			} else {
				System.out.println("데이터 추가를 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

		return cnt;
	}

	@Override
	public int deleteMember(String memId) {

		SqlSession session = null;

		int cnt = 0;

		try {
			session = MybatisUtil.getSqlSession();

			cnt = session.insert("mymember.deleteMemId", memId);
			
			if (cnt > 0) {
				System.out.println("데이터가 성공적으로 추가되었습니다.");
				// insert, update, delete 작업이 성공하며 commit() 처리
				session.commit();
			} else {
				System.out.println("데이터 추가를 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

		return cnt;
	}

	@Override
	public int updateMember(MymemberVO memVo) {
		SqlSession session = null;

		int cnt = 0;

		try {
			session = MybatisUtil.getSqlSession();

			cnt = session.insert("mymember.updateMember", memVo);
			
			if (cnt > 0) {
				System.out.println("데이터가 성공적으로 추가되었습니다.");
				// insert, update, delete 작업이 성공하며 commit() 처리
				session.commit();
			} else {
				System.out.println("데이터 추가를 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

		return cnt;
	}

	@Override
	public List<MymemberVO> getAllMember() {
		
		SqlSession session = null;
		
		List<MymemberVO> memList = null; // 반환값이 저장될 변수

		try {
			session = MybatisUtil.getSqlSession();

			memList = session.selectList("mymember.getAllMember");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

		return memList;
	}

	@Override
	public int getMemIdCount(String memId) {
		SqlSession session = null;
		
		int cnt = 0;

		try {
			session = MybatisUtil.getSqlSession();

			cnt = session.insert("mymember.getMemIdCnt", memId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

		return cnt;
	}

	@Override
	public int selectUpdate(Map<String, String> paramMap) {
		
		SqlSession session = null;
		int cnt = 0;

		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.update("mymember.updateMember2",paramMap);
			
			if(cnt > 0) session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

		return cnt;
	}
}
