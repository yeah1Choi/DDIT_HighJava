package kr.or.ddit.vo;

import java.sql.Date;

public class BoardVO {
	public int board_no;
	public String board_title;
	public String board_writer;
	public Date board_date;
	public int board_cnt;
	public String board_content;
	
	
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_writer() {
		return board_writer;
	}
	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public Date getBoard_date() {
		return board_date;
	}
	public void setBoard_date(Date date) {
		this.board_date = date;
	}
	public int getBoard_cnt() {
		return board_cnt;
	}
	public void setBoard_cnt(int board_cnt) {
		this.board_cnt = board_cnt;
	}
	@Override
	public String toString() {
		return "BoardVO [board_no=" + board_no + ", board_title=" + board_title + ", board_writer=" + board_writer
				+ ", board_date=" + board_date + ", board_cnt=" + board_cnt + ", board_content=" + board_content + "]";
	}
}
