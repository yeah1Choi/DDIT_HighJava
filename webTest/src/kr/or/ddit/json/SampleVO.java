package kr.or.ddit.json;

public class SampleVO {
	private int num;
	private String name;
	
	public SampleVO(int num, String name) {
		super();
		this.num = num;
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "SampleVO [name=" + name + ", num=" + num + "]";
	}
}