package kr.or.ddit.basic.tcp;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

// 이 클래스는 소켓을 통해 메시지를 보내는 역할을 담당하는 쓰레드 클래스
public class Sender extends Thread {
	private Socket socket;
	private DataOutputStream dout;

	private Scanner scan;
	private String name;

	// 생성자
	public Sender(Socket socket) {
		this.socket = socket;
		scan = new Scanner(System.in);

		System.out.println("대화명 입력 >>");
		name = scan.nextLine();
		try {
			dout = new DataOutputStream(this.socket.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (dout != null) {
			try {
				dout.writeUTF(name + " : " + scan.nextLine());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}