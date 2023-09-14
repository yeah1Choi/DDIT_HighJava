package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
<파일을 전송하는 프로그램 작성하기>
- 클라이언트 프로그램 => 클라이언트 프로그램은 서버와 접속이 완료되면
	'D:/D_other'폴더에 있는 '펭귄.jpg'파일을 서버로 전송한다
	
- 서버용 프로그램 => 서버용 프로그램은 클라이언트와 접속을 성공하고 
	클라디언트가 보내온 파일 데이터를 받아서 'D:/D_other/uploads/'폴더에 저장한다
*/
public class TcpServerEx01 {

	public void serverStart() {
		// File 객체 생성 : 저장될 파일 경로를 지정
		File saveDir = new File("D:/D_Other/uploads/");

		// 저장폴더 없으면 새로 만든다
		if (!saveDir.exists()) {
			saveDir.mkdirs();
			return;
		}

		Socket socket = null;
		DataInputStream din = null;
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		ServerSocket server = null;

		try {
			server = new ServerSocket(1234); // 서버소켓생성
			System.out.println("서버가 준비되었습니다");

			socket = server.accept(); // 클라이언트의 접속을 기다린다

			System.out.println("파일 수신 시작");

			// 스트림 객체 생성
			din = new DataInputStream(socket.getInputStream());
			bin = new BufferedInputStream(din);

			// 클라이언트가 처음으로 보낸 자료명인 파일명을 받는다
			String filename = din.readUTF();

			// 수신한 파일명과 저장할 폴더를 이용한 File객체 생성
			File file = new File(saveDir, filename);

			bin = new BufferedInputStream(din);
			bout = new BufferedOutputStream(new FileOutputStream(file));

			// 수신한 파일 데이터를 받아서 파일로 저장한다
			byte[] temp = new byte[1024];
			int len = 0;
			while ((len = bin.read(temp)) != -1) {
				bout.write(temp, 0, len);
			}
			bout.flush();

			System.out.println("파일 수신 완료");

		} catch (Exception e) {
			System.out.println("파일 수신 실패");
		}  finally {
            try {
            	if (din != null) din.close();
                if (bin != null) bin.close();
                if (bout != null) bout.close();
                if (socket != null) socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}

	public static void main(String[] args) {
		TcpServerEx01 sv = new TcpServerEx01();
		sv.serverStart();
	}
}