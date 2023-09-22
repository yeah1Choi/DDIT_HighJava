package kr.or.ddit.basic;

import org.apache.log4j.Logger;

public class Log4jTest {
	// Logger 클래스의 인스턴스를 받아온다
	static Logger logger = Logger.getLogger(Log4jTest.class);
	
	public static void main(String[] args) {
		// 로그 기록 남기기
		// 형식 ) Logger객체.로그레벨이름("출력할 메시지")
		// 예) logger.trace("메시지")
		logger.trace("이것은 Log4j의 [TRACE]레벨의 출력입니다");
		logger.debug("이것은 Log4j의 [DEBUG]레벨의 출력입니다");
		logger.info("이것은 Log4j의 [INFO]레벨의 출력입니다");
		logger.warn("이것은 Log4j의 [WARN]레벨의 출력입니다");
		logger.error("이것은 Log4j의 [ERROR]레벨의 출력입니다");
		logger.fatal("이것은 Log4j의 [FATAL]레벨의 출력입니다");
		
	}
}