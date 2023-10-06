package kr.or.ddit.login.service;

import kr.or.ddit.login.dao.IMemberDao;
import kr.or.ddit.login.dao.MemberDaoImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	private IMemberDao dao;
	
	private static MemberServiceImpl service;
	
	private MemberServiceImpl() {
		dao = MemberDaoImpl.getInstance();
	}
	
	public static MemberServiceImpl getInstance() {
		if(service == null) service = new MemberServiceImpl();
		return service;
	}
	
	@Override
	public MemberVO getLoginMember(MemberVO memVo) {
		return dao.getLoginMember(memVo);
	}

}
