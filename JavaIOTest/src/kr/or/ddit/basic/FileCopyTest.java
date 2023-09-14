package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

/*
	문제) 
	'D://D_Other'폴더에 있는 '펭귄.jpg'파일을
	'D://D_Other/연습용'폴더에 '펭귄_복사본.jpg'파일로
	 복사하는 프로그램을 작성
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyTest {

	public static void main(String[] args) {
		File file = new File("D:/D_Other/Penguins.jpg");
		
		if(!file.exists()) {
			System.out.println(file.getPath() + " 파일이 없습니다");
			System.out.println("작업 중단");
			return;
		}
		
		try { 
			// 입력용 스트림과 출력용 스트림 객체 생성
			FileInputStream fin = new FileInputStream(file);
			FileOutputStream fout = new FileOutputStream("D:/D_Other/연습용/Penguins_copy.jpg");
			
			BufferedInputStream bfin = new BufferedInputStream(fin);
			BufferedOutputStream bfout = new BufferedOutputStream(fout);
			
			System.out.println("복사 시작");

//			int data;
			
			// 스트림
//			while ((data = fin.read()) != -1) {
//				fout.write(data);
//			}
//			fout.close();
//			fin.close();
			
			// 버퍼 스트림 이용
//			while ((data = bfin.read()) != -1) {
//				bfout.write(data);
//			}
//			bfout.flush();
//			bfin.close();
//			bfout.close();
			
			// 배열 입출력 이용
			byte[] temp = new byte[1024];
			int len = 0; // 한번 읽어들이는 데이터 갯수
			while((len = bfin.read(temp))!=-1) {
				bfout.write(temp,0,len);
			}
			bfout.flush();
			bfin.close();
			bfout.close();
			
			System.out.println("작업 완료");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}