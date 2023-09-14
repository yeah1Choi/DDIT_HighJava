package kr.or.ddit.basic.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer03 {

	public static void main(String[] args) throws IOException {
		// ServerSocket를 생성하고, 클라이언트가 접속해오면 소켓을 만들어서
		// 메시지를 받는 쓰레드와 메시지를 보내는 쓰레드에 이 소켓을 넘겨준다
		ServerSocket server = new ServerSocket(0122);

		System.out.println("서버가 준비 중 입니다..");

		Socket socket = server.accept();

		Sender sender = new Sender(socket);
		Receiver receiver = new Receiver(socket);

		sender.start();
		receiver.start();
	}
}