package kr.or.ddit.basic;

import java.io.File;

public class FileTest01 {

	public static void main(String[] args) {
		// File 객체 만들기 연습

		// 1. new File(String 파일 또는 경로) =>
		// 디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의 구분 문자는 '/'를 사용하거나 '\'를 사용할 수 있다

//		File file1 = new File("D://D_Other/test.txt"); // 구분문자 '/'
		File file1 = new File("D:\\D_Other\test.txt"); // 구분문자 '\' // 파일객체

		System.out.println("파일명 : " + file1.getName()); // 파일명 : D_Other est.txt
		System.out.println("디렉토리 여부 : " + file1.isDirectory()); // 디렉토리 여부 : false
		System.out.println("파일 여부 : " + file1.isFile()); // 파일 여부 : false

		File file2 = new File("D://D_Other"); // 디렉토리 객체

		System.out.println("파일명 : " + file2.getName()); // 파일명 : D_Other
		System.out.println("디렉토리 여부 : " + file2.isDirectory()); // 디렉토리 여부 : true
		System.out.println("파일 여부 : " + file2.isFile()); // 파일 여부 : false

		// 2. new File(File parent, String child) =>
		// 'parent'디렉토리 안에 있는 'child'파일을 갖는다

		File file3 = new File(file2, "test.txt");

		System.out.println("파일명 : " + file3.getName()); // 파일명 : test.txt
		System.out.println("디렉토리 여부 : " + file3.isDirectory()); // 디렉토리 여부 : false
		System.out.println("파일 여부 : " + file3.isFile()); // 파일 여부 : true

		// 3. new File(String parent, String child) =>
		// 'parent'디렉토리 안에 있는 'child'파일을 갖는다

		File file4 = new File("D://D_Other", "test.txt");

		System.out.println("파일명 : " + file4.getName()); // 파일명 : test.txt
		System.out.println("디렉토리 여부 : " + file4.isDirectory()); // 디렉토리 여부 : false
		System.out.println("파일 여부 : " + file4.isFile()); // 파일 여부 : true

		System.out.println("---------------------------------------------------------------");

/*
 < 디렉토리(폴더) 만들기 > 
 - mkdir() : File 객체 전체의 경로 중 마지막 위치의 디렉토리를 만든다 
           : 반환값 : 만들기 성공(true), 만들기 실패(false)
           : 전체 경로 중 중간부분의 경로가 모두 만들어져 있어야 마지막 위치의 디덱토리(폴더)를 만들 수 있다
 - mkdirs() : 중간 부분의 경로가 없으면 중간 부분의 경로도 같이 만들어 준다
 */
		
		File file5 = new File("D://D_Other/연습용");

		System.out.println(file5.getName() + "의 존재 여부 : " + file5.exists()); // 연습용의 존재 여부 : false

		if (!file5.exists()) { // 존재해야 실행
			if (file5.mkdir()) {
				System.out.println(file5.getName() + ", 만들기 성공 ❤");
			} else {
				System.out.println(file5.getName() + ", 만들기 실패 💣");
			}
		}
		System.out.println();
		
		File file6 = new File("D://D_Other/test/java/src");
		
		System.out.println(file6.getName() + "의 존재 여부 : " + file6.exists()); // src의 존재 여부 : false
		
		if(file6.mkdirs()) {
			System.out.println(file6.getName() + ", 만들기 성공 ❤");
		} else {
			System.out.println(file6.getName() + ", 만들기 실패 💣");
		} // src, 만들기 성공 ❤ <= mkdirs: 경로에 없던 파일경로가 있더라도 그 없는 경로의 파일도 모두 만들어 준다
	}
}