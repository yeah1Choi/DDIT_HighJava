package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedIOTest01 {

	public static void main(String[] args) {
		// 입출력의 성능 향상을 위해 Buffered 스트림을 사용
		try {
			FileOutputStream fout = new FileOutputStream("D://D_Other/bufferTest.txt"); // 기반스트림
			
			BufferedOutputStream bout = new BufferedOutputStream(fout, 5); // 보조스트림
			
			for(int i='1';i<'9';i++) {
				bout.write(i);
			}
			
//			bout.flush();  // flush(): 출력 버퍼에 남아 있는 데이터를 강제로 모두 출력시킨다
			
			bout.close(); 
			// 보조스트림을 닫으면 보조스트림이 사용한 기반스트림도 닫음(또 닫을 필요없음)
			// Buffered 스트림의 close()는 flush()기능이 내장
			
			System.out.println("작업 끝");
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}