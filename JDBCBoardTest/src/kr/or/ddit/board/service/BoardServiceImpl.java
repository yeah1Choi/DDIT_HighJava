package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	
	private IBoardDao dao;
	
	private static BoardServiceImpl service;
	
	public BoardServiceImpl() {
		dao = BoardDaoImpl.getInstance();
	}
	
	public static BoardServiceImpl getInstance() {
		if(service == null) service = new BoardServiceImpl();
		return service;
	}

	@Override
	public int createBoard(BoardVO borVO) {
		return dao.createBoard(borVO);
	}

	@Override
	public List<BoardVO> getAllBoard() {
		return dao.getAllBoard();
	}

	@Override
	public List<BoardVO> searchBoard(String title) {
		return dao.searchBoard(title);
	}

	@Override
	public BoardVO getABoard(int board_no) {
		int cnt = dao.increseCnt(board_no);
		if(cnt == 0) return null;
	
		return dao.getABoard(board_no);
	}

	@Override
	public int increseCnt(int board_no) {
		return dao.increseCnt(board_no);
	}

	@Override
	public int deleteBoard(int board_no) {
		return dao.deleteBoard(board_no);
	}

	@Override
	public int updateBoard(BoardVO borVO) {
		return dao.updateBoard(borVO);
	}
}
