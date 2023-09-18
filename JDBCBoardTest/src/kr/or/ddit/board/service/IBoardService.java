package kr.or.ddit.board.service;

import java.util.List;
/**
 * 
 * @author PC-24
 */
import kr.or.ddit.vo.BoardVO;

public interface IBoardService {
	/**
	 * BoardVO에 담긴 자료를 DB에 create하는 메서드
	 * @param borVO : DB에 create할 자료가 저장될 BoardVO객체
	 * @return : 작업 성공시 1, 실패시 0
	 */
	public int createBoard(BoardVO borVO);
	
	/**
	 * DB에 저장된 전체 회원 정보를 가져와서 List에 담아서 반환하는 메서드
	 * @return : MemberVO 객체가 저장된 List
	 */
	public List<BoardVO> getAllBoard();
	
	/**
	 * 검색어를 통해 동일한 단어를 가진 데이터를 가져온다
	 * @param keyword : 검색어
	 * @return : MemberVO 객체가 저장된 List
	 */
	public List<BoardVO> searchBoard(String keyword);
	
	/**
	 * DB에 저장된 특정 회원 정보를 가져와서 List에 담아서 반환하는 메서드
	 * @param board_no
	 * @return : MemberVO 객체가 저장된 List
	 */
	public List<BoardVO> getABoard(int board_no);

	/**
	 * 조회 시 하나 더한 값으로 업데이트하는 메소드
	 * @param board_cnt : 조회수 데이터
	 * @return : 작업 성공시 1, 실패시 0
	 */
	public int updateCnt(BoardVO borVO);
}
