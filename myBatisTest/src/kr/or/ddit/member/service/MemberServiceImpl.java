package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {

	private IMemberDao dao; // DAO객체 변수 선언

	private static MemberServiceImpl service; // 싱글톤 1)

	// 싱글톤 2) 생성자
	// public MemberServiceImpl() {
	private MemberServiceImpl() {
		// dao = new MemberDaoImpl();
		dao = MemberDaoImpl.getInstance();
	}

	// 3)
	public static MemberServiceImpl getInstance() {
		if (service == null)
			service = new MemberServiceImpl();
		return service;
	}

	@Override
	public int insertMember(MemberVO memVo) {
		return dao.insertMember(memVo);
	}

	@Override
	public int deleteMember(String memId) {
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memVo) {
		return dao.updateMember(memVo);
	}

	@Override
	public List<MemberVO> getAllMember() {
		return dao.getAllMember();
	}

	@Override
	public int getMemIdCount(String memId) {
		return dao.getMemIdCount(memId);
	}

	@Override
	public int selectUpdate(Map<String, String> paramMap) {
		return dao.selectUpdate(paramMap);
	}

}
