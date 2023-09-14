package kr.or.ddit.basic;

import java.awt.Panel;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DialogTest {

	public static void main(String[] args) {
		// AWT => SWING => javaFX

		// SWING의 파일열기,저장 창 연습
		JFileChooser chooser = new JFileChooser();

		// 선택할 파일의 종류 설정하기 (파일의 확장자를 이용하여 구별한다)
		FileNameExtensionFilter txt = new FileNameExtensionFilter("Text파일(*.txt)", "txt");
		FileNameExtensionFilter img = new FileNameExtensionFilter("그림파일", "png", "jpg", "gif");
		FileNameExtensionFilter doc = new FileNameExtensionFilter("MS Word파일", new String[] { "doc", "docx" }); // new String[]{"doc","docx"} 가변형 배열

		chooser.addChoosableFileFilter(txt);
		chooser.addChoosableFileFilter(img);
		chooser.addChoosableFileFilter(doc);
		
//		chooser.setAcceptAllFileFilterUsed(true); // '모든파일' 항목의 표시 여부 설정 (true 설정(기본값) / false 해제)
		
		// Dialog창에 나타날 기본 경로 설정 ('D:/D_other'로 설정)
		chooser.setCurrentDirectory(new File("D:/D_other"));

//		int result = chooser.showOpenDialog(new Panel()); // 열기용
		int result = chooser.showSaveDialog(new Panel()); // 저장용

		if (result == JFileChooser.APPROVE_OPTION) { // APPROVE_OPTION: 창에서 '저장' 또는 '열기' 버튼을 눌렀을 때를 검사
			File selectedFile = chooser.getSelectedFile();
			System.out.println("선택한 파일: " + selectedFile.getAbsolutePath());
		}
	}
}