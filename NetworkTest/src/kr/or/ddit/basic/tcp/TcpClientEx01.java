package kr.or.ddit.basic.tcp;

import java.awt.Panel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
- 클라이언트 프로그램 => 클라이언트 프로그램은 서버와 접속이 완료되면
	'D:/D_other'폴더에 있는 '펭귄.jpg'파일을 서버로 전송한다
 */
public class TcpClientEx01 {

	public void clientStart() {
		// 전송할 파일 정보를 갖는 File 객체 생성
		File file = new File("D:/D_Other/Penguins.jpg");

		if (!file.exists()) { // 파일 부재 예외처리
			System.out.println(file.getPath() + " 파일이 없습니다");
			System.out.println("작업 중단");
			return;
		}

		Socket socket = null;
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		DataOutputStream dout = null;

		try {
			socket = new Socket("localhost", 1234); // 데이터 전송을 위한 접속 서버명과 서버포트

			System.out.println("파일 전송 시작");

			// 파일 입력용 스트림 객체 생성
			bin = new BufferedInputStream(new FileInputStream(file));

			// 소켓으로 출력할 스트림 객체 생성
			dout = new DataOutputStream(socket.getOutputStream());
			bout = new BufferedOutputStream(dout); // 전송속도를 위한 버퍼

			// 서버에 접속하면 처음으로 파일명을 전송한다
			dout.writeUTF(file.getName());

			// 파일이름 전송이 완료되면 파일의 내용을 읽어서 전송
			byte[] temp = new byte[1024];
			int len = 0;
			while ((len = bin.read(temp)) != -1) {
				bout.write(temp, 0, len);
			}
			// 파일을 읽는데 사용되는 BufferedInputStream 객체 bin에서 데이터를 읽어와(read)
			// temp라는 임시 바이트 배열에 저장
			// 현재 읽은 데이터의 길이 반환값을 len에 저장
			// 반복해서 파일을 끝까지 읽는데 read는 파일 끝에 도달하면 -1을 반환함으로 -1될때까지 반복해 데이터를 읽어냄
			// 읽어온 데이터를 BufferedOutputStream 객체 bout을 통해 서버로 전송(write)
			// temp배열에서 len만큼 데이터를 읽어와 서버로 전송
			// while문의 블록 형식으로 읽어 서버 전송 -> 대용량 파일도 가능한 효과적인 전송방법
			
			bout.flush(); // 출력 스트림을 비워 모든 데이터가 전송되도록 보장

			System.out.println("파일 전송 완료");

		} catch (Exception e) {
			System.out.println("파일 전송 실패");
		} finally {
            try {
                if (bin != null) bin.close();
                if (bout != null) bout.close();
                if (dout != null) dout.close();
                if (socket != null) socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}

	   public void showDialog() {
		   
			// SWING의 파일열기,저장 창 연습
			JFileChooser chooser = new JFileChooser();

			// 선택할 파일의 종류 설정하기 (파일의 확장자를 이용하여 구별한다)
			FileNameExtensionFilter txt = new FileNameExtensionFilter("Text파일(*.txt)", "txt");
			FileNameExtensionFilter img = new FileNameExtensionFilter("그림파일", "png", "jpg", "gif");
			FileNameExtensionFilter doc = new FileNameExtensionFilter("MS Word파일", new String[] { "doc", "docx" }); // new String[]{"doc","docx"} 가변형 배열

			chooser.addChoosableFileFilter(txt);
			chooser.addChoosableFileFilter(img);
			chooser.addChoosableFileFilter(doc);
			
			chooser.setAcceptAllFileFilterUsed(true); // '모든파일' 항목의 표시 여부 설정 (true 설정(기본값) / false 해제)
			
			// Dialog창에 나타날 기본 경로 설정 ('D:/D_other'로 설정)
			chooser.setCurrentDirectory(new File("D:/D_other"));

//			int result = chooser.showOpenDialog(new Panel()); // 열기용
			int result = chooser.showSaveDialog(new Panel()); // 저장용

			if (result == JFileChooser.APPROVE_OPTION) { // APPROVE_OPTION: 창에서 '저장' 또는 '열기' 버튼을 눌렀을 때를 검사
				File selectedFile = chooser.getSelectedFile();
				System.out.println("선택한 파일: " + selectedFile.getAbsolutePath());
			}

	   }
	
	public static void main(String[] args) {
		TcpClientEx01 client = new TcpClientEx01();
        //client.clientStart();
        
        client.showDialog();
	}
}