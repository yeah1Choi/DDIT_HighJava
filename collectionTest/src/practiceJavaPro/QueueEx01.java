package practiceJavaPro;

import java.util.LinkedList;
import java.util.Queue;

public class QueueEx01 {

	public static void main(String[] args) {
		Queue<Message> messageQueue = new LinkedList<Message>();

		messageQueue.offer(new Message("sendMail", "비둘기")); // command, to 
		messageQueue.offer(new Message("sendSMS", "야자수"));
		messageQueue.offer(new Message("sendKakaotalk", "진달래"));

		while (!messageQueue.isEmpty()) {
			Message message = messageQueue.poll(); // Message클래스 타입의 변수 message에다가 messageQueue큐에서 꺼내온 것을 저장
			switch (message.command) { // ('입력변수') = message.command => 변수 message의 command 변수 
			case "sendMail":
				System.out.println(message.to + "님에게 메일을 보냅니다."); // message.to : 변수 message의 to 변수
				break;
			case "sendSMS":
				System.out.println(message.to + "님에게 SMS을 보냅니다.");
				break;
			case "sendKakaotalk":
				System.out.println(message.to + "님에게 카카오톡을 보냅니다.");
				break;
			}
		}
	}

}

class Message {
	public String command;
	public String to;

	public Message(String command, String to) {
		this.command = command;
		this.to = to;
	}
}