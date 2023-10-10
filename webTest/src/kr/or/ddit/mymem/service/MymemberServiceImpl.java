package kr.or.ddit.mymem.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.mymem.dao.IMymemberDao;
import kr.or.ddit.mymem.dao.MymemberDaoImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MymemberVO;

public class MymemberServiceImpl implements IMymemberService {

	private IMymemberDao dao; // DAO객체 변수 선언

	private static MymemberServiceImpl service; // 싱글톤 1)

	// 싱글톤 2) 생성자
	// public MemberServiceImpl() {
	private MymemberServiceImpl() {
		// dao = new MemberDaoImpl();
		dao = MymemberDaoImpl.getInstance();
	}

	// 3)
	public static MymemberServiceImpl getInstance() {
		if (service == null)
			service = new MymemberServiceImpl();
		return service;
	}

	@Override
	public int insertMember(MymemberVO memVo) {
		return dao.insertMember(memVo);
	}

	@Override
	public int deleteMember(String memId) {
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MymemberVO memVo) {
		return dao.updateMember(memVo);
	}

	@Override
	public List<MymemberVO> getAllMember() {
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
