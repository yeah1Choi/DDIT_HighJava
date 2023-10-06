package kr.or.ddit.json;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.LprodVO;

public class LprodDaoImpl {
	private static LprodDaoImpl dao;
	
	private LprodDaoImpl() {}
	
	public static LprodDaoImpl getInstance() {
		if(dao == null) dao = new LprodDaoImpl();
		return dao;
	}
	
	public List<LprodVO> getAllLprod() {
		SqlSession session = MybatisUtil.getSqlSession();
		List<LprodVO> list = null;
		
		try {
			list = session.selectList("lprod.getAllLprod");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return list;
	}
}