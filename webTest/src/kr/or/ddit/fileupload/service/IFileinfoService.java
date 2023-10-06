package kr.or.ddit.fileupload.service;

import java.util.List;

import kr.or.ddit.vo.FileInfoVO;

public interface IFileinfoService {
	/**
	 * 업로드된 파일 정보가 저장된 FileinfoVO객체를 매개변수로 받아서 
	 * DB에 insert하는 메서드 
	 * @param fvo insert할 파일 정보가 저장된 FileInfoVO객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int insertFileinfo(FileInfoVO fvo);
	/**
	 * DB에 저장된 전체 파일 목록을 가져와 List에 담아 반환하는 메소드
	 * @return 전체 파일 정보가 저장된 List객체
	 */
	public List<FileInfoVO> getAllFileinfo();
	/**
	 * 파일 번호를 매개변수로 받아서 해당 파일 정보를 검색하여 반환하는 메소드
	 * @param fileNo 검색할 파일 번호
	 * @return 검색된 파일 정보가 저장된 FileInfoVO객체
	 */
	public FileInfoVO getFileinfo(int fileNo);
}
