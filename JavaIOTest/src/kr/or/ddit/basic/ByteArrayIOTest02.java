package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest02 {

	public static void main(String[] args) {
		byte[] inSrc = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		byte[] outSrc = null;

		byte[] temp = new byte[4];

		// 스트림 객체 생성
		ByteArrayInputStream bin = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();

		try {
			// available() : 읽어올 수 있는(남아있는) 데이터의 개수 반환
			while (bin.available() > 0) {
//				bin.read(temp); // 한번에 배열 4개씩(배열의 크기만큼) 읽어온다
//				bout.write(temp); // 배열의 데이터 출력한다
				
//				System.out.println("반복문 안에서 temp : "+Arrays.toString(temp));
				/* 반복문 안에서 temp : [0, 1, 2, 3]
				      반복문 안에서 temp : [4, 5, 6, 7]
				      반복문 안에서 temp : [8, 9, 6, 7] 
			       // 4개짜리 배열에 새로 값을 덮어 쓰는데 마지막은 남는 두 값을 넣고 그 전에 있던 값이 남아 있어서 출력 값이 다름
			    */
				
				// read(byte[] 배열명) : 이 메소드의 반환값은 실제 읽어온 데이터 갯수
				int len = bin.read(temp);
				
				// temp 배열의 내용 중 0번째 부터 len 개수만큼 출력한다
				bout.write(temp, 0, len);
			}

			outSrc = bout.toByteArray();

			System.out.println("inSrc : " + Arrays.toString(inSrc)); // inSrc : [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
			System.out.println("outSrc : " + Arrays.toString(outSrc)); // outSrc : [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 6, 7]
			// 출력 값이 다른 이유 위의  bin.read(temp);, bout.write(temp);에서 
			
			// 스트림닫기
			bin.close();
			bout.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}
