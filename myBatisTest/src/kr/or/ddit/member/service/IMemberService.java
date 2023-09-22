package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.MemberVO;
/**
 * Service 객체는 DAO에 만들어진 메서드를 원하는 작업에 맞게 호출하여
 * 그 결과를 받아오고, 받아온 결과를 Controller에게 보내주는 역할을 한다
 * 
 * ( 자바 고급시간에서는 보통 DAO의 구조와 같게 만든다)
 * 
 * @author PC-24
 *
 */
public interface IMemberService {
	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	 * 
	 * @param memVo : DB에 insert할 자료가 저장될 MemberVo객체
	 * @return : 작업 성공시 1, 실패시 0
	 */
	public int insertMember(MemberVO memVo);
	
	/**
	 * 회원ID를 매개변수로 받아 해당 회원 정보를 삭제하는 메서드
	 * 
	 * @param memId : 삭제할 회원ID
	 * @return : 작업성공시 1, 실패시 0
	 */
	public int deleteMember(String memId);

	/**
	 * MemberVo 자료를 이용하여 DB에 update하는 메서드
	 * 
	 * @param memVo : update할 회원 정보가 저장된 MemberVo 객체
	 * @return : 작업성공시 1, 실패시 0
	 */
	public int updateMember(MemberVO memVo);

	/**
	 * DB에 저장된 전체 회원 정보를 가져와서 List에 담아서 반환하는 메서드
	 * 
	 * @return : MemberVO 객체가 저장된 List
	 */
	public List<MemberVO> getAllMember();
	
	/**
	 * 회원ID를 매개변수로 받아서 해당 회원ID의 개수를 반환하는 메서드
	 * 
	 * @param memId : 검색할 회원ID
	 * @return : 검색된 회원ID의 개수
	 */
	public int getMemIdCount(String memId);
	
	/**
	 * Map의 정보를 이용하여 회원 정보 중 원하는 컬럼을 수정하는 메서드
	 *     key값 정보 : 회원ID(memid), 수정할컬럼명(field), 수정할데이터(data)
	 *     
	 * @param paramMap : 회원ID, 수정할 컬럼명, 수정할 데이터가 저장된 Map객체
	 * @return : 작업성공시 1, 실패시 0
	 */
	public int selectUpdate(Map<String, String> paramMap); 
}
