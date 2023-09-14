package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// 문제 ) 
//	학번(int), 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student 클래스를 만든다
//  이 클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서 초기화한다
//  (이 때 총점은 세 과목의 점수를 이용해서 초기화한다)

//  이 Student객체는 List에 저장 관리한다

// 	List에 저장된 데이터들을 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현하고 
// 	총점의 역순(내림차순)으로 정렬하는데 총점이 같으면 그 이름의 오름차순으로 정렬이 되는 외부 정렬 기준 클래스를 작성하여
//	정렬한 결과를 출력하시오

// 	(등수는 List에 전체 데이터가 추가된 후에 구해서 저장한다) => StudentTest클래스에서 처리

public class StudentTest {

	// 등수를 구하는 메서드
	public void createRank(List<Student> list) {
		for (Student stu1 : list) { // 등수를 구할 기준 데이터를 구하기 위한 반복문
			int rank = 1; // 처음에는 1등으로 설정해놓고 시작
			for (Student stu2 : list) { // 비교 대상을 나타내는 반복문
				// 기준값보다 큰 값을 만나면 rank변수의 값을 증가시킨다
				if (stu1.getTotalScore() < stu2.getTotalScore()) {
					rank++;
				}
			}
			// 구해진 등수를 기준 데이터의 rank변수에 저장
			stu1.setRank(rank);
		}
	}

	public static void main(String[] args) {
		List<Student> stuList = new ArrayList<Student>();

		stuList.add(new Student(3, "최예원", 90, 80, 70));
		stuList.add(new Student(2, "아폴리", 70, 70, 30));
		stuList.add(new Student(1, "도시락", 60, 80, 70));
		stuList.add(new Student(5, "김메이", 50, 30, 60));
		stuList.add(new Student(4, "진달래", 60, 60, 70));
		stuList.add(new Student(6, "겨울이", 40, 60, 70));
		stuList.add(new Student(8, "홍길동", 60, 50, 60));
		stuList.add(new Student(7, "진진짜", 80, 60, 90));

		// 등수를 구하는 메서드 호출
		StudentTest test = new StudentTest();
		test.createRank(stuList);

		System.out.println("정렬 전 리스트 : ");
		for (Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println();
		System.out.println("--------------------------------------------------");

		Collections.sort(stuList);
		System.out.println();
		System.out.println("학번의 오름차순으로 정렬 후 리스트 : ");
		for (Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println();
		System.out.println("--------------------------------------------------");

		Collections.sort(stuList, new SortDesc());
		System.out.println();
		System.out.println("총점의 내림차순으로 정렬 후 리스트 : ");
		for (Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println();
		System.out.println("--------------------------------------------------");
	}

}

class Student implements Comparable<Student> {

	// 매개변수
	private int stuNo;
	private String stuName;

	private int korScore;
	private int engScore;
	private int mathScore;

	private int totalScore;
	private int rank;

	// getter&setter
	public int getStuNo() {
		return stuNo;
	}

	public void setStuNo(int stuNo) {
		this.stuNo = stuNo;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public int getKorScore() {
		return korScore;
	}

	public void setKorScore(int korScore) {
		this.korScore = korScore;
	}

	public int getEngScore() {
		return engScore;
	}

	public void setEngScore(int engScore) {
		this.engScore = engScore;
	}

	public int getMathScore() {
		return mathScore;
	}

	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	// 생성자
	public Student(int stuNo, String stuName, int korScore, int engScore, int mathScore) {
		this.stuNo = stuNo;
		this.stuName = stuName;
		this.korScore = korScore;
		this.engScore = engScore;
		this.mathScore = mathScore;

		// 총점 초기화
		totalScore = korScore + engScore + mathScore;

	}

	@Override
	public String toString() {
		return "Student [ stuNo = " + stuNo + ", stuName = " + stuName + ", korScore = " + korScore + ", engScore = "
				+ engScore + ", mathScore = " + mathScore + ", totalScore = " + totalScore + ", rank = " + rank + " ]";
	}

	// 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현
	@Override
	public int compareTo(Student stu) {
		return new Integer(this.stuNo).compareTo(stu.stuNo);
	}
}

// 총점의 역순(내림차순)으로 정렬하는데 총점이 같으면 그 이름의 오름차순으로 정렬이 되는 외부 정렬 기준 클래스
class SortDesc implements Comparator<Student> {
	@Override
	public int compare(Student stu1, Student stu2) {
		if (stu1.getTotalScore() == stu2.getTotalScore()) {
			return stu1.getStuName().compareTo(stu2.getStuName());
		} else {
			return Integer.compare(stu1.getTotalScore(), stu2.getTotalScore()) * -1;
		}
	}
}