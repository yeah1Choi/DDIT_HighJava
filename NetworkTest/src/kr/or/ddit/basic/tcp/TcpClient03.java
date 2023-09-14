package kr.or.ddit.basic.tcp;

import java.net.Socket;

public class TcpClient03 {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("192.168.36.93", 7777);
			System.out.println("서버에 연결되었습니다..");
			System.out.println();
			
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}