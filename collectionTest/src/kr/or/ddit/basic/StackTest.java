package kr.or.ddit.basic;

import java.util.Stack;

public class StackTest {

	public static void main(String[] args) {
		Browser b = new Browser();
		b.goURL("1.네이버");
		b.history();
		
		b.goURL("2.구글");
		b.history();
		
		b.goURL("3.대덕인재개발원");
		b.history();
		
		b.goURL("4.다음");
		b.history();
		
		System.out.println("뒤로가기 후..");
		b.goBack();
		b.history();
		
		System.out.println("뒤로가기 후..");
		b.goBack();
		b.history();
		
		System.out.println("앞으로가기 후..");
		b.goForward();
		b.history();
		
		System.out.println("새로운 사이트 접속 후..");
		b.goURL("5.네이트");
		b.history();
	}

}

// 웹브라우저의 앞으로 가기, 뒤로가기 기능 구현하기 (stack이용)
class Browser {
	private Stack<String> back; // 이전 방문 내역이 저장될 스택
	private Stack<String> forward; // 다음 방문 내역이 저장될 스택
	private String currentURL;

	// 생성자
	public Browser() {
		back = new Stack<String>();
		forward = new Stack<String>();
		currentURL = "";
	}

	// 사이트를 방문하는 메서드 => 매개변수에 방문할 URL 주소 저장
	public void goURL(String url) {
		System.out.println(url + " 사이트에 접속합니다..");
		System.out.println();

		if (currentURL != null && !"".equals(currentURL)) { // 현재 페이지가 있으면,,
			back.push(currentURL); // 현재 페이지를 back스택에 추가
		}
		currentURL = url; // 현재 페이지를 변경
		forward.clear(); // forward스택의 데이터 전체 삭제
	}

	// 뒤로가기 기능
	public void goBack() {
		// isEmpty 메소드 => Collection에 데이터가 없으면 true, 있으면 false 반환
		if (!back.isEmpty()) { // back 스택에 값이 있으면,,
			forward.push(currentURL); // 현재페이지를 forward 스택에 추가
			currentURL = back.pop(); // back스택에서 1개의 요소를 꺼내 현 페이지로 한다
		}
	}

	// 앞으로가기 기능
	public void goForward() {
		if (!forward.isEmpty()) { // forward 스택이 없으면,,
			back.push(currentURL); // 현재페이지를 back스택에 추가
			currentURL = forward.pop(); // forward스택에서 1개의 요소를 꺼내 현 페이지로 한다
		}
	}

	// 방문 기록 확인하기
	public void history() {
		System.out.println("------------------------------------");
		System.out.println("             <방문기록>");
		System.out.println("------------------------------------");
		System.out.println("   back => " + back);
		System.out.println("      현재페이지 => " + currentURL);
		System.out.println("   forward => " + forward);
		System.out.println("------------------------------------");
		System.out.println();
	}
}