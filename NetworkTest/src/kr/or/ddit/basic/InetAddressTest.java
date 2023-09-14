package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException {
		// InetAddress 클래스 : IP 주소를 다루기 위한 클래스

		// www.naver.comm의 IP정보 구하기
		InetAddress ip = InetAddress.getByName("www.naver.com");
		System.out.println("HostName : " + ip.getHostName());
		System.out.println("HostAddress : " + ip.getHostAddress());
		System.out.println("toString : " + ip.toString());

		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println("내 컴퓨터의 HostName : " + localIp.getHostName());
		System.out.println("내 컴퓨터의 HostAddress : " + localIp.getHostAddress());
		System.out.println();
		
		// IP 주소가 여러개인 호스트의 IP 정보 가져오기
		InetAddress[] ipArr = InetAddress.getAllByName("www.naver.com");
		for(InetAddress nIp : ipArr) {
			System.out.println(nIp.toString());
		}
	}
}