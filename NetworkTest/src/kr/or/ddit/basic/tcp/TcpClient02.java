package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient02 {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		try {
			String serverIP = "localhost";

			System.out.println(serverIP + "서버에 연결 중입니다");

			Socket socket = new Socket(serverIP, 7777);

			System.out.println("서버에 연결되었습니다.");
			System.out.println();

			// 데이터 송수신을 위한 스트림 객체 생성
			// 수신용
			DataInputStream din = new DataInputStream(socket.getInputStream());
			// 송신용
			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());

			while (true) {
				System.out.println("서버로 보낼 메시지 입력 >>");
				String str = scan.nextLine();
				
				// 입력 받은 메시지를 서버로 전송한다
				dout.writeUTF(str);
				
				if(str.equals("/end")) break;
				
				System.out.println("서버에서 보내온 메시지 : " + din.readUTF());
				System.out.println();
			}

			din.close();
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}