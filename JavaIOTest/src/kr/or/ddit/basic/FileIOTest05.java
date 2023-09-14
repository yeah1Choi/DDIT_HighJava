package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
	한글이 저장된 파일 읽어오기
	(한글의 인코딩 방식을 지정해서 읽어오기)
*/
public class FileIOTest05 {

	public static void main(String[] args) {
		try {
//			FileReader fr = new FileReader("D://D_Other/test_ansi.txt");
//			FileReader fr = new FileReader("D://D_Other/test_utf.txt");
			
			FileInputStream fin = 
					new FileInputStream("D://D_Other/test_ansi.txt");
//					new FileInputStream("D://D_Other/test_utf.txt");
			
			// 기본 인코딩 방식으로 읽어온다.
			InputStreamReader isr = new InputStreamReader(fin);
			
			// 인코딩 방식을 지정해서 읽어오기
			// 인코딩 방식 예시
			// - MS949 => 윈도우의 기본 한글 인코딩 방식 (ANSI방식)
			// - UTF-8 => 유니코드 UTF-8 인코딩 방식
			// - US-ASCII => 영문 전용 인코딩 방식
			InputStreamReader isr = new InputStreamReader(fin);

			int c;
			while ((c = fr.read()) != -1) {
				System.out.println((char) c);
			}
			
			fr.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}