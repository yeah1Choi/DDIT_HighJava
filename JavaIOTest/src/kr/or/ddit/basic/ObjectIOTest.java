package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectIOTest {

	public static void main(String[] args) {
		// Member의 인스턴스 생성
		Member mem1 = new Member("홍길동", 20, "대전");
		Member mem2 = new Member("홍길서", 30, "서울");
		Member mem3 = new Member("홍길남", 40, "부산");
		Member mem4 = new Member("홍길북", 50, "제주");

		// 객체를 파일에 저장하기
		try {
			// 출력용 스트림 객체 생성
			FileOutputStream fout = new FileOutputStream("D:/D_other/member.obj");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			ObjectOutputStream oout = new ObjectOutputStream(bout);
			// 보조스트림을 보조스트림을 가지면서 - 스트림의 입출력 효율을 높임 + 직렬화

			// 출력 작업
			System.out.println("객체 저장 작업 시작");
			oout.writeObject(mem1);
			oout.writeObject(mem2);
			oout.writeObject(mem3);
			oout.writeObject(mem4);

			// readObject()의 EOExeoption 방지(처리)방법 2
			// : 객체를 저장할 때 마지막에 null을 저장 - catch에 잡히지 않음 - 예외자체를 방지함
			oout.writeObject(null);

			System.out.println("객체 저장 작업 완료");

			oout.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		////////////////////////////////////////////////////////

		// 저장된 객체를 읽어와 화면에 출력하기
		try {
			// 입력용 스트림 객체 생성
			ObjectInputStream oin = new ObjectInputStream( // 이런식으로 인스턴스에 역순으로 객체생성이 가능함
					new BufferedInputStream(new FileInputStream("D:/D_other/member.obj")));

			Object obj; // 읽어올 객체를 저장할 변수

			// readObject() : 데이터를 끝까지 다 읽으면 EOExeoption이 발생
			while ((obj = oin.readObject()) != null) {
				// 읽어온 자료를 원래의 객체형으로 형변환 후 사용
				Member mem = (Member) obj;

				System.out.println("이름 : " + mem.getName());
				System.out.println("나이 : " + mem.getAge());
				System.out.println("주소 : " + mem.getAddr());
				System.out.println("----------------------------------");
			}
			// readObject()의 EOExeoption 처리방법 1
			// : catch블록에서 예외처리
		} catch (EOFException e) { // EOF: end of file
			System.out.println("출력 끝");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

class Member implements Serializable {
	private String name;
	
	// transient => 직렬화가 되지 않을 멤버 변수에 지정
	// (직렬화되지 않은 변수는 기본값으로 초기화)
	// (기본값 => 참조형변수 : null, 숫자유형변수 : 0)
	private transient int age;
	private transient String addr;

	// 생성자
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
}