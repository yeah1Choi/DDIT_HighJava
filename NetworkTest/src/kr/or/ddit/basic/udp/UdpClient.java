package kr.or.ddit.basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// 수신 받은 데이터가 저장될 byte형 배열 선언
		byte[] bMsg = new byte[512];

		try {
			DatagramSocket socket = new DatagramSocket();

			// 접속할 상대방의 주소 정보 생성
//			InetAddress address = InetAddress.getByName("127.0.0.1");
			InetAddress address = InetAddress.getByName("localhost");

			// 수신용, 송신용 패킷 객체 변수 선언
			DatagramPacket inpacket, outpacket;

			while (true) {
				// 전송할 메시지를 입력받는다
				System.out.println("보낼 메시지 입력>>");
				String msg = scan.nextLine();

				// 전송용(송신용) 패킷 객체 생성
				outpacket = new DatagramPacket(msg.getBytes("utf-8"), msg.getBytes("utf-8").length, address, 8888);

				// 데이터 전송
				socket.send(outpacket);

				if ("/end".equals(msg)) {
					break;
				}

				// --------------------------------------

				// 서버에서 온 데이터를 받아 출력
				inpacket = new DatagramPacket(bMsg, bMsg.length);

				// 데이터 수신
				socket.receive(inpacket);

				String receiveMsg = new String(inpacket.getData(), 0, inpacket.getLength(), "utf-8");

				System.out.println("서버의 응답 메시지 >>" + receiveMsg);
				System.out.println();

			} // while끝

			System.out.println("작업 끝");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}