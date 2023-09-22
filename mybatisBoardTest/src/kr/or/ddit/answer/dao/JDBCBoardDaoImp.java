package kr.or.ddit.answer.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.answer.vo.JDBCBoardVO;
import kr.or.ddit.util.MybatisUtil;

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

		SqlSession session = null;

		int cnt = 0;

		try {
			session = MybatisUtil.getSqlSession();

			cnt = session.insert("board.insertBoard", boardVo);

			if (cnt > 0) {
				System.out.println("데이터가 성공적으로 추가되었습니다.");
				// insert, update, delete 작업이 성공하며 commit() 처리
				session.commit();
			} else {
				System.out.println("데이터 추가를 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		SqlSession session = null;

		int cnt = 0;

		try {
			session = MybatisUtil.getSqlSession();

			cnt = session.delete("board.deleteBoard", boardNo);

			if (cnt > 0) {
				System.out.println("데이터가 성공적으로 추가되었습니다.");
				// insert, update, delete 작업이 성공하며 commit() 처리
				session.commit();
			} else {
				System.out.println("데이터 추가를 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return cnt;
	}

	@Override
	public int updateBoard(JDBCBoardVO boardVo) {
		SqlSession session = null;

		int cnt = 0;

		try {
			session = MybatisUtil.getSqlSession();

			cnt = session.delete("board.updateBoard", boardVo);

			if (cnt > 0) {
				System.out.println("데이터가 성공적으로 추가되었습니다.");
				// insert, update, delete 작업이 성공하며 commit() 처리
				session.commit();
			} else {
				System.out.println("데이터 추가를 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return cnt;
	}

	@Override
	public List<JDBCBoardVO> getAllBoard() {
		SqlSession session = null;
		List<JDBCBoardVO> boardList = null;

		try {
			session = MybatisUtil.getSqlSession();
			boardList = session.selectList("board.getAllBoard");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return boardList;
	}

	@Override
	public JDBCBoardVO getBoard(int boardNo) {
		
		SqlSession session = null;
		JDBCBoardVO boardVo = null;

		try {
			session = MybatisUtil.getSqlSession();
			boardVo = session.selectOne("board.getOneBoard", boardNo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return boardVo;
	}

	@Override
	public List<JDBCBoardVO> getSearchBoard(String title) {
		SqlSession session = null;
		List<JDBCBoardVO> boardList = null;

		try {
			session = MybatisUtil.getSqlSession();
			boardList = session.selectList("board.getOneBoard", title);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return boardList;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisUtil.getSqlSession();

			cnt = session.update("board.updateCnt", boardNo);
			
			if (cnt > 0) {
				System.out.println("데이터가 성공적으로 추가되었습니다.");
				// insert, update, delete 작업이 성공하며 commit() 처리
				session.commit();
			} else {
				System.out.println("데이터 추가를 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return cnt;
	}

}
