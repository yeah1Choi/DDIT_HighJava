package kr.or.ddit.basic;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileTest03 {

	public static void main(String[] args) {
		FileTest03 test = new FileTest03();
		test.displayFileList(new File("D:\\A_TeachingMaterial\\03_HighJava\\workspace"));

	}

	// 디렉토리를 매개변수로 받아서 해당 디렉토리에 있는 모든 파일과 디렉토리 목록을 출력하는 메서드
	public void displayFileList(File dir) {
		if (!dir.isDirectory()) {
			System.out.println("디렉토리(폴더)만 가능!");
			return;
		}
		System.out.println(" ** [" + dir.getAbsolutePath() + "] 디렉토리 내용 ** ");

		// 해당 디렉토리 안에 있는 모든 파일과 디렉토리 목록을 가져온다
		File[] files = dir.listFiles();
//		String[] fileStrs = dir.list(); // String 타입을 꺼내도 File 형식으로 변환해야한다

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd a HH:mm");

		// 가져온 파일과 디렉토리 목록 개수만큼 반복
		for (int i = 0; i < files.length; i++) {
			String fileName = files[i].getName();

			String attr = ""; // 파일의 속성(읽기,쓰기,히든,디렉토리 구분)
			long size = 0;

			if (files[i].isDirectory()) {
				attr = "<DIR>";
			} else {
				size = files[i].length();
				attr = files[i].canRead() ? "R" : "";
				attr += files[i].canWrite() ? "W" : "";
				attr += files[i].isHidden() ? "H" : "";
			}
			System.out.printf("%s %5s %d %s\n", df.format(new Date(files[i].lastModified())), attr, size, fileName);
		}
	}
}