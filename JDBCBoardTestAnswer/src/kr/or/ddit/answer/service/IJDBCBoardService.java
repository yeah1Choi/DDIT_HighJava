package kr.or.ddit.answer.service;

import java.util.List;

import kr.or.ddit.answer.vo.JDBCBoardVO;

public interface IJDBCBoardService {
	/**
	 * JdbcBoardVO에 담겨진 자료를 DB에 insert하는 메서드
	 * 
	 * @param boardVo DB에 insert할 자료가 저장된 JdbcBoardVO객체
	 * @return 작업성공:1, 작업실패:0
	 */
	public int insertBoard(JDBCBoardVO boardVo);

	/**
	 * 게시글 번호를 매개변수로 받아서 해당 게시글 정보를 삭제하는 메서드
	 * 
	 * @param boardNo 삭제할 게시글 번호
	 * @return 작업성공:1, 작업실패:0
	 */
	public int deleteBoard(int boardNo);

	/**
	 * JdbcBoardVO에 저장된 자료를 이용하여 update작업을 수행하는 메서드
	 * 
	 * @param boardVo 수정할 자료가 저장된 JdbcBoardVO객체
	 * @return 작업성공:1, 작업실패:0
	 */
	public int updateBoard(JDBCBoardVO boardVo);

	/**
	 * DB의 jdbc_board 테이블의 전체 데이터를 가져와 List에 담아 반환 메서드
	 * 
	 * @return JDBCBoardVO 객체를 저장하고 있는 List 객체
	 */
	public List<JDBCBoardVO> getAllBoard();

	/**
	 * 게시글 번호를 매개변수로 받아서 해당 게시글 정보를 가져와 반환 메소드
	 * 
	 * @param boardNo
	 * @return
	 */
	public JDBCBoardVO getBoard(int boardNo);

	/**
	 * 게시글의 제목을 이용하여 게시글을 검색하는 메서드
	 * 
	 * @param title 검색할 게시글의 제목
	 * @return 검색된 결과를 담은 List객체
	 */
	public List<JDBCBoardVO> getSearchBoard(String title);

	/**
	 * 게시글의 번호를 매개변수로 받아서 해당 게시글의 조회수를 증가시키는 메서드
	 * 
	 * @param boardNo
	 * @return작업성공:1, 작업실패:0
	 */
	public int setCountIncrement(int boardNo);
}
