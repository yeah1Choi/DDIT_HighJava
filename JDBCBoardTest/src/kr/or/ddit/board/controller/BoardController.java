package kr.or.ddit.board.controller;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.BoardVO;

public class BoardController {

	private IBoardService service; // Service객체 변수 선언
	private Scanner scan = new Scanner(System.in);

	// 생성자
	public BoardController() {
		service = new BoardServiceImpl();
	}

	public static void main(String[] args) {
		new BoardController().startProgram();
	}
	
	// 게시물 검색 메서드
	private void search() {
		System.out.println("	* 검색 작업 *");
		System.out.println("-----------------------------------");
		
		System.out.print("- 검색할 제목 입력 : ");
		String searchKeyword = scan.nextLine();
		
		List<BoardVO> borList = service.searchBoard(searchKeyword);
		
		System.out.println("-------------------------------------------------------------");
		System.out.println(" No	        제 목            작성자 	조회수");
		System.out.println("-------------------------------------------------------------");

		if (borList == null || borList.size() == 0) {
			System.out.println("등록된 게시물이 하나도 없습니다");
		} else {
			for (BoardVO borVo : borList) {
				System.out.println(borVo.getBoard_no() + "\t" + borVo.getBoard_title() + "\t" + 
				borVo.getBoard_writer() + "\t" + borVo.getBoard_cnt());
			}
			System.out.println("-------------------------------------------------------------");
		}
	}
	
	// 게시물 보기 메서드 - 게시물번호가 키값
	// 이 메서드를 통해 열람한 게시물의 조회수가 하나 올라간다
	private void read() {
		
		int cntlook; // 조회수 저장 할 변수	
		
		System.out.println("	* 게시물 보기 *");
		System.out.println("-----------------------------------");
		
		System.out.print("보기를 원하는 게시물 번호 입력 >>");
		int boardNo = scan.nextInt();
		
		List<BoardVO> borList = service.getABoard(boardNo);
		
		BoardVO borVo = borList.get(0);
		
		cntlook = borVo.getBoard_cnt();
		cntlook++;
		 
		System.out.println(boardNo+"번글 내용");
		System.out.println("------------------------------------------------------------");
		System.out.println("- 제  목 : "+borVo.getBoard_title());
		System.out.println("- 작성자 : "+borVo.getBoard_writer());
		System.out.println("- 내  용 : "+borVo.getBoard_content());
		System.out.println("- 작성일 : "+borVo.getBoard_date());
		System.out.println("- 조회수 : " + cntlook);
		System.out.println("------------------------------------------------------------");

		// 조회수 다시 저장
		BoardVO borVO2 = new BoardVO();
		
		borVO2.setBoard_cnt(cntlook);
		borVO2.setBoard_no(boardNo);
		
		int cnt = service.updateCnt(borVO2);
		
		if (cnt > 0) {
			System.out.println("조회수 데이터가 성공적으로 수정되었습니다.");
		} else {
			System.out.println("조회수 데이터 수정을 실패했습니다.");
		}
	}

	// 새글 작성 메서드
	private void create() {
		System.out.println("	* 새글 작성하기 *");
		System.out.println("-----------------------------------");
		
		// 입력 외 부여해야할 데이터 (순번, 작성일, 조회수)
		
		// 입력 데이터 (제목, 작성자, 내용)
		System.out.print("- 제  목 : ");
		String title = scan.next();

		System.out.print("- 작성자 : ");
		String writer = scan.next();

		scan.nextLine();
		System.out.print("- 내  용 : ");
		String content = scan.nextLine();
		
		// 입력 받은 데이터들을 VO객체에 저장
		BoardVO borVO = new BoardVO();
		
		borVO.setBoard_title(title);
		borVO.setBoard_writer(writer);
		borVO.setBoard_content(content);
		
		int cnt = service.createBoard(borVO);
		
		if (cnt > 0) {
			System.out.println("새 글이 추가되었습니다.");
		} else {
			System.out.println("새 글 추가를 실패했습니다.");
		}
	
	}

	// 시작 메서드
	public void startProgram() {
		System.out.println("*************************");
		System.out.println("	 게 시 판 프 로 그 램");
		System.out.println("*************************");

		while (true) {
			int choice = displayMenu();
			switch (choice) {
			case 1: // 새 글 작성
				create();
				break;
			case 2: // 게시글보기 (게시글 중 하나 선택)
				read();
				break;
			case 3: // 검색
				search();
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

	// 메뉴 메소드 - 전체 리스트 같이 출력
	private int displayMenu() {

		List<BoardVO> borList = service.getAllBoard();

		System.out.println("-------------------------------------------------------------");
		System.out.println(" No	        제 목            작성자 	조회수");
		System.out.println("-------------------------------------------------------------");

		if (borList == null || borList.size() == 0) {
			System.out.println("등록된 게시물이 하나도 없습니다");
		} else {
			for (BoardVO borVo : borList) {
				System.out.println(borVo.getBoard_no() + "\t" + borVo.getBoard_title() + "\t" + 
				borVo.getBoard_writer() + "\t" + borVo.getBoard_cnt());
			}
			System.out.println("-------------------------------------------------------------");
		}

		System.out.println("메뉴 : 1. 새글작성 \t 2. 게시글보기  \t 3. 검색  \t 0.작업 끝");
		System.out.print("작업선택 >>");
		return scan.nextInt();
	}
}
