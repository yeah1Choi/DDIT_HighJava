package kr.or.ddit.answer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.answer.service.JDBCBoardServiceImpl;
import kr.or.ddit.answer.utill.DBUtil3;
import kr.or.ddit.answer.vo.JDBCBoardVO;

public class JDBCBoardDaoImp implements IJDBCBoardDao {

	private static JDBCBoardDaoImp dao;

	private JDBCBoardDaoImp() {
	}

	public static JDBCBoardDaoImp getInstance() {
		if (dao == null)
			dao = new JDBCBoardDaoImp();
		return dao;
	}

	@Override
	public int insertBoard(JDBCBoardVO boardVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();

			String sql = "INSERT INTO jdbc_board (board_no, board_title, board_writer, board_date, board_cnt, board_content) "
					+ "VALUES (board_seq.nextVal, ?, ?, SYSDATE, 0,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_writer());
			pstmt.setString(3, boardVo.getBoard_content());

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
	public int deleteBoard(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;

		try {
			conn = DBUtil3.getConnection();

			String sql = "delete from jdbc_board WHERE board_no = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, boardNo);

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
	public int updateBoard(JDBCBoardVO boardVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();

			String sql = "update jdbc_board set board_title = ?, board_content = ?, board_date = sysdate where board_no = ? ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_content());
			pstmt.setInt(3, boardVo.getBoard_no());

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
	public List<JDBCBoardVO> getAllBoard() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<JDBCBoardVO> boardList = null;

		try {
			conn = DBUtil3.getConnection();

			String sql = "SELECT * FROM jdbc_board order by board_no desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (boardList == null)
					boardList = new ArrayList<JDBCBoardVO>();

				JDBCBoardVO boardVo = new JDBCBoardVO();
				boardVo.setBoard_no(rs.getInt("board_no"));
				boardVo.setBoard_title(rs.getString("board_title"));
				boardVo.setBoard_writer(rs.getString("board_writer"));
				boardVo.setBoard_date(rs.getString("board_date"));
				boardVo.setBoard_cnt(rs.getInt("board_cnt"));
				boardVo.setBoard_content(rs.getString("board_content"));

				boardList.add(boardVo);
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
		return boardList;
	}

	@Override
	public JDBCBoardVO getBoard(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JDBCBoardVO boardVo = null;

		try {
			conn = DBUtil3.getConnection();

			String sql = "select board_no, board_title, board_writer, to_char(board_date,'YYYY-MM-DD') board_date, board_cnt, board_content "
					+ " from jdbc_board where board_no = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				boardVo = new JDBCBoardVO();

				boardVo.setBoard_no(rs.getInt("board_no"));
				boardVo.setBoard_title(rs.getString("board_title"));
				boardVo.setBoard_writer(rs.getString("board_writer"));
				boardVo.setBoard_date(rs.getString("board_date"));
				boardVo.setBoard_cnt(rs.getInt("board_cnt"));
				boardVo.setBoard_content(rs.getString("board_content"));
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
		return boardVo;
	}

	@Override
	public List<JDBCBoardVO> getSearchBoard(String title) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<JDBCBoardVO> boardList = null;

		try {
			conn = DBUtil3.getConnection();

			String sql = "select * from jdbc_board where board_title like '%' || ? || '%' order by board_no desc";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (boardList == null)
					boardList = new ArrayList<JDBCBoardVO>();

				JDBCBoardVO boardVo = new JDBCBoardVO();
				boardVo.setBoard_no(rs.getInt("board_no"));
				boardVo.setBoard_title(rs.getString("board_title"));
				boardVo.setBoard_writer(rs.getString("board_writer"));
				boardVo.setBoard_date(rs.getString("board_date"));
				boardVo.setBoard_cnt(rs.getInt("board_cnt"));
				boardVo.setBoard_content(rs.getString("board_content"));

				boardList.add(boardVo);
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
		return boardList;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();

			String sql = "update jdbc_board set board_cnt = board_cnt + 1 where board_no = ? ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, boardNo);

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
