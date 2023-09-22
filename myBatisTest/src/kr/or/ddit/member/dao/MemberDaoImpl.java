package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {

	// 싱글톤 1)
	private static MemberDaoImpl dao;
	
	// 싱글톤 2)
	private MemberDaoImpl() {}
	
	// 싱글톤 3)
	public static MemberDaoImpl getInstance() {
		if(dao == null) dao = new MemberDaoImpl();
		return dao;
	}
	
	@Override
	public int insertMember(MemberVO memVo) {
		
		SqlSession session = null;
		
		int cnt = 0; // 반환값이 저장될 변수

		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.insert("member.insertMember", memVo);
		
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

			cnt = session.insert("member.deleteMemId", memId);
			
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
	public int updateMember(MemberVO memVo) {
		SqlSession session = null;

		int cnt = 0;

		try {
			session = MybatisUtil.getSqlSession();

			cnt = session.insert("member.updateMember", memVo);
			
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
	public List<MemberVO> getAllMember() {
		
		SqlSession session = null;
		
		List<MemberVO> memList = null; // 반환값이 저장될 변수

		try {
			session = MybatisUtil.getSqlSession();

			memList = session.selectList("member.getAllMember");

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

			cnt = session.insert("member.getMemIdCnt", memId);
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
			cnt = session.update("member.updateMember2",paramMap);
			
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
