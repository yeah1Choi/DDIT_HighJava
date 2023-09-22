package kr.or.ddit.answer.service;

import java.util.List;

import kr.or.ddit.answer.dao.IJDBCBoardDao;
import kr.or.ddit.answer.dao.JDBCBoardDaoImp;
import kr.or.ddit.answer.vo.JDBCBoardVO;

public class JDBCBoardServiceImpl implements IJDBCBoardService {

	private IJDBCBoardDao dao;

	private static JDBCBoardServiceImpl service;

	private JDBCBoardServiceImpl() {
		dao = JDBCBoardDaoImp.getInstance();
	}

	public static JDBCBoardServiceImpl getInstance() {
		if (service == null)
			service = new JDBCBoardServiceImpl();
		return service;
	}

	@Override
	public int insertBoard(JDBCBoardVO boardVo) {
		return dao.insertBoard(boardVo);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return dao.deleteBoard(boardNo);
	}

	@Override
	public int updateBoard(JDBCBoardVO boardVo) {
		return dao.updateBoard(boardVo);
	}

	@Override
	public List<JDBCBoardVO> getAllBoard() {
		return dao.getAllBoard();
	}

	@Override
	public JDBCBoardVO getBoard(int boardNo) {
		int cnt = dao.setCountIncrement(boardNo);
		if (cnt == 0)
			return null;
		return dao.getBoard(boardNo);
	}

	@Override
	public List<JDBCBoardVO> getSearchBoard(String title) {
		return dao.getSearchBoard(title);
	}

	@Override
	public int setCountIncrement(int boardNo) {
		return dao.setCountIncrement(boardNo);
	}

}
