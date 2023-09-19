package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.util.DBUtil3;
import kr.or.ddit.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao {
	
	private static BoardDaoImpl dao;
	
	private BoardDaoImpl() {}
	
	public static BoardDaoImpl getInstance() {
		if(dao == null) dao = new BoardDaoImpl();
		return dao;
	}

	@Override
	public int createBoard(BoardVO borVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();

			String sql = "INSERT INTO jdbc_board (board_no, board_title, board_writer, board_date, board_cnt, board_content) "
					+ "VALUES (board_seq.nextVal, ?, ?, SYSDATE, 0, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, borVO.getBoard_title());
			pstmt.setString(2, borVO.getBoard_writer());
			pstmt.setString(3, borVO.getBoard_content());

			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}

	@Override
	public List<BoardVO> getAllBoard() {
		Connection conn = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		List<BoardVO> borList = null;

		try {
			conn = DBUtil3.getConnection();

			String sql = "SELECT * FROM jdbc_board order by board_no desc";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (borList == null)
					borList = new ArrayList<BoardVO>();

				// 한개의 레코드가 저장될 VO 객체 생성
				BoardVO borVo = new BoardVO();

				// ResultSet에서 각 컬럼값들을 가져와 VO의 멤버변수에 저장한다
				borVo.setBoard_no(rs.getInt("BOARD_NO"));
				borVo.setBoard_title(rs.getString("BOARD_TITLE"));
				borVo.setBoard_writer(rs.getString("BOARD_WRITER"));
				borVo.setBoard_date(rs.getString("BOARD_DATE"));
				borVo.setBoard_cnt(rs.getInt("BOARD_CNT"));
				borVo.setBoard_content(rs.getString("BOARD_CONTENT"));

				// 구성된 VO객체를 List에 추가한다
				borList.add(borVo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return borList;
	}

	@Override
	public List<BoardVO> searchBoard(String title) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> borList = null;

		try {
			conn = DBUtil3.getConnection();

			String sql = "SELECT * FROM jdbc_board " + "WHERE board_title LIKE '%' || ? || '%' order by board_no desc";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (borList == null)
					borList = new ArrayList<BoardVO>();

				// 한개의 레코드가 저장될 VO 객체 생성
				BoardVO borVo = new BoardVO();

				// ResultSet에서 각 컬럼값들을 가져와 VO의 멤버변수에 저장한다
				borVo.setBoard_no(rs.getInt("BOARD_NO"));
				borVo.setBoard_title(rs.getString("BOARD_TITLE"));
				borVo.setBoard_writer(rs.getString("BOARD_WRITER"));
				borVo.setBoard_date(rs.getString("BOARD_DATE"));
				borVo.setBoard_cnt(rs.getInt("BOARD_CNT"));
				borVo.setBoard_content(rs.getString("BOARD_CONTENT"));

				// 구성된 VO객체를 List에 추가한다
				borList.add(borVo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return borList;
	}

	@Override
	public BoardVO getABoard(int board_no) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		BoardVO borVo = null;

		try {
			conn = DBUtil3.getConnection();

			String sql = "select board_no, board_title, board_writer, to_char(board_date,'YYYY-MM-DD') board_date, board_cnt, board_content "
					+ " from jdbc_board where board_no = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, board_no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 한개의 레코드가 저장될 VO 객체 생성
				borVo = new BoardVO();

				// ResultSet에서 각 컬럼값들을 가져와 VO의 멤버변수에 저장한다
				borVo.setBoard_no(rs.getInt("BOARD_NO"));
				borVo.setBoard_title(rs.getString("BOARD_TITLE"));
				borVo.setBoard_writer(rs.getString("BOARD_WRITER"));
				borVo.setBoard_date(rs.getString("BOARD_DATE"));
				borVo.setBoard_cnt(rs.getInt("BOARD_CNT"));
				borVo.setBoard_content(rs.getString("BOARD_CONTENT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return borVo;
	}

	@Override
	public int increseCnt(int board_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;

		try {
			conn = DBUtil3.getConnection();

			String sql = "UPDATE jdbc_board SET BOARD_CNT = BOARD_CNT + 1 WHERE BORARD_NO = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, board_no);

			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int board_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;

		try {
			conn = DBUtil3.getConnection();

			String sql = "delete from jdbc_board WHERE board_no = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, board_no);

			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO borVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;

		try {
			conn = DBUtil3.getConnection();

			String sql = "update jdbc_board set board_title = ?, board_content = ?, board_date = sysdate where board_no = ? ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, borVO.getBoard_title());
			pstmt.setString(2, borVO.getBoard_content());
			pstmt.setInt(3, borVO.getBoard_no());

			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
}
