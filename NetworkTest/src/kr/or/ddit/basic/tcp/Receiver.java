package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.net.Socket;

// 이 클래스는 소켓에서 메시지를 받아 화면에 출력하는 역할을 담당하는 쓰레드 클래스
public class Receiver extends Thread {
	private Socket socket;
	private DataInputStream din;

	// 생성자
	public Receiver(Socket socket) {
		this.socket = socket;
		try {
			din = new DataInputStream(this.socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(din != null) {
			try {
				System.out.println(din.readUTF());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}