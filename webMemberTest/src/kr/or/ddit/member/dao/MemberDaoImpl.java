package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {
	
	// 1번
	private static MemberDaoImpl dao;
	
	// 2번 - 생성자
	private MemberDaoImpl() {	}
	
	// 3번
	public static MemberDaoImpl getInstance() {
		if(dao==null) dao = new MemberDaoImpl();
		return dao;
	}
	

	@Override
	public int insertMember(MemberVO memVo) {
		SqlSession session = MybatisUtil.getSqlSession();  // MyBatis 처리용 객체 생성
		int cnt = 0;  // 반환값이 저장될 변수 선언
		
		try {
			cnt = session.insert("member.insertMember", memVo);
			if(cnt > 0) session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		SqlSession session = MybatisUtil.getSqlSession();  
		int cnt = 0;		// 반환값이 저장될 변수
		
		try {
			cnt = session.delete("member.deleteMember", memId);
			if(cnt > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}	
		
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		SqlSession session = MybatisUtil.getSqlSession();  
		int cnt = 0;		// 반환값이 저장될 변수
		
		try {
			cnt = session.update("member.updateMember", memVo);
			if(cnt > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}	
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		SqlSession session = MybatisUtil.getSqlSession(); 
		List<MemberVO> memList = null;  // 반환값이 저장될 변수
		
		try {
			memList = session.selectList("member.getAllMember");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		SqlSession session = MybatisUtil.getSqlSession(); 
		int count = 0;	// 반환값이 저장될 변수
		
		try {
			count = session.selectOne("member.getMemberCount", memId);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		} 
		
		return count;
	}

	
	@Override
	public MemberVO getMember(String memId) {
		SqlSession session = MybatisUtil.getSqlSession();  
		MemberVO loginMemberVo = null;	// 반환값이 저장될 변수
		
		try {
			loginMemberVo = session.selectOne("member.getMember", memId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		} 
		return loginMemberVo;
	}

}
