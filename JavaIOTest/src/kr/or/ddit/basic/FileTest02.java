package kr.or.ddit.basic;

import java.io.File;

public class FileTest02 {

	public static void main(String[] args) {
		File f1 = new File("D://D_Other/test.txt");
		
		System.out.println(f1.getName()+" 파일의 크기 : "+f1.length() + "bytes"); // test.txt 파일의 크기 : 82bytes
		System.out.println("path : "+f1.getPath()); // path : D:\D_Other\test.txt
		System.out.println("absolutePath : "+f1.getAbsolutePath()); // absolutePath : D:\D_Other\test.txt
		
		File f2 = new File("."); // '.' : 현재경로
		
		System.out.println("path : "+f2.getPath()); // path : .
		System.out.println("absolutePath : "+f2.getAbsolutePath()); 
		// absolutePath : D:\A_TeachingMaterial\03_HighJava\workspace\JavaIOTest\.
		// 이클립스에서 자바 프로그램이 실행되는 위치는 해당 프로그램이 위치하는  프로젝트 (즉 '프로젝트이름'에 해당하는 폴더)
		
		System.out.println();
		
		File f3 = new File("D://D_Other/sample.exe");
		
		if(f3.isFile()) {
			System.out.println(f3.getName()+"은(는) 파일입니다.");
		} else if (f3.isDirectory()) {
			System.out.println(f3.getName()+"은(는) 디렉토리입니다.");
		} else { 
			System.out.println(f3.getName()+"은(는) 무엇일까요..");
		} // sample.exe은(는) 무엇일까요..
		// 현재 디스크에 존재하지 않는 파일 정보를 갖는 File 객체는 파일 또는 디렉토리 여부 검사 못함
	}
}