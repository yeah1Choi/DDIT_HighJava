package kr.or.ddit.vo;
/**
 * 파일 정보가 저장될 VO객체
 * @author PC-24
 *
 */
public class FileInfoVO {
	private int file_no;
	private String file_writer;
	private String origin_file_name;
	private String save_file_name;
	private long file_size;
	private String file_date;
	public int getFile_no() {
		return file_no;
	}
	public void setFile_no(int file_no) {
		this.file_no = file_no;
	}
	public String getFile_writer() {
		return file_writer;
	}
	public void setFile_writer(String file_writer) {
		this.file_writer = file_writer;
	}
	public String getOrigin_file_name() {
		return origin_file_name;
	}
	public void setOrigin_file_name(String origin_file_name) {
		this.origin_file_name = origin_file_name;
	}
	public String getSave_file_name() {
		return save_file_name;
	}
	public void setSava_file_name(String save_file_name) {
		this.save_file_name = save_file_name;
	}
	public long getFile_size() {
		return file_size;
	}
	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}
	public String getFile_date() {
		return file_date;
	}
	public void setFile_date(String file_date) {
		this.file_date = file_date;
	}
	
	
}
