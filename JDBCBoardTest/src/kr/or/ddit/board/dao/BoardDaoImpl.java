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

	@Override
	public int createBoard(BoardVO borVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();

			String sql = "INSERT INTO jdbc_board (board_no, board_title, board_writer, board_date, board_content) "
					+ "VALUES (board_seq.nextVal, ?, ?, SYSDATE, ?)";

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

			String sql = "SELECT * FROM jdbc_board";
			
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
				borVo.setBoard_date(rs.getDate("BOARD_DATE"));
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
	public List<BoardVO> searchBoard(String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		List<BoardVO> borList = null;

		try {
			conn = DBUtil3.getConnection();

			String sql = "SELECT * FROM jdbc_board " + "WHERE board_title LIKE '%' || ? || '%' ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, keyword);

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
				borVo.setBoard_date(rs.getDate("BOARD_DATE"));
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
	public List<BoardVO> getABoard(int board_no) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		List<BoardVO> borList = null;
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "SELECT * FROM jdbc_board WHERE board_no = ? ";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, board_no);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				if (borList == null)
					borList = new ArrayList<BoardVO>();

				// 한개의 레코드가 저장될 VO 객체 생성
				BoardVO borVo = new BoardVO();

				// ResultSet에서 각 컬럼값들을 가져와 VO의 멤버변수에 저장한다
				borVo.setBoard_title(rs.getString("BOARD_TITLE"));
				borVo.setBoard_writer(rs.getString("BOARD_WRITER"));
				borVo.setBoard_date(rs.getDate("BOARD_DATE"));
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

}
