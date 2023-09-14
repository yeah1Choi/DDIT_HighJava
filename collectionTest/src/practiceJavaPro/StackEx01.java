package practiceJavaPro;

import java.util.*;

public class StackEx01 {

	public static void main(String[] args) {

		Stack<Coin> coinBox = new Stack<Coin>();

		coinBox.push(new Coin(100));
		coinBox.push(new Coin(50));
		coinBox.push(new Coin(500));
		coinBox.push(new Coin(10));

		while (!coinBox.isEmpty()) {
			Coin coin = coinBox.pop();
			System.out.println("꺼내온 동전 : " + coin.getValue() + "원");
		}
	}

}

class Coin {
	private int value;

	public Coin(int value) {
		// super();
		this.value = value;
	}

	int getValue() {
		return value;
	}
}