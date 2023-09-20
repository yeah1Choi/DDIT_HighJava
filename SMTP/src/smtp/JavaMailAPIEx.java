package smtp;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class JavaMailAPIEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// *발신자의 정보
		String host = "smtp.naver.com"; // <= 네이버 SMTP 서버명
		final String user = "yewon8586@naver.com"; // <= 발신자 이메일 주소
		final String password = "Luv4paul!"; // <= 발신자 이메일 비밀번호

		// *수신자의 정보
		String to = "yewon8586@naver.com"; // <= 수신자 이메일 주소

		// Property에 SMTP 서버 정보를 설정
		Properties props = new Properties();
		props.put("mail.smtp.host", host); // <= SMTP 서버명
		props.put("mail.smtp.auth", "true");

		// SMTP 서버 정보와 사용자 정보를 기반으로 Session 클래스의 인스턴스 생성
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// Message 클래스의 객체를 사용해 수신자와 내용, 제목, 메일을 작성
		try {
            // MimeMessage 객체 생성
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // 제목 부분 추가
            message.setSubject("Art Space 주간 뉴스레터: 새로운 소식과 업데이트");

            // 텍스트 부분 추가
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("안녕하세요,\n 9월 3째주의 주요 뉴스 및 아트페어 개최를 안내합니다.");

            // 이미지 파일 부분 추가 (광고 이미지 파일 경로를 변경해야 합니다)
            MimeBodyPart imagePart = new MimeBodyPart();
            imagePart.attachFile("D:/A_TeachingMaterial/03_HighJava/workspace/NetworkTest/src/kr/or/ddit/basic/뉴스레터이미지.png"); // 광고 이미지 파일 경로 지정

            // 첨부 파일의 이름 설정 (원하는 이름으로 변경)
            imagePart.setFileName("23년9월아트페어.jpg");
      
            // Multipart 객체 생성 (텍스트와 첨부 파일을 함께 보내기 위해)
            Multipart multipart = new MimeMultipart();
            
            // 텍스트와 이미지 부분을 Multipart에 추가
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(imagePart);

            // 메시지에 Multipart 설정
            message.setContent(multipart);

            // 메일 보내기
            Transport.send(message);
            System.out.println("아트 뉴스레터 메일을 성공적으로 보냈습니다.");

			// 예외처리
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}