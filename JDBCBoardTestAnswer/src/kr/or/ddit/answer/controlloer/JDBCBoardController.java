package kr.or.ddit.answer.controlloer;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.answer.service.IJDBCBoardService;
import kr.or.ddit.answer.service.JDBCBoardServiceImpl;
import kr.or.ddit.answer.vo.JDBCBoardVO;

public class JDBCBoardController {
	private IJDBCBoardService service;
	private Scanner scan;

	// 생성자
	public JDBCBoardController() {
		service = JDBCBoardServiceImpl.getInstance();
		scan = new Scanner(System.in);
	}

	public static void main(String[] args) {
		new JDBCBoardController().boardStart();
	}

	// 검색할 제목을 입력받아 반환하는 메서드
	private String searchBoard() {
		scan.nextLine(); // 버퍼비우기
		System.out.println("검색 작업");
		System.out.println("--------------------------------------------");
		System.out.print("- 검색할 제목 입력 : ");
		return scan.nextLine();
	}

	// 게시글 내용을 보여주는 메서드
	private void viewBoard() {
		System.out.println();
		System.out.println("보기를 원하는 게시물 번호 입력 >> ");
		int num = scan.nextInt();

		JDBCBoardVO boardVo = service.getBoard(num);
		if (boardVo == null) {
			System.out.println(num + "등록된 게시물이 하나도 없습니다");
			return;
		}

		System.out.println(num + "번의 게시글 내용");
		System.out.println("------------------------------------------------------------");
		System.out.println("- 제  목 : " + boardVo.getBoard_title());
		System.out.println("- 작성자 : " + boardVo.getBoard_writer());
		System.out.println("- 내  용 : " + boardVo.getBoard_content());
		System.out.println("- 작성일 : " + boardVo.getBoard_date());
		System.out.println("- 조회수 : " + boardVo.getBoard_cnt());
		System.out.println("------------------------------------------------------------");
		System.out.println("메뉴 : 1. 수정 \t 2. 삭제  \t 3. 리스트로가기");
		System.out.print("작업선택 >>");
		int choice = scan.nextInt();
		switch (choice) {
		case 1: // 수정
			updateBoard(num);
			break;
		case 2: // 삭제
			deleteBoard(num);
			break;
		case 3: // 리스트

			break;
		}
	}

	// 게시글을 삭제하는 메서드
	private void deleteBoard(int boardNo) {
		int cnt = service.deleteBoard(boardNo);

		if (cnt > 0) {
			System.out.println(boardNo + "번 글이 삭제되었습니다.");
		} else {
			System.out.println(boardNo + "번 글이 삭제 실패입니다");
		}
	}

	// 게시글을 수정하는 메서드
	private void updateBoard(int boardNo) {
		scan.nextLine(); // 버퍼비우기

		System.out.println();
		System.out.println("수정 작업하기");
		System.out.println("-----------------------------------");

		System.out.print("- 제  목 : ");
		String newTitle = scan.nextLine();

		System.out.print("- 내  용 : ");
		String newContent = scan.nextLine();

		JDBCBoardVO boardVo = new JDBCBoardVO();
		boardVo.setBoard_title(newTitle);
		boardVo.setBoard_content(newContent);
		boardVo.setBoard_no(boardNo);

		int cnt = service.updateBoard(boardVo);

		if (cnt > 0) {
			System.out.println(boardNo + "번 글이 수정되었습니다.");
		} else {
			System.out.println(boardNo + "번 글이 수정 실패입니다");
		}
	}

	// 새글 작성 메서드
	private void insertBoard() {

		scan.nextLine(); // 버퍼비우기
		System.out.println();
		System.out.println("새글 작성하기");
		System.out.println("-----------------------------------");

		System.out.print("- 제  목 : ");
		String title = scan.nextLine();

		System.out.print("- 작성자 : ");
		String writer = scan.nextLine();

		System.out.print("- 내  용 : ");
		String content = scan.nextLine();

		// 입력받은 데이터를 VO에 저장
		JDBCBoardVO boardVo = new JDBCBoardVO();

		boardVo.setBoard_title(title);
		boardVo.setBoard_writer(writer);
		boardVo.setBoard_content(content);

		int cnt = service.insertBoard(boardVo);

		if (cnt > 0) {
			System.out.println("새 글이 추가되었습니다.");
		} else {
			System.out.println("새 글 추가를 실패했습니다.");
		}
	}

	// 시작메서드
	public void boardStart() {
		System.out.println("*************************");
		System.out.println("	게 시 판 프 로 그 램");
		System.out.println("*************************");

		String searchTitle = null;
		int choice = 0;
		while (true) {
			if(choice !=3) { // 이전의 작업 번호를 확인
				searchTitle = null;
			}
			choice = displayMenu(searchTitle);
			
			switch (choice) {
			case 1: // 새글작성
				insertBoard();
				break;
			case 2: // 게시글보기
				viewBoard();
				break;
			case 3: // 검색
				searchTitle = searchBoard();
				break;
			case 0: // 프로그램 종료
				System.out.println("게시판 프로그램 종료....");
				return;
			default:
				System.out.println("작업 번호를 잘못 입력하였습니다");
				System.out.println("다시 입력하세요");
			}
		}
	}

	// 게시글목록을 보여주고 메뉴를 나타내며 사용자가 입력한 메뉴 번호를 반환하는 메서드
	private int displayMenu(String title) {

		List<JDBCBoardVO> boardList = null;
		if (title == null) {
			boardList = service.getAllBoard();
		} else {
			boardList = service.getSearchBoard(title);
		}

		System.out.println("-------------------------------------------------------------");
		System.out.println(" No	        제 목            작성자 	조회수");
		System.out.println("-------------------------------------------------------------");

		if (boardList == null || boardList.size() == 0) {
			System.out.println("등록된 게시물이 하나도 없습니다");
		} else {
			for (JDBCBoardVO boardVo : boardList) {
				System.out.println(boardVo.getBoard_no() + "\t" + boardVo.getBoard_title() + "\t"
						+ boardVo.getBoard_writer() + "\t" + boardVo.getBoard_cnt());
			}
		}
		System.out.println("-------------------------------------------------------------");
		System.out.println("메뉴 : 1. 새글작성 \t 2. 게시글보기  \t 3. 검색  \t 0.작업 끝");
		System.out.print("작업선택 >>");

		return scan.nextInt();
	}
}
