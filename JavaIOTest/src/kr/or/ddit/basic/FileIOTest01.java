package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileIOTest01 {

	public static void main(String[] args) {
		// 바이트 기반의 스트림을 이용하여 파일 내용 읽어와 출력하기
		try {
			// 파일 입력용 스트림 객체 생성

			// 방법 1.
//			FileInputStream fin = new FileInputStream("D://D_Other/test.txt"); // () = 읽어올 파일정보

			// 방법 2.
			File file = new File("D://D_Other/test.txt"); // 1. 파일객체를 만들고
			FileInputStream fin = new FileInputStream(file); // 2. 스트림 객체 만들어 파일 읽기

			int c; // 읽어온 데이터가 저장될 변수
			while ((c = fin.read()) != -1) {
				// 읽어온 데이터를 화면에 출력하기
				System.out.print((char) c); // int -> char
			}
			
			// 작업 완료 후 스트림 닫기
			fin.close();

		} catch (IOException e) {
			System.out.println("입출력 오류입니다");
			e.printStackTrace();
		}
	}
}