package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	
	private IBoardDao dao;
	
	public BoardServiceImpl() {
		dao = new BoardDaoImpl();
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
	public List<BoardVO> searchBoard(String keyword) {
		return dao.searchBoard(keyword);
	}
}
