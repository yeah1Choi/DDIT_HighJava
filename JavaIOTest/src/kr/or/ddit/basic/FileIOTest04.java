package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FileIOTest04 {

	public static void main(String[] args) {
		// 사용자가 입력한 내용을 그대로 파일에 저장하기
		try {
			// System.in => 콘솔(표준 입출력 장치) 입력장치의 입력용 스트림 객체가 저장되어있다.(바이트 기반 스트림)
//			new Scanner(System.in);
			
			// 입력용 바이트 기반 스트림을 문자 기반 스트림으로 변환하기
			InputStreamReader isr = new InputStreamReader(System.in);
			
			// 문자 기반의 파일 출력용 스트림 객체 생성
			FileWriter fw = new FileWriter("D://D_Other/testChar.txt");
			
			System.out.println("아무 내용이나 입력하세요.(입력의 끝은 Ctrl+Z 입니다.)");
			
			int c;
			
			while ((c = isr.read()) != -1) { // 콘솔로 입력받기
				fw.write(c); // 입력받은 데이터를 파일로 출력하기
			}
			
			isr.close();
			fw.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
