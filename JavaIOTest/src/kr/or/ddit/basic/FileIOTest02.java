package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOTest02 {

	public static void main(String[] args) {
		// 바이트 기반 스트림을 이용한 파일로 출력하기
		try {
			FileOutputStream fout = new FileOutputStream("D://D_Other/out.txt");
			for (char ch = 'A'; ch <= 'Z'; ch++) {
				fout.write(ch); // ch변수의 데이터를 파일로 출력
			}
			System.out.println("출력 작업 완료");
			fout.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}