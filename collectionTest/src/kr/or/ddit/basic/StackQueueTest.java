package kr.or.ddit.basic;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackQueueTest {

	public static void main(String[] args) {
		/*
		 * Stack의 명령 
		 * 1. 자료 입력 : push(입력값) 
		 * 2. 자료 출력 : pop() => 자료를 꺼내온 후 꺼내온 자료를 Stack에 삭제한다
		 *             peek() => 삭제없이 자료를 꺼내온다
		 */
		Stack<String> stack = new Stack<String>();

		stack.push("홍길동");
		stack.push("최원팔");
		stack.push("강감찬");
		stack.push("변학도");
		stack.push("일지매");

		System.out.println("현재 stack값 : " + stack);
		// 현재 stack값 : [홍길동, 최원팔, 강감찬, 변학도, 일지매]
		// 출력되는 배열 뒤에가 입구, 출구 : 일지매 뒤에

		String data = stack.pop();
		System.out.println("꺼내온 값 : " + data);
		System.out.println("꺼내온 값 : " + stack.pop());
		System.out.println("현재 stack값 : " + stack);

		stack.push("성춘향");
		System.out.println("추가 후 stack값 : " + stack);

		System.out.println("꺼내온 값 : " + stack.pop());
		System.out.println("현재 stack값 : " + stack);

		System.out.println("삭제없이 꺼내온 값 : " + stack.peek());
		System.out.println("현재 stack값 : " + stack);

		System.out.println();
		System.out.println("///////////////////////////////////////////////////");
		System.out.println();

		/*
		 * Queue의 명령 
		 * 1. 자료 입력 : offer(입력값) 
		 * 2. 자료 출력 : poll() => 자료를 꺼내온 후 꺼내온 자료를 Queue에 삭제한다
		 *             peek() => 삭제없이 자료를 꺼내온다
		 */

		Queue<String> queue = new LinkedList<String>();

		queue.offer("홍길동");
		queue.offer("최원팔");
		queue.offer("강감찬");
		queue.offer("변학도");
		queue.offer("일지매");

		System.out.println("현재 queue값 : " + queue);
		
		String temp = queue.poll();
		System.out.println("꺼내온 값 : " + temp);
		System.out.println("꺼내온 값 : " + queue.poll());
		System.out.println("현재 queue값 : " + queue);
		
		stack.push("성춘향");
		System.out.println("추가 후 queue값 : " + queue);
		
		System.out.println("꺼내온 값 : " + queue.poll());
		System.out.println("현재 queue값 : " + queue);

		System.out.println("삭제없이 꺼내온 값 : " + queue.peek());
		System.out.println("현재 queue값 : " + queue);
	}

}
