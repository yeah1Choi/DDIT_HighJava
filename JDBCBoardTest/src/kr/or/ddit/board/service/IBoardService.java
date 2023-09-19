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
	 * @param title : 검색어
	 * @return : MemberVO 객체가 저장된 List
	 */
	public List<BoardVO> searchBoard(String title);
	
	/**
	 * DB에 저장된 특정 회원 정보를 가져와서 List에 담아서 반환하는 메서드
	 * @param board_no
	 * @return : MemberVO 객체가 저장된 List
	 */
	public BoardVO getABoard(int board_no);
	
	/**
	 * 게시글의 번호를 매개로 해당 게시글의 조회수 증가하는 메소드
	 * @param borVO : 조회수 데이터
	 * @return : 작업 성공시 1, 실패시 0
	 */
	public int increseCnt(int board_no);
	
	/**
	 * 게시글 번호를 매개변수로 받아 해당 게시글 삭제하는 메소드
	 * @param board_no 삭제할 게시글 번호
	 * @return : 작업 성공시 1, 실패시 0
	 */
	public int deleteBoard(int board_no);

	/**
	 * BoardVO에 저장된 자료를 이용한 업데이트하는 메소드
	 * @param borVO 수정할 자료가 저장된 VO 객체
	 * @return : 작업 성공시 1, 실패시 0
	 */
	public int updateBoard(BoardVO borVO);
}
